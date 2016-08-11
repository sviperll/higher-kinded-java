/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.sviperll.higherkindedjava;

import com.github.sviperll.writejava4me.GeneratesClass;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 *
 * @author Victor Nazarov &lt;asviraspossible@gmail.com&gt;
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
@Documented
@GeneratesClass(classNameTemplateString = "{{annotated}}TypeConstructor", classTemplateResourcePath = "com/github/sviperll/higherkindedjava/TypeConstructor.mustache")
public @interface GenerateTypeConstructor {

}
