package com.hinsteny.function;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;


/**
 * PracticeFunction.java
 * 
 * @author: Hinsteny
 * @date: 2015年10月9日
 * @copyright: 2015 All rights reserved.
 * 
 */
public class PracticeFunction {
	
	@Test
	public void testMyFunction(){
		HFunctional<Integer> adds = (x,y) -> x + y;
		HFunctional<Integer> minus = (x,y) -> x - y;
		System.out.println("TestMyFunction (adds) => 1 + 2 = " + adds.apply(1,2));
		System.out.println("TestMyFunction (minus) => 1 - 2 = " + minus.apply(1,2));
		System.out.println("TestMyFunction (adds,minus) => (8 - 2) + 3 = " + adds.compose(minus,3).apply(8,2));
		System.out.println("TestMyFunction (adds,minus) => (8 + 2) - 3 = " + minus.compose(adds,3).apply(8,2));
		System.out.println("TestMyFunction (adds,minus) => (3 + 2) - 1 = " + adds.andThen(minus,1).apply(3,2));
		System.out.println("TestMyFunction (adds,minus) => (3 - 1) + 3 = " + minus.andThen(adds,3).apply(3,1));
	}

	@Test
    public void testConsumer(){
	    Consumer<Integer> consumer = System.out::println;
	    consumer.accept(1);
	    System.out.println("==========");
	    Consumer<Integer> consumer1 = t -> {
	        System.out.println("Do something!");
	    };
	    consumer.andThen(consumer1).accept(2);
    }
	
	@Test
	public void testPredicate(){
	    Predicate<Integer> predicate = t -> { return t >= 0 ? true : false;};
	    System.out.println("judge if t>=0: "+predicate.test(0));
	    Predicate<Integer> predicate1 = t -> { return t % 2 == 0 ? true : false;};
	    System.out.println("return the negate result:: "+predicate.negate().test(3));
	    System.out.println("judge if predicate && predicate1 is true: "+predicate.and(predicate1).test(3));
	    System.out.println("judge if predicate || predicate1 is true: "+predicate.or(predicate1).test(1));
	    System.out.println("judge if t>=0 and 2.equal(2): "+Predicate.isEqual(2).test(2));
	    System.out.println("judge if t>=0 and 2.equal(1): "+Predicate.isEqual(2).test(1));
	}
	
	@Test
	public void testSupplier(){
	    Supplier<Integer> supplier = () -> { return new Integer(-1);};
	    Supplier<Integer> supplier1 = () -> { return new Integer(1);};
	    System.out.println("Supplier begin");
	    System.out.println(supplier.get() +":"+ supplier1.get());
	}
	
	@Test
	public void testBiFunction(){
		BiFunction<Integer,Double,Double> binaryOperator = (a, b) -> { return a + b;};
	    System.out.println("BinaryOperator begin");
	    System.out.println(binaryOperator.apply(1, 2.1));
	}
	
	@Test
	public void testBinaryOperator(){
	    BinaryOperator<Integer> binaryOperator = (a, b) -> { return a + b;};
	    System.out.println("BinaryOperator begin");
	    System.out.println(binaryOperator.apply(1, 2));
	}
	
	@Test
	public void testTwoParams(){
	    BiFunction<Integer, String, Boolean> biFunction = (a, b) -> { return a + Integer.valueOf(b) > 0 ? true : false;};
	    System.out.println("BiFunction begin");
	    System.out.println(biFunction.apply(-1, "2"));
	    
	    BiConsumer<Integer, String> biConsumer = (a, b) -> {System.out.println(a + b);};
	    BiConsumer<Integer, String> biConsumer1 = (a, b) -> {System.out.println(a - Integer.parseInt(b));};
        System.out.println("BiConsumer begin");
        biConsumer.andThen(biConsumer1).accept(1, "2");

        BiPredicate<Integer, String> biPredicate = (a, b) -> {return a < Integer.valueOf(b) ? true : false;};
        System.out.println("BiPredicate begin");
        System.out.println(biPredicate.test(1, "2"));
	}
}
