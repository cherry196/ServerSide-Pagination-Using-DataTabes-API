package com.project.service;

import java.sql.SQLException;
import java.util.HashMap;

import com.fasterxml.jackson.databind.JsonNode;

public interface ProjectService {

	HashMap<String, Object> getProjectTable(JsonNode data) throws SQLException;

}
