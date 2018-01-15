package com.project.web;

import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.JsonNode;

import com.project.dao.ProjectDao;
import com.project.service.ProjectService;

@Controller("ProjectController")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	@Autowired
	ProjectDao projectDao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayIndex() {
		return "Index";
	}
	
	@ResponseBody
	@RequestMapping(value = "/projecttable", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String,Object> getProjectTable(
			@RequestBody JsonNode data) {
		HashMap<String,Object> resultMap = new HashMap<>();
		try {
			resultMap = projectService.getProjectTable(data);
		}
		catch(Exception ex) {
			System.out.println("ERROR IN getProjectTable "+ex);
		}
		return resultMap;
	}
}
