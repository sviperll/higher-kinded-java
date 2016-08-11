/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.sviperll.higherkindedjava;

import java.util.function.Function;

/**
 *
 * @author Victor Nazarov &lt;asviraspossible@gmail.com&gt;
 */
public interface GADTVisitor<T, R> {
    R zero(Type.Eq<T, Integer> tArgKnowledge);
    R succ(GADT<Integer> value, Type.Eq<T, Integer> tArgKnowledge);
    R isZero(GADT<Integer> value, Type.Eq<T, Boolean> tArgKnowledge);
    R ifClause(GADT<Boolean> condition, GADT<T> thenValue, GADT<T> elseValue);
    <U, W> R lam(Function<U, GADT<W>> function, Type.Eq<T, Function<U, W>> tArgKnowledge);
    <U> R app(GADT<Function<U, T>> function, GADT<U> argument);
}
