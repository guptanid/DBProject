package com.db.db.objects;

public class Department {

	private int id;
	private String name;
	
	public Department() {
		id = 0;
		name = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
