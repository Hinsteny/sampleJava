package com.hisoka.Annotation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Programmer.java
 * 
 * @author: Hinsteny
 * @date: 2015年10月9日
 * @copyright: 2015 All rights reserved.
 * 
 */
@Repeatable(Programmers.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Programmer {
	String name();
}
