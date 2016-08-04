/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sviperll.higherkindedjava.data;

import com.github.sviperll.higherkindedjava.Type.App;
import com.github.sviperll.higherkindedjava.Type.UniqueToken;
import javax.annotation.Generated;

/**
 *
 * @author vir
 */
@Generated("") // Should be actually generated and not exposed to user
public interface GeneratedListTypeSupport<T> extends App<UniqueToken, T> {
    // This is the ultimate solution to all our problems
    // WILDCARD!!!
    //
    // This is the only instantiation of List.Type class
    // You can't instantiate it yourself since constructor is private.
    //
    // This instance's type contains wildcard.
    //
    // The only way to use type-information is to capture wildcard
    // thus effectively obtaining a unique type-level token.
    //
    // This type-level token can be used to prove that type-casts are safe
    //
    // Rawtypes, manual instantiation of Type.App interface and plain old casts
    // can all circumvernt type-safety and cause ClassCastException
    // But all this features are expected to be inherently unsafe.
    //
    // Manual instantiation of Type.App interface can be made visibly unsafe
    // we can provide add method like
    //
    //     void pleaseDoNotImplementMeItIsUnsafe();
    //
    // to make it obviously and visibly unsafe.
    public static Type<?> type = new Type<>();

    default void pleaseDoNotImplementMeItIsUnsafe() {
        // I'm a wise code generator.
        // I know what I'm doing
    }

    public static class Type<TT extends UniqueToken> {
        private Type() {
        }
        public <T> App<TT, T> toTypeApp(List<T> value) {
            // It is safe since List implements App
            // Besides, all UniqueTokens has the same runtime representation
            // they all are instances of UniqueToken class (you can't extend this class)
            return (App<TT, T>)value.toAny();
        }
        public <T> AnyList<T> toList(App<TT, T> value) {
            // It is safe since the only way to get App for given UniqueToken is buy using toTypeApp method above
            return (AnyList<T>)value;
        }
    }

}
