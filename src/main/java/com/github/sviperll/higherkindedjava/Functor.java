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
import java.util.function.Function;

/**
 *
 * @author vir
 * @param <TT>
 * @param <T>
 */
public interface Functor<TT extends Type.Token> {
    <T, R> Type.App<TT, R> map(Type.App<TT, T> self, Function<T, R> f);
}
