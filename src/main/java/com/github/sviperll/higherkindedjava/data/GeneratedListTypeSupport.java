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
