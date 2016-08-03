/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sviperll.higherkindedjava.data;

import java.util.function.Function;

/**
 *
 * @author vir
 */
public abstract class AnyList<T> implements List<T> {
    public static <T> AnyList<T> wrap(EmptyList<T>.InitializationToken empty) {
        EmptyList<T> value = empty.instance();
        return new AnyList<T>() {
            @Override
            public <R> R accept(Visitor<T, R> visitor) {
                return visitor.empty(value);
            }
        };
    }

    public static <T> AnyList<T> wrap(PrependedList<T>.InitializationToken prepended) {
        PrependedList<T> value = prepended.instance();
        return new AnyList<T>() {
            @Override
            public <R> R accept(Visitor<T, R> visitor) {
                return visitor.prepended(value);
            }
        };
    }

    private AnyList() {
    }

    public abstract <R> R accept(Visitor<T, R> visitor);

    @Override
    public AnyList<T> toAny() {
        return this;
    }

    public <R> AnyList<R> map(Function<T, R> f) {
        return accept(new Visitor<T, AnyList<R>>() {
            @Override
            public AnyList<R> empty(EmptyList<T> empty) {
                return empty.map(f).toAny();
            }

            @Override
            public AnyList<R> prepended(PrependedList<T> prepended) {
                return prepended.map(f).toAny();
            }
        });
    }
    public <R> AnyList<R> flatMap(Function<T, List<R>> f) {
        return accept(new Visitor<T, AnyList<R>>() {
            @Override
            public AnyList<R> empty(EmptyList<T> empty) {
                return empty.flatMap(f).toAny();
            }

            @Override
            public AnyList<R> prepended(PrependedList<T> prepended) {
                return prepended.flatMap(f).toAny();
            }
        });
    }
    public AnyList<T> append(List<T> list) {
        return accept(new Visitor<T, AnyList<T>>() {
            @Override
            public AnyList<T> empty(EmptyList<T> empty) {
                return list.toAny();
            }

            @Override
            public AnyList<T> prepended(PrependedList<T> prepended) {
                return new PrependedList<>(prepended.head(), prepended.tail().append(list)).toAny();
            }
        });
    }

    @Override
    public String toString() {
        return accept(new Visitor<T, String>() {

            @Override
            public String empty(EmptyList<T> empty) {
                return empty.toString();
            }

            @Override
            public String prepended(PrependedList<T> prepended) {
                return prepended.toString();
            }
        });
    }


    public interface Visitor<T, R> {
        R empty(EmptyList<T> empty);
        R prepended(PrependedList<T> prepended);
    }
}
