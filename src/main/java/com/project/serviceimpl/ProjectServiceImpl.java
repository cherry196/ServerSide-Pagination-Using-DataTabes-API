package com.project.serviceimpl;

import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.dao.ProjectDao;
import com.project.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectDao projectDao;
	
	@Override
	public HashMap<String,Object> getProjectTable(JsonNode data) throws SQLException{
		return projectDao.getProjectTable(data);
	}
}
