/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sviperll.higherkindedjava.data;

import com.github.sviperll.higherkindedjava.GenerateTypeConstructor;

/**
 *
 * @author vir
 */
@GenerateTypeConstructor
public interface Optional<T> {
    public static <T> EmptyOptional<T> empty() {
        return new EmptyOptional<T>();
    }
    public static <T> PresentOptional<T> unit(T value) {
        return new PresentOptional<>(value);
    }
    public static <T> PresentOptional<T> present(T value) {
        return new PresentOptional<>(value);
    }
    public static <T> AnyOptional<T> join(Optional<? extends Optional<T>> values) {
        return values.toAny().flatMap(list -> list);
    }

    AnyOptional<T> toAny();
}
