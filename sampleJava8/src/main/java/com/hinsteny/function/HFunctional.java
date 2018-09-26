package com.hinsteny.function;

import java.util.Objects;


/**
 * HFunctional.java
 * 
 * @author: Hinsteny
 * @date: 2015年10月9日
 * @copyright: 2015 All rights reserved.
 * 
 */
public interface HFunctional <T>{
	
	T apply(T t1,T t2);
	
	default  HFunctional<T> compose(HFunctional<T> before, T t) {
        Objects.requireNonNull(before);
        return (T t1,T t2) -> apply(before.apply(t1, t2), t);
    }
	
	default <V> HFunctional<T> andThen(HFunctional<T> after,T t) {
        Objects.requireNonNull(after);
        return (T t1, T t2) -> after.apply(apply(t1,t2), t);
    }
}
