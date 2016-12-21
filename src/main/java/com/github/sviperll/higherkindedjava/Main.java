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
import com.github.sviperll.higherkindedjava.data.ListTypeConstructor;
import com.github.sviperll.higherkindedjava.data.Optional;
import com.github.sviperll.higherkindedjava.data.OptionalTypeConstructor;
import java.util.function.Function;

/**
 *
 * @author vir
 */
public class Main {
    // Monad generic code
    static <M extends Type.Constructor> Type.App<M, Integer> transformMonad(Monad<M> monad, Type.App<M, Integer> value) {
        Type.App<M, Integer> result = monad.flatMap(value, i -> monad.unit(5));
        return transformFunctor(monad, result);
    }

    // Functor generic code
    static <F extends Type.Constructor> Type.App<F, Integer> transformFunctor(Functor<F> functor, Type.App<F, Integer> value) {
        return functor.map(value, i -> i + 1);
    }

    // Using specific instance
    static <L extends Type.Constructor> AnyList<Integer> processLists(ListTypeConstructor.Is<L> proof) {
        Type.App<L, Integer> value = proof.convertToTypeApp(List.prepend(1, List.prepend(2, List.empty())));
        Type.App<L, Integer> result = transformMonad(new ListMonad<>(proof), value);
        return proof.convertToList(result).toAny();
    }

    // Using specific instance
    static <O extends Type.Constructor> AnyOptional<Integer> processOptionals(OptionalTypeConstructor.Is<O> proof) {
        Type.App<O, Integer> value = proof.convertToTypeApp(Optional.present(1));
        // Type.App<TT, Integer> value = Optional.present(1); // Compile-time error
        // Type.App<TT, Integer> value = List.prepend(1, List.empty())); // Compile-time error
        // Type.App<TT, Integer> value = proof.convertToTypeApp(List.prepend(1, List.empty())); // Compile-time error

        Type.App<O, Integer> result = transformMonad(new OptionalMonad<>(proof), value);
        return proof.convertToOptional(result).toAny();
    }

    public static void main(String[] args) {
        System.out.println(processLists(ListTypeConstructor.GET));
        System.out.println(processOptionals(OptionalTypeConstructor.GET));

        GADT<Integer> zero = GADT.zero();
        GADT<Integer> one = GADT.succ(zero);
        GADT<Function<Integer, Integer>> f1 = GADT.lam(n -> zero);
        GADT<Function<Integer, Integer>> f2 = GADT.lam(n -> one);
        GADT<Integer> exp = GADT.app(GADT.ifClause(GADT.isZero(one), f1, f2), one);
        System.out.println(exp + " = " + exp.eval());
    }
}
