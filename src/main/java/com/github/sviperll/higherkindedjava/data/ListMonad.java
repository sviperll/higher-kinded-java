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
public class ListMonad<TT extends Type.UniqueToken> implements com.github.sviperll.higherkindedjava.Monad<TT> {
    public static final ListMonad<?> INSTANCE = new ListMonad<>(List.type);
    private final List.Type<TT> type;
    private ListMonad(List.Type<TT> type) {
        this.type = type;
    }
    public List.Type<TT> type() {
        return type;
    }

    @Override
    public <T, R> Type.App<TT, R> map(Type.App<TT, T> self, Function<T, R> f) {
        return type.toTypeApp(type.toList(self).map(f));
    }

    @Override
    public <T> Type.App<TT, T> unit(T value) {
        return type.toTypeApp(List.unit(value));
    }

    @Override
    public <T, R> Type.App<TT, R> flatMap(Type.App<TT, T> self, Function<T, Type.App<TT, R>> f) {
        return type.toTypeApp(type.toList(self).flatMap(e -> type.toList(f.apply(e))));
    }
}
