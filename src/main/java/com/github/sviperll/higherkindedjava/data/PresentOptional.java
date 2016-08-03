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
public class PresentOptional<T> implements Optional<T> {
    private AnyOptional<T> any = null;
    private final T value;
    public PresentOptional(T value) {
        this.value = value;
    }

    public T value() {
        return value;
    }

    public <R> PresentOptional<R> map(Function<T, R> f) {
        return new PresentOptional<>(f.apply(value));
    }
    public <R> AnyOptional<R> flatMap(Function<T, Optional<R>> f) {
        return f.apply(value).toAny();
    }

    @Override
    public AnyOptional<T> toAny() {
        if (any == null)
            any = AnyOptional.wrap(new InitializationToken());
        return any;
    }

    @Override
    public String toString() {
        return "PresentOptional{" + "value=" + value + '}';
    }

    class InitializationToken {
        private InitializationToken() {
        }
        PresentOptional<T> instance() {
            return PresentOptional.this;
        }
    }

}
