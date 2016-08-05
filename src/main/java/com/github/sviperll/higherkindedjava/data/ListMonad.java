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
    public static final ListMonad<?> INSTANCE = new ListMonad<>(List.TypeConstructor.get);
    private final List.TypeConstructor.Is<TT> proof;
    public ListMonad(List.TypeConstructor.Is<TT> type) {
        this.proof = type;
    }
    public List.TypeConstructor.Is<TT> proveWhatTypeConstructorIs() {
        return proof;
    }

    @Override
    public <T, R> Type.App<TT, R> map(Type.App<TT, T> self, Function<T, R> f) {
        return proof.convertToTypeApp(proof.convertToList(self).map(f));
    }

    @Override
    public <T> Type.App<TT, T> unit(T value) {
        return proof.convertToTypeApp(List.unit(value));
    }

    @Override
    public <T, R> Type.App<TT, R> flatMap(Type.App<TT, T> self, Function<T, Type.App<TT, R>> f) {
        return proof.convertToTypeApp(proof.convertToList(self).flatMap(e -> proof.convertToList(f.apply(e))));
    }
}
