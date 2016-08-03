/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sviperll.higherkindedjava.data;

/**
 *
 * @author vir
 */
// @GenerateTypeSupport(GeneratedTypeSupportForOptional.class)
// @GenerateDictionaryImplementation(
//    dictionary=Functor.class,
//    generate=OptionalFunctor.class)
public interface Optional<T>
        extends GeneratedTypeSupportForOptional<T> {
    public static <T> EmptyOptional<T> empty() {
        return new EmptyOptional<T>();
    }
    public static <T> PresentOptional<T> unit(T value) {
        return new PresentOptional<>(value);
    }
    public static <T> PresentOptional<T> present(T value) {
        return new PresentOptional<>(value);
    }

    AnyOptional<T> toAny();
}
