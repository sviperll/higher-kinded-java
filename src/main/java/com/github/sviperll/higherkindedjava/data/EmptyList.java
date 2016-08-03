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
public class EmptyList<T> implements List<T> {
    private AnyList<T> any = null;

    public EmptyList() {
    }

    public <R> EmptyList<R> map(Function<T, R> f) {
        return new EmptyList<>();
    }
    public <R> EmptyList<R> flatMap(Function<T, List<R>> f) {
        return new EmptyList<>();
    }

    @Override
    public AnyList<T> toAny() {
        if (any == null) {
            any = AnyList.wrap(new InitializationToken());
        }
        return any;
    }

    @Override
    public String toString() {
        return "EmptyList{" + '}';
    }

    class InitializationToken {
        private InitializationToken() {
        }
        public EmptyList<T> instance() {
            return EmptyList.this;
        }
    }
}
