package com.onur.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Transient;

@Entity
public class Company {
	public Company(){
		
	}
	public Company(String companyCode,String companyName){
		this.setCompanyCode(companyCode);
		this.setCompanyName(companyName);
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	@Column(name="CompanyCode")
	private String companyCode;
	@Column(name="CompanyName")
	private String companyName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Transient
	public static Company companyList(String companyCode){
		if(companyCode==null || companyCode==""){
			System.out.println("Þirket kodu boþ geçilemez...");
		}
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("RestJpaProject");
		EntityManager em=factory.createEntityManager();
		Query q= em.createNamedQuery("select c from Company c where c.CompanyCode=:companyCode");
		Company c=(Company) q.setParameter("companyCode", companyCode).getSingleResult();
		em.close();
		factory.close();	
		return c;
	}
}
