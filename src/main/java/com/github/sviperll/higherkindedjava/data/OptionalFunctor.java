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
public class OptionalFunctor<TT extends Type.Token> implements com.github.sviperll.higherkindedjava.Functor<TT> {
    public static final OptionalFunctor<?> INSTANCE = new OptionalFunctor<>(Optional.type);
    private final Optional.Type<TT> type;
    private OptionalFunctor(Optional.Type<TT> type) {
        this.type = type;
    }

    public Optional.Type<TT> type() {
        return type;
    }
    @Override
    public <T, R> Type.App<TT, R> map(Type.App<TT, T> self, Function<T, R> f) {
        return type.toTypeApp(type.toOptional(self).map(f));
    }
}
