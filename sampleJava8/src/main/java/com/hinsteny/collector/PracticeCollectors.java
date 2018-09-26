package com.hinsteny.collector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.hisoka.pojo.Person;
import com.hisoka.pojo.Person.COLOR;


/**
 * PracticeCollectors.java
 * 
 * @author: Hinsteny
 * @date: 2015年10月9日
 * @copyright: 2015 All rights reserved.
 * 
 */
public class PracticeCollectors {
	
	@Test
	public void collectorTest(){
		List<Person> persons = personList();

		attributeList(persons);
		attributeSet(persons);
		
		objectStrings(persons);
		
		summingOperator(persons);
		averagingOperator(persons);
		maxOperator(persons);
		minOperator(persons);
		countOperator(persons);
		
		groupingBySex(persons);
		groupingByOperator(persons);
		summingDoublegroupingBySex(persons);
		groupingByConcurrent(persons);
		
		partitioningOperator(persons);		
		
		personNameMap(persons);
		nameToSalaryMap(persons);
	}
	
	// Accumulate names into a List
	private void attributeList(List<Person> persons){
		List<String> list = persons.stream()
                               .map(Person::getName)
                               .collect(Collectors.toList());
       System.out.println("NameList: " + list);
	}
	
	// Accumulate names into a TreeSet
	private void attributeSet(List<Person> persons){
		 Set<String> set = persons.stream()
                               .map(Person::getName)
                               .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("NameSet: " + set);
	}
	
	//joining object to string separated by commas
	private void objectStrings(List<Person> persons){
		String joined = persons.stream()
                               .map(Object::toString)
                                .collect(Collectors.joining(",","!","/!"));
		System.out.println("JoiningString: " + joined);
	}
	
	//summing,average,maxBy,minBy,counting
	private void summingOperator(List<Person> persons){
		Double total = persons.stream().collect(Collectors.summingDouble(Person::getSalary));
		System.out.println("TotalSalary: " + total);
	}
	
	private void averagingOperator(List<Person> persons){
		double averagingSalary= persons.stream().collect(Collectors.averagingDouble(Person::getSalary));
        System.out.println("AverageSalary: " + averagingSalary);
	}
	
	private void maxOperator(List<Person> persons){
		Optional maxBy = persons.stream().collect(Collectors.maxBy((p1,p2) -> p1.getSalary() > p2.getSalary() ? 1 : -1));
        System.out.println("MaxSalaryPeople: " + maxBy);
	}
	
	private void minOperator(List<Person> persons){
		Optional minBy = persons.stream().collect(Collectors.minBy((p1,p2) -> p1.getSalary() > p2.getSalary() ? 1 : -1));
        System.out.println("MinSalaryPeople: " + minBy);
	}
	
	private void countOperator(List<Person> persons){
		long count = persons.stream().filter((p) -> p.getName().length() > 0).collect(Collectors.counting());
        System.out.println("PeopleNumber: " + count);
	}
	
	//groupingBy,groupingByConcurrent
	private void groupingBySex(List<Person> persons){
		Map<Boolean, List<Person>> byDept = persons.stream().collect(Collectors.groupingBy(p -> p.getSex()));
        System.out.println("groupingBy sex:" + byDept);
	}
	
	private void groupingByOperator(List<Person> persons){
		 Map<String, Double> salaryByName =persons.stream().
                                collect(Collectors.groupingBy(Person::getName, Collectors.summingDouble(Person::getSalary)));
		 System.out.println("MapSalaryByName: " + salaryByName);
	}
	
	private void summingDoublegroupingBySex(List<Person> persons){
		Map<Object, Double> totalSalary =persons.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.summingDouble(Person::getSalary)));
        System.out.println("summingDouble Salary by sex:" + totalSalary);
	}
	
	private void groupingByConcurrent(List<Person> persons){
		ConcurrentMap<COLOR, List<Person>> groupingByConcurrent = persons.stream().collect(Collectors.groupingByConcurrent(Person::getColor));
        System.out.println("groupingByConcurrent: " + groupingByConcurrent);
	}
	
	//Partition
	private void partitioningOperator(List<Person> persons){
		Map<Boolean,List<Person>> passingFailing = persons.stream().collect(Collectors.partitioningBy(p -> p.getAge() > 20));
        System.out.println("passingFailing: " + passingFailing);
	}

	//Mapto
	private void personNameMap(List<Person> persons){
		Map<String,String> toMap = persons.stream().collect(Collectors.toMap(Person::getName, Person::getTitle));
        System.out.println("personNameMap: " + toMap);
	}
	
	private void nameToSalaryMap(List<Person> persons){
		Map salaryByName = persons.stream().collect(()->new HashMap<>(), (hashMap,item) -> {hashMap.put(item.getName(), item.getSalary());}, (hashMapOld, hashMapNew) -> hashMapOld.putAll(hashMapNew));
		System.out.println("salaryByName: " + salaryByName);
	}
	
	private List<Person> personList(){
		Object[][] personArray = {
                {"yao", "boss", true, 18, 300.01, Person.COLOR.GREEN, new String[]{"one","two","three"}}, 
                {"flym", "manager", false, 23, 200.01, Person.COLOR.RED, new String[]{"1","2","3"}},
                {"hisoka", "HR", true, 30, 100.01, Person.COLOR.YELLOW, new String[]{"I","II","III"}}
            };
		List<Person> persons = Stream.of(personArray)
				.map(data -> new Person(data[0],data[1],data[2],data[3],data[4],data[5],data[6]))
				.collect(Collectors.toList());
		return persons;
	}
}
