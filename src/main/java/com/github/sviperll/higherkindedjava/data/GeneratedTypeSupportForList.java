/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sviperll.higherkindedjava.data;

import com.github.sviperll.higherkindedjava.Type.App;
import com.github.sviperll.higherkindedjava.Type.Token;

/**
 *
 * @author vir
 */
public interface GeneratedTypeSupportForList<T> extends App<Token, T> {
    public static Type<?> type = new Type<>();

    public static class Type<TT extends Token> {
        private Type() {
        }
        public <T> App<TT, T> toTypeApp(List<T> value) {
            return (App<TT, T>)value.toAny();
        }
        public <T> AnyList<T> toList(App<TT, T> value) {
            return (AnyList<T>)value;
        }
    }

}
