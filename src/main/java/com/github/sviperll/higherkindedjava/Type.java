/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sviperll.higherkindedjava;

/**
 *
 * @author vir
 */
public class Type {
    private Type() {
    }

    /**
     * Type application.
     *
     * Please, do not manually implement.
     *
     * Do not implement com.github.sviperll.higherkindedjava.Type.App interface.
     * Manual implementation of com.github.sviperll.higherkindedjava.Type.App interface
     * is a high risk of circumventing type safety guarantees provided by usage of this interface.
     */
    public interface App<TT extends UniqueToken, T> {
        /**
         * Please, do not manually implement.
         *
         * Do not implement com.github.sviperll.higherkindedjava.Type.App interface.
         * Manual implementation of com.github.sviperll.higherkindedjava.Type.App interface
         * is a high risk of circumventing type safety guarantees provided by usage of this interface.
         */
        void pleaseDoNotImplementMeItIsUnsafe();
    }

    /**
     * Reified type-equality
     */
    public static class Eq<T, U> {
        @SuppressWarnings("rawtyopes")
        private static final Eq INSTANCE = new Eq();

        @SuppressWarnings("unchecked")
        public static <T> Eq<T, T> obvious() {
            return INSTANCE;
        }

        @SuppressWarnings("unchecked")
        public static <TT extends UniqueToken, T, U> Eq<T, U> fromTypeApp(Eq<Type.App<TT, T>, Type.App<TT, U>> equality) {
            return INSTANCE;
        }

        private Eq() {
        }

        public U cast(T type) {
            return (U)type;
        }

        @SuppressWarnings("unchecked")
        public Eq<U, T> reverse() {
            return INSTANCE;
        }

        @SuppressWarnings("unchecked")
        public <V> Eq<T, V> merge(Eq<U, V> equality) {
            return INSTANCE;
        }

        @SuppressWarnings("unchecked")
        public <TT extends UniqueToken> Eq<Type.App<TT, T>, Type.App<TT, U>> toTypeApp() {
            return INSTANCE;
        }
    }
    public static class UniqueToken {
        private UniqueToken() {
        }
    }
}
