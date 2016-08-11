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
 * @param <T>
 */
@GenerateTypeConstructor
public interface List<T> {
    public static <T> PrependedList<T> unit(T value) {
        return new PrependedList<>(value, empty());
    }
    public static <T> PrependedList<T> prepend(T head, List<T> tail) {
        return new PrependedList<>(head, tail);
    }
    public static <T> EmptyList<T> empty() {
        return new EmptyList<>();
    }
    public static <T> AnyList<T> join(List<? extends List<T>> values) {
        return values.toAny().flatMap(list -> list);
    }

    AnyList<T> toAny();
}
