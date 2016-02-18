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

package com.github.sviperll.higherkindedjava;

import com.github.sviperll.adt4j.GenerateValueClassForVisitor;
import com.github.sviperll.adt4j.Getter;
import com.github.sviperll.adt4j.Updater;
import com.github.sviperll.adt4j.Visitor;
import com.github.sviperll.adt4j.WrapsGeneratedValueClass;
import com.github.sviperll.higherkindedjava.util.Functor;
import com.github.sviperll.higherkindedjava.util.Monad;
import com.github.sviperll.higherkindedjava.util.generic.Self;
import com.github.sviperll.higherkindedjava.util.generic.Type;
import java.util.function.Function;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 *
 * @author Victor Nazarov &lt;asviraspossible@gmail.com&gt;
 */
@ParametersAreNonnullByDefault
@WrapsGeneratedValueClass(visitor = Optional.OptionalVisitor.class)
public class Optional<T> extends OptionalBase<T>
        implements Type.ConstructorApplication<Optional<?>, Optional<T>, T>,
                Monad<Optional<?>, Optional<T>, T>{

    public static final Monad.Util<Optional<?>> MONAD = OptionalMonad.INSTANCE;

    public static <CAT extends Monad<Optional<?>, CAT, T>, T> Optional<T> fromGeneric(CAT value) {
        return Type.Equality.<Optional<?>, Optional<T>, CAT, T>obviousForConstructorApplication().cast(value);
    }

    Optional(OptionalBase<T> value) {
         super(value);
    }

    @Override
    public Optional<T> self() {
        return this;
    }

    @Override
    public Util<Optional<?>> monad() {
        return OptionalMonad.INSTANCE;
    }

    public <U> Optional<U> map(Function<T, U> f) {
        return accept(new OptionalVisitor<T, Optional<U>>() {
            @Override
            public Optional<U> missing() {
                return Optional.missing();
            }

            @Override
            public Optional<U> present(T value) {
                return Optional.present(f.apply(value));
            }
        });
    }

    public <U> Optional<U> flatMap(Function<T, Optional<U>> f) {
        return accept(new OptionalVisitor<T, Optional<U>>() {
            @Override
            public Optional<U> missing() {
                return Optional.missing();
            }

            @Override
            public Optional<U> present(T value) {
                return f.apply(value);
            }
        });
    }

    @Override
    public <CAU extends Functor<Optional<?>, CAU, U>, U> Self<CAU> mapGeneric(Function<T, U> f) {
        return map(f).<CAU>toGeneric();
    }

    @Override
    public <CAAU extends Monad<Optional<?>, CAAU, CAU>, CAU extends Monad<Optional<?>, CAU, U>, U> Self<CAU> flatMapGeneric(Function<T, CAU> f) {
        return flatMap((e) -> fromGeneric(f.apply(e))).<CAU>toGeneric();
    }

    <CAT extends Type.ConstructorApplication<Optional<?>, CAT, T>> CAT toGeneric() {
        return Type.Equality.<Optional<?>, CAT, Optional<T>, T>obviousForConstructorApplication().cast(this);
    }

    private enum OptionalMonad implements Monad.Util<Optional<?>> {
        INSTANCE;

        @Override
        public <CAT extends Monad<Optional<?>, CAT, T>, T> Self<CAT> unit(T value) {
            return Optional.present(value).<CAT>toGeneric();
        }
    }

    @GenerateValueClassForVisitor(wrapperClass = Optional.class)
    @Visitor(resultVariableName = "R")
    public interface OptionalVisitor<T, R> {
        R missing();
        R present(@Getter(name = "getValue") @Updater T value);
    }


}
