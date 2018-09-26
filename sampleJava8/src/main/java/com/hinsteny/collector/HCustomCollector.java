package com.hinsteny.collector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.hisoka.pojo.Person;


/**
 * CustomCollector.java
 * 
 * @author: Hinsteny
 * @date: 2015年10月9日
 * @copyright: 2015 All rights reserved.
 * 
 */
public class HCustomCollector {
	
	private List<Person> persons;
	
	public HCustomCollector () {
		Object[][] personArray = {
                {"yao", "boss", true, 18, 300.01, Person.COLOR.GREEN, new String[]{"one","two","three"}}, 
                {"flym", "boss", false, 23, 200.01, Person.COLOR.RED, new String[]{"1","2","3"}},
                {"hisoka", "HR", true, 30, 100.01, Person.COLOR.YELLOW, new String[]{"I","II","III"}}
            };
		persons = Stream.of(personArray)
				.map(data -> new Person(data[0],data[1],data[2],data[3],data[4],data[5],data[6]))
				.collect(Collectors.toList());
	}

	@Test
	public void  test(){
		List personList = persons.stream().collect(CustomCollector.toList());
		System.out.println("PersonList: " + personList);
		
		Set personSet = persons.stream().collect(CustomCollector.toSet());
		System.out.println("personSet: " + personSet);
		
		List personPropertyList = persons.stream().collect(CustomCollector.propertyToList(Person::getTitle));
		System.out.println("personPropertyList: " + personPropertyList);
		
		List personPropertySalaryList = persons.stream().collect(CustomCollector.propertyToList(Person::getSalary));
		System.out.println("personPropertySalaryList: " + personPropertySalaryList);
		
		Set personPropertySet = persons.stream().collect(CustomCollector.propertyToSet(Person::getTitle));
		System.out.println("personPropertySet: " + personPropertySet);
		
		Integer sumAge = persons.stream().collect(CustomCollector.sumInteger(Person::getAge));
		System.out.println("sumAge: " + sumAge);

		Double sumSalary = persons.stream().collect(CustomCollector.sumDouble(Person::getSalary));
		System.out.println("sumSalary: " + sumSalary);
		
	}
	
	/**
	 * 
	 * @param <T> the type of input elements to the reduction operation
	 * @param <A> the mutable accumulation type of the reduction operation (often
	 *            hidden as an implementation detail)
	 * @param <R> the result type of the reduction operation
	 * @author Administrator
	 *
	 * @param <T>
	 * @param <A>
	 * @param <R>
	 */
	static class CustomCollector<T, A, R> implements Collector<T, A, R> {

		
		static final Set<Collector.Characteristics> CH_NOID = Collections.emptySet();
		
		private final Supplier<A> supplier;
        private final BiConsumer<A, T> accumulator;
        private final BinaryOperator<A> combiner;
        private final Function<A, R> finisher;
        private final Set<Characteristics> characteristics;

        private static <I, R> Function<I, R> castingIdentity() {
            return i -> (R) i;
        }
        
        static double computeFinalSum(double[] summands) {
        	return summands[0];
        }
        
		public CustomCollector(Supplier<A> supplier, BiConsumer<A, T> accumulator, BinaryOperator<A> combiner) {
			this(supplier, accumulator, combiner, castingIdentity());
		}

		public CustomCollector(Supplier<A> supplier, BiConsumer<A, T> accumulator, BinaryOperator<A> combiner, Function<A, R> finisher) {
			super();
			this.supplier = supplier;
			this.accumulator = accumulator;
			this.combiner = combiner;
			this.finisher = finisher;
			this.characteristics = CH_NOID;
		}
		
		@Override
		public Supplier supplier() {
			return supplier;
		}

		@Override
		public BiConsumer accumulator() {
			return accumulator;
		}

		@Override
		public BinaryOperator<A> combiner() {
			return combiner;
		}

		@Override
		public Function finisher() {
			return finisher;
		}

		@Override
		public Set characteristics() {
			return characteristics;
		}

		public static <T> Collector<T, ?, List<T>> toList ( ) {
	        return new CustomCollector<>((Supplier<List<T>>) ArrayList::new, List::add,
	                                   (left, right) -> { left.addAll(right); return left; });
	    }

		public static <T,R> Collector<T, ?, Set<R>> toSet ( ) {
			return new CustomCollector<>((Supplier<Set<T>>) HashSet::new, Set::add,
                    (left, right) -> { left.addAll(right); return left; });
	    }

		public static <T,R> Collector<T, ?, List<R>> propertyToList(Function<T,R> property) {
			BiConsumer<List<R>,T > accumulator = (list,element) ->  list.add(property.apply(element));
			
			return new CustomCollector<>((Supplier<List<R>>) ArrayList::new, accumulator,
                       (left, right) -> { left.addAll(right); return left; });
	    }

		public static <T,R> Collector<T, ?, Set<R>> propertyToSet(Function<T,R> property) {
			BiConsumer<Set<R>,T > accumulator = (set,element) ->  set.add(property.apply(element));
			
			return new CustomCollector<>((Supplier<Set<R>>) HashSet::new, accumulator,
                       (left, right) -> { left.addAll(right); return left; });
	    }
		
		public static <T> Collector<T, ?, Integer> sumInteger(ToIntFunction<? super T> mapper) {
			BiConsumer<Integer[],T > accumulator = (value,element) ->  {value[0] += mapper.applyAsInt(element);};
			BinaryOperator<Integer[]> combiner = (left, right) -> {left[0] += right[0]; return left;};
			
			return new CustomCollector<>(() -> {Integer i[] = new Integer[1];i[0] = 0;return i;}, accumulator, combiner,i->i[0]);
	    }
		
		public static <T,R> Collector<T, ?, Double> sumDouble(ToDoubleFunction<? super T> mapper) {
			BiConsumer<double[],T > accumulator = (value,element) ->  {value[0] += mapper.applyAsDouble(element);};
			BinaryOperator<double[]> combiner = (left, right) -> {left[0] += right[0]; return left;};
			
			return new CustomCollector<>(() -> new double[1], accumulator,combiner, a -> computeFinalSum(a));
	    }
	}
}
