/*
 * Copyright (c) 2016, 2018, Oracle and/or its affiliates.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of
 * conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided
 * with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.oracle.truffle.llvm.nodes.literals;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.llvm.runtime.nodes.api.LLVMExpressionNode;
import com.oracle.truffle.llvm.runtime.nodes.api.LLVMToNativeNode;
import com.oracle.truffle.llvm.runtime.nodes.api.LLVMToNativeNodeGen;
import com.oracle.truffle.llvm.runtime.nodes.api.LLVMTypesGen;
import com.oracle.truffle.llvm.runtime.pointer.LLVMNativePointer;
import com.oracle.truffle.llvm.runtime.vector.LLVMPointerVector;
import com.oracle.truffle.llvm.runtime.vector.LLVMDoubleVector;
import com.oracle.truffle.llvm.runtime.vector.LLVMFloatVector;
import com.oracle.truffle.llvm.runtime.vector.LLVMFunctionVector;
import com.oracle.truffle.llvm.runtime.vector.LLVMI16Vector;
import com.oracle.truffle.llvm.runtime.vector.LLVMI1Vector;
import com.oracle.truffle.llvm.runtime.vector.LLVMI32Vector;
import com.oracle.truffle.llvm.runtime.vector.LLVMI64Vector;
import com.oracle.truffle.llvm.runtime.vector.LLVMI8Vector;

public class LLVMVectorLiteralNode {

    public abstract static class LLVMVectorI1LiteralNode extends LLVMExpressionNode {

        @Children private final LLVMExpressionNode[] values;

        public LLVMVectorI1LiteralNode(LLVMExpressionNode[] values) {
            this.values = values;
        }

        @ExplodeLoop
        @Specialization
        protected LLVMI1Vector doI1Vector(VirtualFrame frame) {
            boolean[] vals = new boolean[values.length];
            for (int i = 0; i < values.length; i++) {
                vals[i] = LLVMTypesGen.asBoolean(values[i].executeGeneric(frame));
            }
            return LLVMI1Vector.create(vals);
        }
    }

    public abstract static class LLVMVectorI8LiteralNode extends LLVMExpressionNode {

        @Children private final LLVMExpressionNode[] values;

        public LLVMVectorI8LiteralNode(LLVMExpressionNode[] values) {
            this.values = values;
        }

        @ExplodeLoop
        @Specialization
        protected LLVMI8Vector doI8Vector(VirtualFrame frame) {
            byte[] vals = new byte[values.length];
            for (int i = 0; i < values.length; i++) {
                vals[i] = LLVMTypesGen.asByte(values[i].executeGeneric(frame));
            }
            return LLVMI8Vector.create(vals);
        }
    }

    public abstract static class LLVMVectorI16LiteralNode extends LLVMExpressionNode {

        @Children private final LLVMExpressionNode[] values;

        public LLVMVectorI16LiteralNode(LLVMExpressionNode[] values) {
            this.values = values;
        }

        @ExplodeLoop
        @Specialization
        protected LLVMI16Vector doI16Vector(VirtualFrame frame) {
            short[] vals = new short[values.length];
            for (int i = 0; i < values.length; i++) {
                vals[i] = LLVMTypesGen.asShort(values[i].executeGeneric(frame));
            }
            return LLVMI16Vector.create(vals);
        }
    }

    public abstract static class LLVMVectorI32LiteralNode extends LLVMExpressionNode {

        @Children private final LLVMExpressionNode[] values;

        public LLVMVectorI32LiteralNode(LLVMExpressionNode[] values) {
            this.values = values;
        }

        @ExplodeLoop
        @Specialization
        protected LLVMI32Vector doI32Vector(VirtualFrame frame) {
            int[] vals = new int[values.length];
            for (int i = 0; i < values.length; i++) {
                vals[i] = LLVMTypesGen.asInteger(values[i].executeGeneric(frame));
            }
            return LLVMI32Vector.create(vals);
        }
    }

    public abstract static class LLVMVectorI64LiteralNode extends LLVMExpressionNode {

        @Children private final LLVMExpressionNode[] values;

        public LLVMVectorI64LiteralNode(LLVMExpressionNode[] values) {
            this.values = values;
        }

        @ExplodeLoop
        @Specialization
        protected LLVMI64Vector doI64Vector(VirtualFrame frame) {
            long[] vals = new long[values.length];
            for (int i = 0; i < values.length; i++) {
                vals[i] = LLVMTypesGen.asLong(values[i].executeGeneric(frame));
            }
            return LLVMI64Vector.create(vals);
        }
    }

    public abstract static class LLVMVectorFloatLiteralNode extends LLVMExpressionNode {

        @Children private final LLVMExpressionNode[] values;

        public LLVMVectorFloatLiteralNode(LLVMExpressionNode[] values) {
            this.values = values;
        }

        @ExplodeLoop
        @Specialization
        protected LLVMFloatVector doFloatVector(VirtualFrame frame) {
            float[] vals = new float[values.length];
            for (int i = 0; i < values.length; i++) {
                vals[i] = LLVMTypesGen.asFloat(values[i].executeGeneric(frame));
            }
            return LLVMFloatVector.create(vals);
        }
    }

    public abstract static class LLVMVectorDoubleLiteralNode extends LLVMExpressionNode {

        @Children private final LLVMExpressionNode[] values;

        public LLVMVectorDoubleLiteralNode(LLVMExpressionNode[] values) {
            this.values = values;
        }

        @ExplodeLoop
        @Specialization
        protected LLVMDoubleVector doDoubleVector(VirtualFrame frame) {
            double[] vals = new double[values.length];
            for (int i = 0; i < values.length; i++) {
                vals[i] = LLVMTypesGen.asDouble(values[i].executeGeneric(frame));
            }
            return LLVMDoubleVector.create(vals);
        }
    }

    public abstract static class LLVMVectorAddressLiteralNode extends LLVMExpressionNode {

        @Children private final LLVMToNativeNode[] toNatives;

        public LLVMVectorAddressLiteralNode(LLVMExpressionNode[] values) {
            this.toNatives = new LLVMToNativeNode[values.length];
            for (int i = 0; i < values.length; i++) {
                this.toNatives[i] = LLVMToNativeNodeGen.create(values[i]);
            }
        }

        @ExplodeLoop
        @Specialization
        protected LLVMPointerVector doAddressVector(VirtualFrame frame) {
            LLVMNativePointer[] vals = new LLVMNativePointer[toNatives.length];
            for (int i = 0; i < toNatives.length; i++) {
                vals[i] = toNatives[i].execute(frame);
            }
            return LLVMPointerVector.create(vals);
        }
    }

    public abstract static class LLVMVectorFunctionLiteralNode extends LLVMExpressionNode {

        @Children private final LLVMExpressionNode[] values;

        public LLVMVectorFunctionLiteralNode(LLVMExpressionNode[] values) {
            this.values = values;
        }

        @ExplodeLoop
        @Specialization
        public LLVMFunctionVector op(VirtualFrame frame) {
            Object[] vals = new Object[values.length];
            for (int i = 0; i < values.length; i++) {
                vals[i] = values[i].executeGeneric(frame);
            }
            return LLVMFunctionVector.create(vals);
        }
    }

}
