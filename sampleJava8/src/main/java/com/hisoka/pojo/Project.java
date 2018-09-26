package com.hisoka.pojo;

public class Project {

	private String id;
	private String name;
	private String client;
	private String description;

	public Project(String name) {
		this.name = name;
	}

	public Project(String id, String name, String client) {
		this.id = id;
		this.name = name;
		this.client = client;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return "" + name + "(" + client + ")";
	}
}