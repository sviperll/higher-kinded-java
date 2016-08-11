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
    public static abstract class App<TT extends Constructor, T> {
        protected App() {
            pleaseDoNotOverrideMeItIsUnsafe();
        }
        /**
         * Please, do not manually implement.
         *
         * Do not implement com.github.sviperll.higherkindedjava.Type.App interface.
         * Manual implementation of com.github.sviperll.higherkindedjava.Type.App interface
         * is a high risk of circumventing type safety guarantees provided by usage of this interface.
         */
        protected abstract void pleaseDoNotImplementMeItIsUnsafe();

        protected void pleaseDoNotOverrideMeItIsUnsafe() {
            throw new AssertionError("Attempt to manually implement com.github.sviperll.higherkindedjava.Type.App interface. Please don't do this. It is unsafe!");
        }
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
        public static <TT extends Constructor, T, U> Eq<T, U> fromTypeApp(Eq<Type.App<TT, T>, Type.App<TT, U>> equality) {
            return INSTANCE;
        }

        private Eq() {
        }

        @SuppressWarnings("unchecked")
        public T cast(U type) {
            return (T)type;
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
        public <TT extends Constructor> Eq<Type.App<TT, T>, Type.App<TT, U>> toTypeApp() {
            return INSTANCE;
        }
    }
    public static class Constructor {
        private Constructor() {
            throw new IllegalStateException("Shouldn't be instantiated");
        }
    }
}
