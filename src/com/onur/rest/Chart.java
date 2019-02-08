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
	private int databaseCount(String uri){
		response=client.target(uri).request().get();
		int count=response.readEntity(int.class);
		response.close();
		client.close();
		return count;
	}
	public String chart(){
		map = new HashMap<Object,Object>();map.put("label", "Genel Personel Sayýsý:"); 
		map.put("y", databaseCount("http://localhost:8080/RestJpaProject/rest/list/count")); list.add(map);
		String dataPoints=gsonObj.toJson(list);
		return dataPoints;
	}
}