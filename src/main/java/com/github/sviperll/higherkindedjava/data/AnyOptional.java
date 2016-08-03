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
public abstract class AnyOptional<T> implements Optional<T> {
    static <T> AnyOptional<T> wrap(EmptyOptional<T>.InitializationToken token) {
        EmptyOptional<T> instance = token.instance();
        return new AnyOptional<T>() {

            @Override
            public <R> R accept(Visitor<T, R> visitor) {
                return visitor.empty(instance);
            }
        };
    }
    static <T> AnyOptional<T> wrap(PresentOptional<T>.InitializationToken token) {
        PresentOptional<T> instance = token.instance();
        return new AnyOptional<T>() {

            @Override
            public <R> R accept(Visitor<T, R> visitor) {
                return visitor.present(instance);
            }
        };
    }
    private AnyOptional() {
    }
    public abstract <R> R accept(Visitor<T, R> visitor);

    public <R> AnyOptional<R> map(Function<T, R> f) {
        return accept(new Visitor<T, AnyOptional<R>>() {

            @Override
            public AnyOptional<R> empty(EmptyOptional<T> empty) {
                return empty.map(f).toAny();
            }

            @Override
            public AnyOptional<R> present(PresentOptional<T> present) {
                return present.map(f).toAny();
            }
        });
    }
    public <R> AnyOptional<R> flatMap(Function<T, Optional<R>> f) {
        return accept(new Visitor<T, AnyOptional<R>>() {
            @Override
            public AnyOptional<R> empty(EmptyOptional<T> empty) {
                return empty.flatMap(f).toAny();
            }

            @Override
            public AnyOptional<R> present(PresentOptional<T> present) {
                return present.flatMap(f);
            }
        });
    }

    @Override
    public String toString() {
        return accept(new Visitor<T, String>() {

            @Override
            public String empty(EmptyOptional<T> empty) {
                return empty.toString();
            }

            @Override
            public String present(PresentOptional<T> present) {
                return present.toString();
            }
        });
    }

    @Override
    public AnyOptional<T> toAny() {
        return this;
    }

    public interface Visitor<T, R> {
        R empty(EmptyOptional<T> empty);
        R present(PresentOptional<T> present);
    }
}
