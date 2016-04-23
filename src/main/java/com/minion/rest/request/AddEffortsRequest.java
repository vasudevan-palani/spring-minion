package com.minion.rest.request;

import java.util.ArrayList;
import java.util.List;

import com.minion.Mapper;
import com.minion.service.bean.GetEffortsBean;



public class AddEffortsRequest extends Request {
	private String empId;
	private String password;
	
	private ArrayList<GetEffortsBean> request;


	public String getEmpId() {
		return empId;
	}


	public void setEmpId(String empId) {
		this.empId = empId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public ArrayList<GetEffortsBean> getRequest() {
		return request;
	}


	public void setRequest(ArrayList<GetEffortsBean> request) {
		this.request = request;
	}


	public com.minion.service.bean.request.AddEffortsRequest getServiceRequest(){
		com.minion.service.bean.request.AddEffortsRequest request = new com.minion.service.bean.request.AddEffortsRequest();
		request.setRequest(getRequest());
		return request;
	}
}
