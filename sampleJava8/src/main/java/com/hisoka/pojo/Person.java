package com.hisoka.pojo;

import java.util.Arrays;


/**
 * Person.java
 * 
 * @author: Hinsteny
 * @date: 2015年10月9日
 * @copyright: 2015 All rights reserved.
 * 
 */
public class Person extends Object{
	private String name, title;
	private boolean sex;
	private int age;
	private double salary;
	private COLOR color;
	private String friends[];
	
	public Person(String name, String title, int age, double salary) {
		super();
		this.name = name;
		this.title = title;
		this.age = age;
		this.salary = salary;
	}
	
	public Person(String name, String title, boolean sex, int age, double salary, COLOR color) {
		super();
		this.name = name;
		this.title = title;
		this.sex = sex;
		this.age = age;
		this.salary = salary;
		this.color = color;
	}

	
	public Person(String name, String title, boolean sex, int age,
			double salary, COLOR color, String[] friends) {
		super();
		this.name = name;
		this.title = title;
		this.sex = sex;
		this.age = age;
		this.salary = salary;
		this.color = color;
		this.friends = friends;
	}

	public Person(Object name, Object title, Object sex, Object age,
			Object salary, Object color, Object friends) {
		super();
		this.name = (String) name;
		this.title = (String) title;
		this.sex = (boolean) sex;
		this.age = (int) age;
		this.salary = (double) salary;
		this.color = (COLOR) color;
		this.friends = (String[]) friends;
	}
	
	public boolean getSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}


	/**
	 * @return the color
	 */
	public COLOR getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(COLOR color) {
		this.color = color;
	}	
	
	
	/**
	 * @return the friends
	 */
	public String[] getFriends() {
		return friends;
	}

	/**
	 * @param friends the friends to set
	 */
	public void setFriends(String[] friends) {
		this.friends = friends;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", title=" + title + ", sex=" + sex
				+ ", age=" + age + ", salary=" + salary + ", color=" + color
				+ ", friends=" + Arrays.toString(friends) + "]";
	}

	public enum COLOR {  
		GREEN, YELLOW, RED
	}
}
