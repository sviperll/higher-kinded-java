/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sviperll.higherkindedjava.data;

import com.github.sviperll.higherkindedjava.Type;

/**
 *
 * @author vir
 */
// @GenerateTypeSupport(GeneratedTypeSupportForList.class)
// @GenerateDictionaryImplementation(
//    dictionary=Functor.class,
//    generate=ListFunctor.class)
public interface List<T> extends GeneratedTypeSupportForList<T> {
    public static <T> PrependedList<T> unit(T value) {
        return new PrependedList<>(value, empty());
    }
    public static <T> PrependedList<T> prepend(T head, List<T> tail) {
        return new PrependedList<>(head, tail);
    }
    public static <T> EmptyList<T> empty() {
        return new EmptyList<>();
    }

    AnyList<T> toAny();
}
