package com.onur.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Employee {
	public Employee(){
		
	}
	public Employee(String personName, String personSurname,int personAge, Department department, Company company){
		this.setPersonName(personName);
		this.setPersonSurname(personSurname);
		this.setDepartment(department);
		this.setPersonAge(personAge);
		this.setCompany(company);
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	@Column(name="PersonName")
	private String personName;
	@Column(name="PersonSurname")
	private String personSurname;
	@Column(name="PersonAge")
	private int personAge;
	@JoinColumn(name="DepartmentId")
	private Department department;
	@JoinColumn(name="CompanyId")
	private Company company;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonSurname() {
		return personSurname;
	}
	public void setPersonSurname(String personSurname) {
		this.personSurname = personSurname;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public int getPersonAge() {
		return personAge;
	}
	public void setPersonAge(int personAge) {
		this.personAge = personAge;
	}

}
