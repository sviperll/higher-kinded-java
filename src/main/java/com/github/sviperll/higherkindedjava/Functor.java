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
 * @param <TT>
 * @param <T>
 */
public interface Functor<TT extends Type.Constructor> {
    <T, R> Type.App<TT, R> map(Type.App<TT, T> self, Function<T, R> f);
}
