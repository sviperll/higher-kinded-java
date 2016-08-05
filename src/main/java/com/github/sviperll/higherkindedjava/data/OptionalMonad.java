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
    public static final OptionalMonad<?> INSTANCE = new OptionalMonad<>(Optional.TypeConstructor.get);
    private final Optional.TypeConstructor.Is<TT> proof;
    public OptionalMonad(Optional.TypeConstructor.Is<TT> type) {
        this.proof = type;
    }

    public Optional.TypeConstructor.Is<TT> proveWhatTypeConstructorIs() {
        return proof;
    }
    @Override
    public <T, R> Type.App<TT, R> map(Type.App<TT, T> self, Function<T, R> f) {
        return proof.convertToTypeApp(proof.convertToOptional(self).map(f));
    }

    @Override
    public <T> Type.App<TT, T> unit(T value) {
        return proof.convertToTypeApp(Optional.present(value));
    }

    @Override
    public <T, R> Type.App<TT, R> flatMap(Type.App<TT, T> self, Function<T, Type.App<TT, R>> f) {
        return proof.convertToTypeApp(proof.convertToOptional(self).flatMap(value -> proof.convertToOptional(f.apply(value))));
    }
}
