/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sviperll.higherkindedjava;

import com.github.sviperll.higherkindedjava.data.AnyList;
import com.github.sviperll.higherkindedjava.data.AnyOptional;
import com.github.sviperll.higherkindedjava.data.ListMonad;
import com.github.sviperll.higherkindedjava.data.OptionalMonad;
import com.github.sviperll.higherkindedjava.data.List;
import com.github.sviperll.higherkindedjava.data.Optional;

/**
 *
 * @author vir
 */
public class Main {
    // Monad generic code
    static <TT extends Type.UniqueToken> Type.App<TT, Integer> transformMonad(Monad<TT> monad, Type.App<TT, Integer> value) {
        Type.App<TT, Integer> result = monad.flatMap(value, i -> monad.unit(5));
        return transformFunctor(monad, result);
    }

    // Functor generic code
    static <TT extends Type.UniqueToken> Type.App<TT, Integer> transformFunctor(Functor<TT> functor, Type.App<TT, Integer> value) {
        return functor.map(value, i -> i + 1);
    }

    // Using specific instance
    static <TT extends Type.UniqueToken> AnyList<Integer> processLists(ListMonad<TT> monad) {
        Type.App<TT, Integer> value = monad.type().toTypeApp(List.prepend(1, List.empty()));
        Type.App<TT, Integer> result = transformMonad(monad, value);
        return monad.type().toList(result);
    }

    // Using specific instance
    static <TT extends Type.UniqueToken> AnyOptional<Integer> processOptionals(OptionalMonad<TT> monad) {
        Type.App<TT, Integer> value = monad.type().toTypeApp(Optional.present(1));
        // Type.App<TT, Integer> value = Optional.present(1); // Compile-time error
        // Type.App<TT, Integer> value = List.prepend(1, List.empty())); // Compile-time error
        // Type.App<TT, Integer> value = monad.type().toTypeApp(List.prepend(1, List.empty())); // Compile-time error

        Type.App<TT, Integer> result = transformMonad(monad, value);
        return monad.type().toOptional(result);
    }

    public static void main(String[] args) {
        System.out.println(processLists(ListMonad.INSTANCE));
        System.out.println(processOptionals(OptionalMonad.INSTANCE));
    }
}
