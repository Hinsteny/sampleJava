package com.hinsteny;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.junit.Test;

import com.hisoka.Annotation.Programmer;
import com.hisoka.Annotation.Programmers;

/**
 * PracticeRepeatAnnotation.java
 * 
 * @author: Hinsteny
 * @date: 2015年10月9日
 * @copyright: 2015 All rights reserved.
 * 
 */
@Programmer(name="Admin")
@Programmer(name="Manager")
public class PracticeRepeatAnnotation {
	
	@Programmer(name="Manager")
	String name;
	
	@Test
	@Programmers({@Programmer(name="Admin"),@Programmer(name="Manager")})
	public void testMethodAnnotation () {
		Method[] methods = PracticeRepeatAnnotation.class.getDeclaredMethods();
		for(Method method : methods){
			if(method.getName() != "testMethodAnnotation"){
				continue;
			}
			boolean hasAnnotation = method.isAnnotationPresent(Programmers.class); 
			if(hasAnnotation){
				Programmers programmers = method.getAnnotation(Programmers.class);
				Programmer programmerone = programmers.value()[0];
				Programmer programmertwo = programmers.value()[1];
				System.out.println("************TestMethodAnnotation one***************");
				System.out.println(programmerone.name());
				System.out.println(programmertwo.name());
			}

			Annotation[] annotations = method.getDeclaredAnnotations();
			for(Annotation annotation : annotations){
				if(annotation.annotationType() == Programmers.class){
					Programmers programmers = (Programmers) annotation;
					Programmer programmerone = programmers.value()[0];
					Programmer programmertwo = programmers.value()[1];
					System.out.println("************TestMethodAnnotation two***************");
					System.out.println(programmerone.name());
					System.out.println(programmertwo.name());
				}
			}
		}
	}
	
	@Test
	@Programmer(name="Admin")
	@Programmer(name="Manager")
	public void testRepeatAnnotation () throws Exception, Exception {
		Method method = PracticeRepeatAnnotation.class.getMethod("testRepeatAnnotation");
		boolean hasAnnotation = method.isAnnotationPresent(Programmers.class); 
		if(hasAnnotation){
			Programmers programmers = method.getAnnotation(Programmers.class);
			Programmer programmerone = programmers.value()[0];
			Programmer programmertwo = programmers.value()[1];
			System.out.println("************TestRepeatAnnotation one***************");
			System.out.println(programmerone.name());
			System.out.println(programmertwo.name());
		}

		Annotation[] annotations = method.getDeclaredAnnotations();
		for(Annotation annotation : annotations){
			if(annotation.annotationType() == Programmers.class){
				Programmers programmers = (Programmers) annotation;
				Programmer programmerone = programmers.value()[0];
				Programmer programmertwo = programmers.value()[1];
				System.out.println("************TestRepeatAnnotation two***************");
				System.out.println(programmerone.name());
				System.out.println(programmertwo.name());
			}
		}
	}
	
	@Test
	public void testClassAnnotation() throws ClassNotFoundException {
		Class clazz = Class.forName("com.hinsteny.PracticeRepeatAnnotation");
		Annotation[] annotations = clazz.getAnnotations();
		for(Annotation annotation : annotations){
			if(annotation.annotationType() == Programmers.class){
				Programmers programmers = (Programmers) annotation;
				Programmer programmerone = programmers.value()[0];
				Programmer programmertwo = programmers.value()[1];
				System.out.println("************TestClassAnnotation***************");
				System.out.println(programmerone.name());
				System.out.println(programmertwo.name());
			}
			if(annotation.annotationType() == Programmer.class){//This will not execute,so the RepeatAnnotation is also Annotation packaging
				Programmer programmer = (Programmer) annotation;
				System.out.println("************TestClassAnnotation child***************");
				System.out.println(programmer.name());
			}
		}
	}
}
