package com.project.dao;

import java.sql.SQLException;
import java.util.HashMap;

import com.fasterxml.jackson.databind.JsonNode;

public interface ProjectDao {

	HashMap<String, Object> getProjectTable(JsonNode data) throws SQLException;

}
