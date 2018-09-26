package com.hisoka.Annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Programmers.java
 * 
 * @author: Hinsteny
 * @date: 2015年10月9日
 * @copyright: 2015 All rights reserved.
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Programmers {
	Programmer[] value();
}
