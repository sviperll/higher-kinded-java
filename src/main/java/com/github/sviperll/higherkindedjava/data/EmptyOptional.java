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
 * @param <T>
 */
public class EmptyOptional<T> implements Optional<T> {
    private AnyOptional<T> any = null;

    public <R> EmptyOptional<R> map(Function<T, R> f) {
        return new EmptyOptional<>();
    }
    public <R> EmptyOptional<R> flatMap(Function<T, Optional<R>> f) {
        return new EmptyOptional<>();
    }

    @Override
    public AnyOptional<T> toAny() {
        if (any == null)
            any = AnyOptional.wrap(new InitializationToken());
        return any;
    }

    @Override
    public String toString() {
        return "EmptyOptional{" + '}';
    }

    class InitializationToken {
        private InitializationToken() {
        }
        EmptyOptional<T> instance() {
            return EmptyOptional.this;
        }
    }
}
