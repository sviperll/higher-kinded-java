/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sviperll.higherkindedjava.data;

import com.github.sviperll.higherkindedjava.Type;
import java.util.function.Function;

/**
 *
 * @author vir
 */
public class ListMonad<TT extends Type.Constructor> implements com.github.sviperll.higherkindedjava.Monad<TT> {
    public static final ListMonad<?> INSTANCE = new ListMonad<>(ListTypeConstructor.GET);
    private final ListTypeConstructor.Is<TT> tyConstrKnowledge;
    public ListMonad(ListTypeConstructor.Is<TT> tyConstrKnowledge) {
        this.tyConstrKnowledge = tyConstrKnowledge;
    }
    public ListTypeConstructor.Is<TT> typeConstructorKnowledge() {
        return tyConstrKnowledge;
    }

    @Override
    public <T, R> Type.App<TT, R> map(Type.App<TT, T> self, Function<T, R> f) {
        return tyConstrKnowledge.convertToTypeApp(tyConstrKnowledge.convertToList(self).toAny().map(f));
    }

    @Override
    public <T> Type.App<TT, T> unit(T value) {
        return tyConstrKnowledge.convertToTypeApp(List.unit(value));
    }

    @Override
    public <T, R> Type.App<TT, R> flatMap(Type.App<TT, T> self, Function<T, Type.App<TT, R>> f) {
        return tyConstrKnowledge.convertToTypeApp(tyConstrKnowledge.convertToList(self).toAny().flatMap(e -> tyConstrKnowledge.convertToList(f.apply(e))));
    }
}
