package com.onur.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Transient;

@Entity
public class Department {

	public Department(){
		
	}
	public Department(String departmentCode, String departmentName, Company company){
		this.setDepartmentCode(departmentCode);
		this.setDepartmentName(departmentName);
		this.setCompany(company);
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	@Column(name="DepartmentCode")
	private String departmentCode;
	@Column(name="DepartmentName")
	private String departmentName;
	@OneToOne
	@JoinColumn(name="CompanyId")
	private Company company;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	@Transient
	public static Department departmentList(String departmentCode){
		if(departmentCode==null || departmentCode==""){
			System.out.println("Departman kodu boþ geçilemez...");
		}
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("RestJpaProject");
		EntityManager em=factory.createEntityManager();
		Query q= em.createQuery("select d from Department d where d.departmentCode=:departmentCode").setParameter("departmentCode", departmentCode);
		List<Department> department=q.getResultList();
		Department d = null;
		for(int i=0; i<department.size(); i++){
			d=department.get(i);
		}
		em.close();
		factory.close();	
		return d;
	}
}
