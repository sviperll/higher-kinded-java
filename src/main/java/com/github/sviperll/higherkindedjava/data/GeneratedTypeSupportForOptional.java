/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sviperll.higherkindedjava.data;

import com.github.sviperll.higherkindedjava.Type;
import com.github.sviperll.higherkindedjava.Type.App;
import com.github.sviperll.higherkindedjava.Type.Token;

/**
 *
 * @author vir
 */
public interface GeneratedTypeSupportForOptional<T> extends Type.App<Token, T> {
    public static Type<?> type = new Type<>();

    public static class Type<TT extends Token> {
        private Type() {
        }
        public <T> App<TT, T> toTypeApp(Optional<T> value) {
            return (App<TT, T>)value.toAny();
        }
        public <T> AnyOptional<T> toOptional(App<TT, T> value) {
            return (AnyOptional<T>)value;
        }
    }

}
