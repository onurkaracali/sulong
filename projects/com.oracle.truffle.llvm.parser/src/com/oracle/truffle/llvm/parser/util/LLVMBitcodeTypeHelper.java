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
package com.oracle.truffle.llvm.parser.util;

import com.oracle.truffle.llvm.parser.model.enums.BinaryOperator;
import com.oracle.truffle.llvm.parser.model.enums.CastOperator;
import com.oracle.truffle.llvm.runtime.NodeFactory;
import com.oracle.truffle.llvm.runtime.nodes.api.LLVMExpressionNode;
import com.oracle.truffle.llvm.runtime.types.Type;

public final class LLVMBitcodeTypeHelper {

    public static LLVMExpressionNode createArithmeticInstruction(NodeFactory nodeFactory, LLVMExpressionNode lhs, LLVMExpressionNode rhs, BinaryOperator operator) {
        switch (operator) {
            case INT_ADD:
            case FP_ADD:
                return nodeFactory.createAdd(lhs, rhs);
            case INT_SUBTRACT:
            case FP_SUBTRACT:
                return nodeFactory.createSub(lhs, rhs);
            case INT_MULTIPLY:
            case FP_MULTIPLY:
                return nodeFactory.createMul(lhs, rhs);
            case INT_UNSIGNED_DIVIDE:
                return nodeFactory.createUDiv(lhs, rhs);
            case INT_SIGNED_DIVIDE:
            case FP_DIVIDE:
                return nodeFactory.createDiv(lhs, rhs);
            case INT_UNSIGNED_REMAINDER:
                return nodeFactory.createURem(lhs, rhs);
            case INT_SIGNED_REMAINDER:
            case FP_REMAINDER:
                return nodeFactory.createRem(lhs, rhs);
            default:
                return null;
        }
    }

    public static LLVMExpressionNode createLogicalInstructionType(NodeFactory nodeFactory, LLVMExpressionNode lhs, LLVMExpressionNode rhs, BinaryOperator operator) {
        switch (operator) {
            case INT_SHIFT_LEFT:
                return nodeFactory.createShl(lhs, rhs);
            case INT_LOGICAL_SHIFT_RIGHT:
                return nodeFactory.createLShr(lhs, rhs);
            case INT_ARITHMETIC_SHIFT_RIGHT:
                return nodeFactory.createAShr(lhs, rhs);
            case INT_AND:
                return nodeFactory.createAnd(lhs, rhs);
            case INT_OR:
                return nodeFactory.createOr(lhs, rhs);
            case INT_XOR:
                return nodeFactory.createXor(lhs, rhs);
            default:
                return null;
        }
    }

    public static LLVMExpressionNode createCast(NodeFactory nodeFactory, LLVMExpressionNode fromNode, Type targetType, Type fromType, CastOperator operator) {
        switch (operator) {
            case ZERO_EXTEND:
            case UNSIGNED_INT_TO_FP:
            case INT_TO_PTR:
            case FP_TO_UNSIGNED_INT:
                return nodeFactory.createUnsignedCast(fromNode, targetType);
            case SIGN_EXTEND:
            case TRUNCATE:
            case FP_TO_SIGNED_INT:
            case FP_EXTEND:
            case FP_TRUNCATE:
            case PTR_TO_INT:
            case SIGNED_INT_TO_FP:
                return nodeFactory.createSignedCast(fromNode, targetType);
            case BITCAST:
                return nodeFactory.createBitcast(fromNode, targetType, fromType);
            case ADDRESS_SPACE_CAST:
            default:
                return null;
        }
    }
}
