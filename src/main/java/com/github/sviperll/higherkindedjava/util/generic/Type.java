/*
 * Copyright (c) 2016, Victor Nazarov &lt;asviraspossible@gmail.com&gt;
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation and/or
 *     other materials provided with the distribution.
 *
 *  3. Neither the name of the copyright holder nor the names of its contributors
 *     may be used to endorse or promote products derived from this software
 *     without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 *  THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 *  ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *   LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.github.sviperll.higherkindedjava.util.generic;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 *
 * @author Victor Nazarov &lt;asviraspossible@gmail.com&gt;
 */
@ParametersAreNonnullByDefault
public class Type {
    private Type() {
    }

    public static class Equality<T, U> {
        @SuppressWarnings("rawtypes")
        private static final Equality INSTANCE = new Equality();

        @SuppressWarnings("unchecked")
        public static <T> Equality<T, T> obvious() {
            return INSTANCE;
        }

        public static <CA extends ConstructorApplication<CA, ? extends CA, ?>, V extends ConstructorApplication<CA, V, T>, W extends ConstructorApplication<CA, W, T>, T> Equality<V, W> obviousForConstructorApplication() {
            return Equality.<T>obvious().<CA, V, W>toTypeConstructorApplication();
        }

        @SuppressWarnings("unchecked")
        public static <CA extends ConstructorApplication<CA, ? extends CA, ?>, V extends ConstructorApplication<CA, V, T>, T, W extends ConstructorApplication<CA, W, U>, U> Equality<T, U> fromTypeConstructorApplication(Equality<V, W> evidence) {
            return INSTANCE;
        }

        private Equality() {
        }

        @SuppressWarnings("unchecked")
        public T cast(U u) {
            return (T)u;
        }

        @SuppressWarnings("unchecked")
        public Equality<U, T> reverse() {
            return INSTANCE;
        }

        @SuppressWarnings("unchecked")
        public <V> Equality<T, V> merge(Equality<U, V> evidence) {
            return INSTANCE;
        }

        @SuppressWarnings("unchecked")
        public <CA extends ConstructorApplication<CA, ? extends CA, ?>, V extends ConstructorApplication<CA, V, T>, W extends ConstructorApplication<CA, W, U>> Equality<V, W> toTypeConstructorApplication() {
            return INSTANCE;
        }
    }

    public interface ConstructorApplication<CA extends ConstructorApplication<CA, ? extends CA, ?>, CAT extends ConstructorApplication<CA, CAT, T>, T> extends Self<CAT> {
    }

}
