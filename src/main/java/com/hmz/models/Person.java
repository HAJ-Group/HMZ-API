package com.hmz.models;

import com.hmz.operations.Age;

public class Person {
	
	private int id;
	private String name;
	private Age age;
	private String adresse;
	private String email;
	private String job;
	
	public Person() {}

	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Person(int id, String name, Age age, String adresse, String email, String job) {
		this(id, name);
		this.age = age;
		this.adresse = adresse;
		this.email = email;
		this.job = job;
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

	public Age getAge() {
		return age;
	}

	public void setAge(Age age) {
		this.age = age;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", adresse=" + adresse + ", email=" + email
				+ ", job=" + job + "]";
	}

}
