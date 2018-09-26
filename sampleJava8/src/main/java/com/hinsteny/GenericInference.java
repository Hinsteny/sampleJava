package com.hinsteny;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;


/**
 * GenericInference.java
 * 
 * @author: Hinsteny
 * @date: 2015年10月9日
 * @copyright: 2015 All rights reserved.
 * 
 */
public class GenericInference {

	@Test
	@SuppressWarnings("unused")
	public void testGenericInference () {
		//1.5
		Map<String, String> oneMap = new HashMap<String, String>();
		//1.7
		Map<String, String> twoMap = new HashMap<>();
		
		List<Integer> leftList = new ArrayList<>();
		leftList.add(2);
		//1.8
		List rightList = new ArrayList<>();
		leftList.addAll(new ArrayList<>());
		
		rightList.add("AAA");
		leftList.addAll(rightList);
		
		//here will throw a Class Cast Exception
		Integer a = leftList.get(1);
	}
}
