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

import com.github.sviperll.higherkindedjava.Optional;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 *
 * @author Victor Nazarov &lt;asviraspossible@gmail.com&gt;
 */
@ParametersAreNonnullByDefault
public class Type {
    private Type() {
    }

    public static class Eq<T, U> {
        @SuppressWarnings("rawtypes")
        private static final Eq INSTANCE = new Eq();

        @SuppressWarnings("unchecked")
        public static <T> Eq<T, T> obvious() {
            return INSTANCE;
        }

        public static <CA extends App<CA, ? extends CA, ?>, V extends App<CA, V, T>, W extends App<CA, W, T>, T> Eq<V, W> obviousForConstructorApplication() {
            return Eq.<T>obvious().<CA, V, W>toTypeConstructorApplication();
        }

        @SuppressWarnings("unchecked")
        public static <CA extends App<CA, ? extends CA, ?>, V extends App<CA, V, T>, T, W extends App<CA, W, U>, U> Eq<T, U> fromTypeConstructorApplication(Eq<V, W> evidence) {
            return INSTANCE;
        }

        private Eq() {
        }

        @SuppressWarnings("unchecked")
        public T cast(U u) {
            return (T)u;
        }

        @SuppressWarnings("unchecked")
        public Eq<U, T> reverse() {
            return INSTANCE;
        }

        @SuppressWarnings("unchecked")
        public <V> Eq<T, V> merge(Eq<U, V> evidence) {
            return INSTANCE;
        }

        @SuppressWarnings("unchecked")
        public <CA extends App<CA, ? extends CA, ?>, V extends App<CA, V, T>, W extends App<CA, W, U>> Eq<V, W> toTypeConstructorApplication() {
            return INSTANCE;
        }
    }

    public interface App<CA extends App<CA, ? extends CA, ?>, CAT extends App<CA, CAT, T>, T> {
        @SuppressWarnings("unchecked")
        default CAT castToType() {
            return (CAT)this;
        }
    }

}
