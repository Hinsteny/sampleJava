package com.hinsteny;

import java.util.Optional;
import java.util.function.Predicate;

import org.junit.Test;


/**
 * PracticeOptional.java
 * 
 * @author: Hinsteny
 * @date: 2015年10月9日
 * @copyright: 2015 All rights reserved.
 * 
 */
public class PracticeOptional {
	
	@Test
    public void OptionalTests() {
        
        Optional<String> optional = Optional.of("optionalTest");
        Optional<String> optional2 = Optional.of("optionalTest2");
        Optional<String> optional3 = Optional.of("optionalTest");
        
        //ofNullable
        Optional empty = Optional.ofNullable(null);
        
        //isPresent/ifPresent
        System.out.println("optional isPresent: " + optional.isPresent());
        System.out.println("optional isPresent: " + empty.isPresent());
        
        System.out.print("optional ifPresent: ");
        optional.ifPresent((s) -> System.out.println(s.substring(0, 8)));
        System.out.println("");
        
        //get
        System.out.println("optional get: " + optional.get());
        System.out.println("");
        
        //orElse/orElseGet()
        System.out.println("optional orElse: " + optional.orElse("fallback"));
        System.out.println("optional orElse: " + empty.orElse("fallback"));
        System.out.println("optional orElseGet: " + optional.orElseGet(() -> optional2.get()));
        System.out.println("optional orElseGet: " + empty.orElseGet(() -> optional2.get()));
        System.out.println("");
        
        //equals
        System.out.println("optional equals: " + optional.equals(optional));
        System.out.println("optional equals: " + optional.equals(optional3));
        System.out.println("optional equals: " + optional.equals("optionalTest"));
        System.out.println("optional equals: " + optional.equals(optional2));
        System.out.println("");
        
        //filter
        Predicate<String> predicate = (s) -> s.length() > 0;
        Predicate<String> predicate2 = (s) -> s.length() < 0;
        System.out.println("optional filter: " + optional.filter(predicate));
        System.out.println("optional filter: " + optional.filter(predicate2));
        System.out.println("");
        
        //Map/flatMap
        Optional<String> upperName = optional.map((value) -> value.toUpperCase());
        System.out.println("optional map: " + upperName.orElse("No value found"));
        Optional<String> upperName2 = optional.flatMap((value) -> Optional.of(value.toUpperCase()));
        System.out.println("optional flatMap: " + upperName2.orElse("No value found"));
        System.out.println("");
        
        //hashCode
        System.out.println("optional hashCode: " + empty.hashCode());
        System.out.println("optional hashCode: " + optional.hashCode());
        System.out.println("");
    }
}
