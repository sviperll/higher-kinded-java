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
public class OptionalMonad<TT extends Type.Constructor> implements com.github.sviperll.higherkindedjava.Monad<TT> {
    public static final OptionalMonad<?> INSTANCE = new OptionalMonad<>(OptionalTypeConstructor.get);
    private final OptionalTypeConstructor.Is<TT> tyConstrKnowledge;
    public OptionalMonad(OptionalTypeConstructor.Is<TT> tyConstrKnowledge) {
        this.tyConstrKnowledge = tyConstrKnowledge;
    }

    public OptionalTypeConstructor.Is<TT> typeConstructorKnowledge() {
        return tyConstrKnowledge;
    }
    @Override
    public <T, R> Type.App<TT, R> map(Type.App<TT, T> self, Function<T, R> f) {
        return tyConstrKnowledge.convertToTypeApp(tyConstrKnowledge.convertToOptional(self).toAny().map(f));
    }

    @Override
    public <T> Type.App<TT, T> unit(T value) {
        return tyConstrKnowledge.convertToTypeApp(Optional.present(value));
    }

    @Override
    public <T, R> Type.App<TT, R> flatMap(Type.App<TT, T> self, Function<T, Type.App<TT, R>> f) {
        return tyConstrKnowledge.convertToTypeApp(tyConstrKnowledge.convertToOptional(self).toAny().flatMap(value -> tyConstrKnowledge.convertToOptional(f.apply(value))));
    }
}
