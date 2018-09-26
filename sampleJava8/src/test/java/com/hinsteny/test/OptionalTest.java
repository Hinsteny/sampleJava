package com.hinsteny.test;

import org.junit.Test;

import java.util.Optional;

/**
 * <p></p>
 */
public class OptionalTest {

	@Test
	public void simpleOptional(){
		Double r = inverse(4d).flatMap(OptionalTest::squareRoot).get();
		System.out.println(r);
	}

	public static Optional<Double> inverse(Double x){
		return x == 0 ? Optional.empty() : Optional.of(1/x);
	}

	public static Optional<Double> squareRoot(Double x) {
		return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
	}
}
