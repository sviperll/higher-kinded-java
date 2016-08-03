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
public class OptionalMonad<TT extends Type.UniqueToken> implements com.github.sviperll.higherkindedjava.Monad<TT> {
    public static final OptionalMonad<?> INSTANCE = new OptionalMonad<>(Optional.type);
    private final Optional.Type<TT> type;
    private OptionalMonad(Optional.Type<TT> type) {
        this.type = type;
    }

    public Optional.Type<TT> type() {
        return type;
    }
    @Override
    public <T, R> Type.App<TT, R> map(Type.App<TT, T> self, Function<T, R> f) {
        return type.toTypeApp(type.toOptional(self).map(f));
    }

    @Override
    public <T> Type.App<TT, T> unit(T value) {
        return type.toTypeApp(Optional.present(value));
    }

    @Override
    public <T, R> Type.App<TT, R> flatMap(Type.App<TT, T> self, Function<T, Type.App<TT, R>> f) {
        return type.toTypeApp(type.toOptional(self).flatMap(value -> type.toOptional(f.apply(value))));
    }
}
