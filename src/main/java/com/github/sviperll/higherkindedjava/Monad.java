/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sviperll.higherkindedjava;

import java.util.function.Function;

/**
 *
 * @author vir
 */
public interface Monad<TT extends Type.Constructor> extends Functor<TT> {
    <T> Type.App<TT, T> unit(T value);

    default <T> Type.App<TT, T> join(Type.App<TT, Type.App<TT, T>> values) {
        return flatMap(values, Function.identity());
    }

    default <T, R> Type.App<TT, R> flatMap(Type.App<TT, T> self, Function<T, Type.App<TT, R>> f) {
        return join(map(self, f));
    }
}
