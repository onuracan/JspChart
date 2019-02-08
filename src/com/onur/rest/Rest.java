package com.onur.rest;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Transient;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.onur.model.Company;
import com.onur.model.Department;
import com.onur.model.Employee;
import javax.ws.rs.*;
@Path("/list")
public class Rest {
	
	EntityManagerFactory factory=Persistence.createEntityManagerFactory("RestJpaProject");
	EntityManager em=factory.createEntityManager();
	@GET
	@Path("/count")
	@Produces(MediaType.TEXT_PLAIN)
	public int getCount(){
		int count=0;
		Query q= em.createQuery("select e from Employee e ");
		List<Employee> employeeList=q.getResultList();
		count=employeeList.size();
		em.close();
		factory.close();	
		return count;
	}
	@POST
	@Path("/insert/{name}/{surname}/{age}/{department}/{company}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response add(@PathParam("name") String name , @PathParam("surname") String surname,@PathParam("age") int age, 
		@PathParam("department") String department, @PathParam("company") String company){
		Company c=Company.companyList(company);
		Department d=Department.departmentList(department);
		Employee e=new Employee(name,surname,age,d,c);
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
		em.close();
		factory.close();
		return Response.status(200).entity(e).build();
		
	}
}