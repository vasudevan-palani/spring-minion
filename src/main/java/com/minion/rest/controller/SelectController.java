package com.minion.rest.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.minion.model.Project;
import com.minion.repo.ProjectRepository;
import com.minion.rest.request.SelectRequest;
import com.minion.rest.request.bean.SelectItem;
import com.minion.rest.response.SelectGetResponse;
import com.minion.rest.response.bean.SelectGetBean;
import com.minion.service.ErrorCodes;
import com.minion.service.ErrorMsg;
import com.minion.service.MinionServiceException;

@RestController
@RequestMapping("/selects")
public class SelectController extends BaseController {
	
	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
	ErrorMsg errorMsgs;
	
	@CrossOrigin(origins = {"http://knowinminutes.com","http://localhost:8887"})
	@RequestMapping(produces = "application/json", value = "/get",method={RequestMethod.POST,RequestMethod.OPTIONS})
	public ResponseEntity<SelectGetResponse> get(@RequestBody SelectRequest request){
		SelectGetResponse response = new SelectGetResponse();
		
		try {
			for (Iterator<SelectItem> names = request.getNames().iterator(); names.hasNext();) {
				SelectItem name = names.next();
				
				if(name.getName().equalsIgnoreCase("project")){
					Iterable<Project> projects = projectRepo.findAll();
					List<SelectGetBean> beans = new ArrayList<SelectGetBean>();
					for (Project project : projects) {
						SelectGetBean bean = new SelectGetBean();
						bean.setId(project.getId().toString());
						bean.setName(project.getName());
						beans.add(bean);
					}
					response.getList().put("project",beans);
				}
			}

			response.setErrorcode(ErrorCodes.SUCCESS);

		} catch (MinionServiceException exception) {
			response.setErrorcode(exception.getErrorCode());
			response.setErrorMsg(exception.getErrorMsg());
		}
		
		
		return new ResponseEntity<SelectGetResponse>(response, HttpStatus.OK);
	}
}
