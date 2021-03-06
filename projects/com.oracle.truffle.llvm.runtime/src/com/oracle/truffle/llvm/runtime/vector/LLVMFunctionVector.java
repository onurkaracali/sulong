/*
 * Copyright (c) 2017, 2018, Oracle and/or its affiliates.
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
package com.oracle.truffle.llvm.runtime.vector;

import java.util.Arrays;

import com.oracle.truffle.llvm.runtime.LLVMFunctionDescriptor;
import com.oracle.truffle.llvm.runtime.pointer.LLVMNativePointer;

public final class LLVMFunctionVector {
    private final Object[] vector;

    public static LLVMFunctionVector create(LLVMFunctionDescriptor[] vector) {
        return new LLVMFunctionVector(vector);
    }

    public static LLVMFunctionVector create(long[] vector) {
        return new LLVMFunctionVector(vector);
    }

    public static LLVMFunctionVector create(Object[] vector) {
        return new LLVMFunctionVector(vector);
    }

    public static LLVMFunctionVector createNullVector() {
        return new LLVMFunctionVector();
    }

    private LLVMFunctionVector(LLVMFunctionDescriptor[] vector) {
        this.vector = new Object[vector.length];
        for (int i = 0; i < vector.length; i++) {
            this.vector[i] = vector[i];
        }

    }

    private LLVMFunctionVector(long[] vector) {
        this.vector = new LLVMNativePointer[vector.length];
        for (int i = 0; i < vector.length; i++) {
            this.vector[i] = LLVMNativePointer.create(vector[i]);
        }
    }

    private LLVMFunctionVector(Object[] vector) {
        this.vector = new Object[vector.length];
        for (int i = 0; i < vector.length; i++) {
            this.vector[i] = vector[i];
        }
    }

    private LLVMFunctionVector() {
        this.vector = null;
    }

    public Object[] getValues() {
        return vector;
    }

    public Object getValue(int index) {
        return vector[index];
    }

    public LLVMFunctionVector insert(LLVMNativePointer element, int index) {
        Object[] copyOf = Arrays.copyOf(vector, vector.length);
        copyOf[index] = element;
        return create(copyOf);
    }

    public LLVMFunctionVector insert(long element, int index) {
        Object[] copyOf = Arrays.copyOf(vector, vector.length);
        copyOf[index] = LLVMNativePointer.create(element);
        return create(copyOf);
    }

    public int getLength() {
        return vector.length;
    }
}
