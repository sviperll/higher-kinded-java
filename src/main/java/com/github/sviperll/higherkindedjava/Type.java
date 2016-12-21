/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sviperll.higherkindedjava;

import java.util.Objects;

/**
 *
 * @author vir
 */
public class Type {
    private Type() {
    }

    public static class App<TT extends Constructor, T> {

        private final Object value;
        private <V> App(V value) {
            this.value = value;
        }
    }

    public static class ConstructorIs<TC extends Constructor> {
        public static final Support<?> SUPPORT = new Support<>();
        private final Support<TC> support;
        public ConstructorIs(Support<TC> support) {
            Objects.requireNonNull(support);
            this.support = support;
        }
        protected <V, T> Type.App<TC, T> unsafeConvertToTypeApp(V value) {
            return support.<V, T>unsafeConvertToTypeApp(value);
        }
        protected <V, T> V unsafeConvertToValue(Type.App<TC, T> value) {
            return support.<V, T>unsafeConvertToValue(value);
        }
        public static class Support<TC extends Constructor> {
            private Support() {
            }
            private <V, T> Type.App<TC, T> unsafeConvertToTypeApp(V value) {
                return new Type.App<>(value);
            }
            @SuppressWarnings("unchecked")
            private <V, T> V unsafeConvertToValue(Type.App<TC, T> value) {
                return (V)value.value;
            }
        }
    }

    /**
     * Reified type-equality
     */
    public static class Eq<T, U> {
        @SuppressWarnings("rawtypes")
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
