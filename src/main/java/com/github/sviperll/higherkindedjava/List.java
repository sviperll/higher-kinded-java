/*
 * Copyright (c) 2015, Victor Nazarov &lt;asviraspossible@gmail.com&gt;
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
import com.github.sviperll.higherkindedjava.util.generic.Type;
import com.github.sviperll.higherkindedjava.util.Monad;
import com.github.sviperll.higherkindedjava.util.Functor;
import java.util.function.Function;

/**
 *
 * @author Victor Nazarov &lt;asviraspossible@gmail.com&gt;
 */
@WrapsGeneratedValueClass(visitor = List.ListVisitor.class)
public class List<T> extends ListBase<T>
        implements Type.ConstructorApplication<List.TypeConstructor, List<?>, List<T>, T>,
        Monad<List.TypeConstructor, List<?>, List<T>, T> {
    public static <CAT extends Monad<TypeConstructor, List<?>, CAT, T>, T> List<T> fromGeneric(CAT value) {
        return Type.Equality.<TypeConstructor, List<?>, List<T>, CAT, T>obviousForConstructorApplication().cast(value);
    }

    public static <T> List<T> join(List<List<T>> lists) {
        return lists.accept(new ListVisitor<List<T>, List<T>>() {
            @Override
            public List<T> empty() {
                return List.<T>empty();
            }

            @Override
            public List<T> prepend(List<T> head, List<List<T>> tail) {
                return head.append(join(tail));
            }
        });
    }

    List(ListBase<T> value) {
         super(value);
    }

    public List<T> append(final List<T> last) {
        return accept(new ListVisitor<T, List<T>>() {
            @Override
            public List<T> empty() {
                return last;
            }

            @Override
            public List<T> prepend(T head, List<T> tail) {
                return List.prepend(head, tail.append(last));
            }
        });
    }

    public <U> List<U> map(final Function<T, U> function) {
        return accept(new ListVisitor<T, List<U>>() {
            @Override
            public List<U> empty() {
                return List.empty();
            }

            @Override
            public List<U> prepend(T head, List<T> tail) {
                return List.prepend(function.apply(head), tail.map(function));
            }
        });
    }

    <CAT extends Type.ConstructorApplication<TypeConstructor, List<?>, CAT, T>> CAT toGeneric() {
        return Type.Equality.<TypeConstructor, List<?>, CAT, List<T>, T>obviousForConstructorApplication().cast(this);
    }

    @Override
    public <CAU extends Functor<TypeConstructor, List<?>, CAU, U>, U> CAU mapGeneric(Function<T, U> f) {
        return map(f).<CAU>toGeneric();
    }

    @Override
    public Monad.Util<TypeConstructor, List<?>> monad() {
        return ListMonad.INSTANCE;
    }

    @GenerateValueClassForVisitor(wrapperClass = List.class)
    @Visitor(resultVariableName = "R")
    public interface ListVisitor<T, R> {
        R empty();
        R prepend(@Getter @Updater T head, @Getter @Updater List<T> tail);
    }

    private static enum ListMonad implements Monad.Util<TypeConstructor, List<?>> {
        INSTANCE;
        @Override
        public <CAT extends Monad<TypeConstructor, List<?>, CAT, T>, T> CAT unit(T value) {
            return List.prepend(value, List.empty()).toGeneric();
        }
        @Override
        public <CAAT extends Monad<TypeConstructor, List<?>, CAAT, CAT>, CAT extends Monad<TypeConstructor, List<?>, CAT, T>, T> CAT join(CAAT values) {
            Type.Equality<CAAT, List<CAT>> equality1 = Type.Equality.<TypeConstructor, List<?>, CAAT, List<CAT>, CAT>obviousForConstructorApplication();
            Type.Equality<CAT, List<T>> equality2 = Type.Equality.<TypeConstructor, List<?>, CAT, List<T>, T>obviousForConstructorApplication();
            Type.Equality<List<CAT>, List<List<T>>> equality3 = equality2.<TypeConstructor, List<?>, List<CAT>, List<List<T>>>toTypeConstructorApplication();
            Type.Equality<CAAT, List<List<T>>> equality4 = equality1.merge(equality3);
            return equality2.cast(List.join(equality4.reverse().cast(values)));
        }

    }

    public abstract static class TypeConstructor extends Type.Constructor<TypeConstructor, List<?>> {
        TypeConstructor(TypeConstructor instance) {
            super(instance);
        }
    }
}
