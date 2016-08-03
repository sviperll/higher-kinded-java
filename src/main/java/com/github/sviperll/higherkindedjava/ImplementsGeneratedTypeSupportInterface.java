/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.sviperll.higherkindedjava;

/**
 *
 * @author Victor Nazarov &lt;asviraspossible@gmail.com&gt;
 */
public @interface ImplementsGeneratedTypeSupportInterface {
    String generatedInterfaceName() default ":auto";
    String conversionToConcreteTypeMethod() default ":none";
}
