/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sviperll.higherkindedjava;

import com.github.sviperll.higherkindedjava.data.AnyList;
import com.github.sviperll.higherkindedjava.data.AnyOptional;
import com.github.sviperll.higherkindedjava.data.List;
import com.github.sviperll.higherkindedjava.data.Optional;
import java.util.Objects;
import java.util.function.Function;

/**
 *
 * @author vir
 */
public interface Monad<TT extends Type.Token> extends Functor<TT> {
    <T> Type.App<TT, T> unit(T value);

    <T> Type.App<TT, T> join(Type.App<TT, Type.App<TT, T>> values);

    <T, R> Type.App<TT, R> flatMap(Type.App<TT, T> self, Function<T, Type.App<TT, R>> f);
}
