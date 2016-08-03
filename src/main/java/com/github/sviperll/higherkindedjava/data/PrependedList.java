/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sviperll.higherkindedjava.data;

import java.util.function.Function;

/**
 *
 * @author vir
 */
public class PrependedList<T> implements List<T> {

    private AnyList<T> any = null;
    private final T head;
    private final AnyList<T> tail;

    public PrependedList(T head, List<T> tail) {
        this.head = head;
        this.tail = tail.toAny();
    }

    public <R> PrependedList<R> map(Function<T, R> f) {
        return new PrependedList<>(f.apply(head), tail.map(f));
    }
    public <R> AnyList<R> flatMap(Function<T, List<R>> f) {
        return f.apply(head).toAny().append(tail.flatMap(f));
    }

    public T head() {
        return head;
    }

    public AnyList<T> tail() {
        return tail;
    }

    @Override
    public AnyList<T> toAny() {
        if (any == null)
            any = AnyList.wrap(new InitializationToken());
        return any;
    }

    @Override
    public String toString() {
        return "PrependedList{" + "head=" + head + ", tail=" + tail + '}';
    }

    class InitializationToken {
        private InitializationToken() {
        }
        public PrependedList<T> instance() {
            return PrependedList.this;
        }
    }
}
