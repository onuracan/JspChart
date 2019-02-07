package com.onur.rest;
import java.util.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientResponse;

import com.google.gson.*;
import com.google.gson.JsonObject;
import com.onur.model.Company;
import com.onur.model.Department;
import com.onur.model.Employee;
@SuppressWarnings("unused")
public class Chart {
	Gson gsonObj=new Gson();
	Map<Object,Object> map=null;
	List<Map<Object,Object>> list=new ArrayList<Map<Object,Object>>();
	Client client=ClientBuilder.newClient();
	Response response=null;
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String PASS="pass";
	private	static final String FAIL="fail";
	private int databaseCount(String uri){
		response=client.target(uri).request().get();
		int count=response.readEntity(int.class);
		response.close();
		client.close();
		return count;
	}
	public String chart(){
		map = new HashMap<Object,Object>();map.put("label", "Genel Personel Sayýsý:"); 
		map.put("y", databaseCount("http://localhost:8080/RestJpaProject/rest/list")); list.add(map);
		String dataPoints=gsonObj.toJson(list);
		return dataPoints;
	}
	public String addData(String name, String surname, int age, String companyCode, String departmentCode){
		Company c=Company.companyList(companyCode);
		Department d=Department.departmentList(departmentCode);
		Employee e=new Employee(name,surname,age,d,c);
		String callResult=client.target("http://localhost:8080/RestJpaProject/rest/list/insert").request(MediaType.TEXT_PLAIN).post(Entity.entity(e, MediaType.TEXT_PLAIN),String.class);
		String result=PASS;
		if(!SUCCESS_RESULT.equals(callResult)){
			result=FAIL;
		}
		return result;
	}
}