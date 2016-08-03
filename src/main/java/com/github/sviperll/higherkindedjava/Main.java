/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sviperll.higherkindedjava;

import com.github.sviperll.higherkindedjava.data.AnyList;
import com.github.sviperll.higherkindedjava.data.AnyOptional;
import com.github.sviperll.higherkindedjava.data.ListFunctor;
import com.github.sviperll.higherkindedjava.data.OptionalFunctor;
import com.github.sviperll.higherkindedjava.data.List;
import com.github.sviperll.higherkindedjava.data.Optional;

/**
 *
 * @author vir
 */
public class Main {
    // Generic code
    static <TT extends Type.Token> Type.App<TT, Integer> transform(Functor<TT> op, Type.App<TT, Integer> functor) {
        return op.map(functor, i -> i + 1);
    }

    // Type cast boilerplate
    static <TT extends Type.Token> AnyList<Integer> processLists(ListFunctor<TT> op) {
        Type.App<TT, Integer> functor = op.type().toTypeApp(List.prepend(1, List.empty()));
        Type.App<TT, Integer> resultFunctor = transform(op, functor);
        return op.type().toList(resultFunctor);
    }

    // Type cast boilerplate
    static <TT extends Type.Token> AnyOptional<Integer> processOptionals(OptionalFunctor<TT> op) {
        Type.App<TT, Integer> functor = op.type().toTypeApp(Optional.present(1));
        Type.App<TT, Integer> resultFunctor = transform(op, functor);
        return op.type().toOptional(resultFunctor);
    }

    public static void main(String[] args) {
        System.out.println(processLists(ListFunctor.INSTANCE));
        System.out.println(processOptionals(OptionalFunctor.INSTANCE));
    }
}
