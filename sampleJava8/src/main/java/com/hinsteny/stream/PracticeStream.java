package com.hinsteny.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

import org.junit.Test;

import com.hisoka.pojo.Person;


/**
 * PracticeStream.java
 * 
 * @author: Hinsteny
 * @date: 2015年10月9日
 * @copyright: 2015 All rights reserved.
 * 
 */
public class PracticeStream {
	
	@Test
	public void execute() {		
		List<Person> persons = personList();
		streamType(persons);
		reduceMethod(persons);
		collectMethod(persons);
		groupingByMethod(persons);
		toMapMethod(persons);
		flatMapMethod(persons);
		reducingMethod(persons);
	}
	
	/**
	 * use method of parallelStream can get stream is a parallel
	 * use method of stream can get stream is a sequential
	 */
	private void streamType(List<Person> persons){
		System.out.println("************");
		persons.stream().limit(2).filter((s)-> {System.out.println(s.getName());return true;})
		.filter(s->{System.out.println(s.getTitle());return true;}).collect(Collectors.toList());
		System.out.println("============");
		persons.parallelStream().limit(2).filter((s)-> {System.out.println(s.getName());return true;})
		.filter(s->{System.out.println(s.getTitle());return true;}).collect(Collectors.toList());
		System.out.println("************");
	}
	
	/**
	 * 
	 */
	private void collectMethod(List<Person> persons){
		List<Person> personcollectone = persons.stream().collect(() -> new ArrayList<>(),
                (list, item) -> {if(!item.getSex()){list.add(item);}},
                (list1, list2) -> list1.addAll(list2));
		personcollectone.stream().forEach(System.out::println);
		
		List<Integer> personcollectage = persons.stream().filter(p -> p.getColor() == Person.COLOR.GREEN)
        		.map(Person::getAge)
        		.collect(() -> new ArrayList<>(), 
        				(list,item) -> {if(item>20){list.add(item);}}, 
        				(list1, list2) -> list1.addAll(list2));
		personcollectage.stream().forEach(System.out::println);
		
		personcollectage = persons.stream().filter(p -> p.getColor() == Person.COLOR.GREEN)
        		.map(Person::getAge)
        		.collect(() -> new ArrayList<>(), 
        				(list,item) -> {list.add(item);}, 
        				(list1, list2) -> list1.addAll(list2));
		personcollectage.stream().forEach(System.out::println);
	}
	
	private void reduceMethod(List<Person> persons){
		System.out.println(persons.stream().mapToDouble(p -> p.getSalary()).reduce((sum,  item) -> sum+item).getAsDouble());
		System.out.println(persons.stream().mapToInt(Person::getAge).average().getAsDouble());
		
	}

	private void reducingMethod(List<Person> persons){
		Map reducingPerson = persons.stream().collect( Collectors.groupingBy(Person::getSex,Collectors.reducing(0, Person::getAge, Integer::sum)));
		System.out.println(reducingPerson);
		
	}
	
	private void groupingByMethod(List<Person> persons){
		Map<Person.COLOR, List<Person>> PersonByColor = persons.stream().filter(p -> p.getSex()).collect(groupingBy(Person::getColor));
		System.out.println("PersonByColor:"+PersonByColor+"\n");
	}
	
	private void toMapMethod(List<Person> persons){
		Map<String, Object> personToMap = persons.stream().collect(toMap(Person::getName, (p) -> {return p;}));
		System.out.println("personToMap:"+personToMap+"\n");
	}
	
	private void flatMapMethod(List<Person> persons){
		List personFriends = persons.parallelStream().flatMap(p-> {if(p.getFriends()!=null){return Stream.of(p.getFriends());}else{return Stream.of(new String());}}).collect(Collectors.toList());
		System.out.println("personFriends"+personFriends+"\n");
	}
	
	private List<Person> personList(){
		List<Person> persons = new ArrayList<>();
		Person person = new Person("ruo", "boss", true, 18, 10000.01, Person.COLOR.GREEN, new String[]{"one","two","three"});
		Person personA = new Person("hinsteny", "manager", false, 23, 1000.01, Person.COLOR.RED, new String[]{"1","2","3"});
		Person personB = new Person("hisoka", "HR", true, 30, 100.01, Person.COLOR.YELLOW, new String[]{"I","II","III"});
		Person personC = new Person("xuhu", "employee", false, 10, 10.01, Person.COLOR.GREEN);
		Person personD = new Person("kiven", "employee", false, 30, 1.01, Person.COLOR.YELLOW);
		persons.add(person);
		persons.add(personA);
		persons.add(personB);
		persons.add(personC);
		persons.add(personD);
		return persons;
	}
}
