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
package com.github.sviperll.higherkindedjava.util;

import com.github.sviperll.higherkindedjava.util.generic.Type;
import java.util.function.Function;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 *
 * @author Victor Nazarov &lt;asviraspossible@gmail.com&gt;
 */
@ParametersAreNonnullByDefault
public interface Monad<C extends Type.Constructor<C, CA>, CA extends Monad<C, CA, ? extends CA, ?>, CAT extends Monad<C, CA, CAT, T>, T>
        extends Functor<C, CA, CAT, T> {
    public Util<C, CA> monad();

    default <CAAU extends Monad<C, CA, CAAU, CAU>, CAU extends Monad<C, CA, CAU, U>, U> CAU flatMapGeneric(Function<T, CAU> f) {
        return monad().<CAAU, CAU, U>join(this.mapGeneric(f));
    }

    public interface Util<C extends Type.Constructor<C, CA>, CA extends Monad<C, CA, ? extends CA, ?>> {
        <CAT extends Monad<C, CA, CAT, T>, T> CAT unit(T value);
        default <CAAT extends Monad<C, CA, CAAT, CAT>, CAT extends Monad<C, CA, CAT, T>, T> CAT join(CAAT values) {
            return values.flatMapGeneric(Function.identity());
        }
    }
}
