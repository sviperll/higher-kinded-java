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
@GenerateTypeConstructor
public abstract class GADT<T> {
    @SuppressWarnings("rawtypes")
    private static final Factory FACTORY = new Factory();

    @SuppressWarnings("unchecked")
    public static <T> GADTVisitor<T, GADT<T>> factory() {
        return FACTORY;
    }

    public static <TC extends Type.Constructor, T, U> GADT<T> cast(GADTTypeConstructor.Is<TC> tyConstrKnowledge, Type.Eq<T, U> tyArgKnowledge, GADT<U> value) {
        return tyConstrKnowledge.convertToGADT(tyArgKnowledge.<TC>toTypeApp().cast(tyConstrKnowledge.convertToTypeApp(value)));
    }

    public static GADT<Integer> zero() {
        return GADT.<Integer>factory().zero(Type.Eq.obvious());
    }

    public static GADT<Integer> succ(GADT<Integer> value) {
        return GADT.<Integer>factory().succ(value, Type.Eq.obvious());
    }

    public static GADT<Boolean> isZero(GADT<Integer> value) {
        return GADT.<Boolean>factory().isZero(value, Type.Eq.obvious());
    }

    public static <T> GADT<T> ifClause(GADT<Boolean> condition, GADT<T> thenValue, GADT<T> elseValue) {
        return GADT.<T>factory().ifClause(condition, thenValue, elseValue);
    }

    public static <U, W> GADT<Function<U, W>> lam(Function<U, GADT<W>> function) {
        return GADT.<Function<U, W>>factory().lam(function, Type.Eq.obvious());
    }

    public static <T, U> GADT<T> app(GADT<Function<U, T>> function, GADT<U> argument) {
        return GADT.<T>factory().app(function, argument);
    }

    private GADT() {
    }

    abstract <R> R accept(GADTVisitor<T, R> visitor);

    public T eval() {
        return accept(new GADTVisitor<T, T>() {
            @Override
            public T zero(Type.Eq<T, Integer> tyArgKnowledge) {
                return tyArgKnowledge.cast(0);
            }

            @Override
            public T succ(GADT<Integer> value, Type.Eq<T, Integer> tyArgKnowledge) {
                return tyArgKnowledge.cast(value.eval() + 1);
            }

            @Override
            public T isZero(GADT<Integer> value, Type.Eq<T, Boolean> tyArgKnowledge) {
                return tyArgKnowledge.cast(value.eval() == 0);
            }

            @Override
            public T ifClause(GADT<Boolean> condition, GADT<T> thenValue, GADT<T> elseValue) {
                return condition.eval() ? thenValue.eval() : elseValue.eval();
            }

            @Override
            public <U, W> T lam(Function<U, GADT<W>> function, Type.Eq<T, Function<U, W>> tyArgKnowledge) {
                return tyArgKnowledge.cast((U argument) -> function.apply(argument).eval());
            }

            @Override
            public <U> T app(GADT<Function<U, T>> function, GADT<U> argument) {
                return function.eval().apply(argument.eval());
            }
        });
    };

    @Override
    public String toString() {
        return accept(new GADTVisitor<T, String>() {

            @Override
            public String zero(Type.Eq<T, Integer> tArgKnowledge) {
                return "0";
            }

            @Override
            public String succ(GADT<Integer> value, Type.Eq<T, Integer> tArgKnowledge) {
                return value.toString() + " + 1";
            }

            @Override
            public String isZero(GADT<Integer> value, Type.Eq<T, Boolean> tArgKnowledge) {
                return "isZero(" + value.toString() + ")";
            }

            @Override
            public String ifClause(GADT<Boolean> condition, GADT<T> thenValue, GADT<T> elseValue) {
                return "(if " + condition.toString() + " then " + thenValue.toString() + " else " + elseValue.toString() + ")";
            }

            @Override
            public <U, W> String lam(Function<U, GADT<W>> function, Type.Eq<T, Function<U, W>> tArgKnowledge) {
                return "<#function>";
            }

            @Override
            public <U> String app(GADT<Function<U, T>> function, GADT<U> argument) {
                return function.toString() + "(" + argument.toString() + ")";
            }
        });
    };

    public GADT<T> cloneAsGADT() {
        return cloneAsGADT(GADTTypeConstructor.get);
    }

    private <TC extends Type.Constructor> GADT<T> cloneAsGADT(GADTTypeConstructor.Is<TC> tyConstrKnowledge) {
        return accept(new GADTVisitor<T, GADT<T>>() {
            @Override
            public GADT<T> zero(Type.Eq<T, Integer> tyArgKnowledge) {
                return GADT.cast(tyConstrKnowledge, tyArgKnowledge, GADT.zero());
            }

            @Override
            public GADT<T> succ(GADT<Integer> value, Type.Eq<T, Integer> tyArgKnowledge) {
                return GADT.cast(tyConstrKnowledge, tyArgKnowledge, GADT.succ(value));
            }

            @Override
            public GADT<T> isZero(GADT<Integer> value, Type.Eq<T, Boolean> tyArgKnowledge) {
                return GADT.cast(tyConstrKnowledge, tyArgKnowledge, GADT.isZero(value));
            }

            @Override
            public GADT<T> ifClause(GADT<Boolean> condition, GADT<T> thenValue, GADT<T> elseValue) {
                return GADT.ifClause(condition, thenValue, elseValue);
            }

            @Override
            public <U, W> GADT<T> lam(Function<U, GADT<W>> function, Type.Eq<T, Function<U, W>> tyArgKnowledge) {
                return GADT.cast(tyConstrKnowledge, tyArgKnowledge, GADT.lam(function));
            }

            @Override
            public <U> GADT<T> app(GADT<Function<U, T>> function, GADT<U> argument) {
                return GADT.app(function, argument);
            }
        });
    }

    private static class Factory<T> implements GADTVisitor<T, GADT<T>> {

        Factory() {
        }

        @Override
        public GADT<T> zero(Type.Eq<T, Integer> typeArgIsInt) {
            return new GADT<T>() {
                @Override
                <R> R accept(GADTVisitor<T, R> visitor) {
                    return visitor.zero(typeArgIsInt);
                }
            };
        }

        @Override
        public GADT<T> succ(GADT<Integer> value, Type.Eq<T, Integer> typeArgIsInt) {
            return new GADT<T>() {
                @Override
                <R> R accept(GADTVisitor<T, R> visitor) {
                    return visitor.succ(value, typeArgIsInt);
                }
            };
        }

        @Override
        public GADT<T> isZero(GADT<Integer> value, Type.Eq<T, Boolean> typeArgIsBool) {
            return new GADT<T>() {
                @Override
                <R> R accept(GADTVisitor<T, R> visitor) {
                    return visitor.isZero(value, typeArgIsBool);
                }
            };
        }

        @Override
        public GADT<T> ifClause(GADT<Boolean> condition, GADT<T> thenValue, GADT<T> elseValue) {
            return new GADT<T>() {
                @Override
                <R> R accept(GADTVisitor<T, R> visitor) {
                    return visitor.ifClause(condition, thenValue, elseValue);
                }
            };
        }

        @Override
        public <U, W> GADT<T> lam(Function<U, GADT<W>> function, Type.Eq<T, Function<U, W>> typeArgIsFunction) {
            return new GADT<T>() {
                @Override
                <R> R accept(GADTVisitor<T, R> visitor) {
                    return visitor.lam(function, typeArgIsFunction);
                }
            };
        }

        @Override
        public <U> GADT<T> app(GADT<Function<U, T>> function, GADT<U> argument) {
            return new GADT<T>() {
                @Override
                <R> R accept(GADTVisitor<T, R> visitor) {
                    return visitor.app(function, argument);
                }
            };
        }
    }
}
