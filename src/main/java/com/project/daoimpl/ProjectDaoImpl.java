package com.project.daoimpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dao.ProjectDao;;

@Repository
public class ProjectDaoImpl implements ProjectDao {

	@Autowired
	@Qualifier("oracleDataSource")
	private DataSource oracleDataSource;

	@Override
	public HashMap<String, Object> getProjectTable(JsonNode data) throws SQLException{
		HashMap<String, Object> resultMap = new HashMap<>();
		System.out.println("STARTS getProjectTable");
		Connection con = null;
		PreparedStatement pst = null;
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root;
			root = mapper.readTree(data.toString());
			int start = root.path("start").asInt();
			int draw = root.path("draw").asInt();
			int length = root.path("length").asInt();
			int maxVal = start + length;
			HashMap<String, String> colMap = new HashMap<>();
			JsonNode contactNode = root.path("columns");
			String searchStr = "";
			for (JsonNode node : contactNode) {
				String columnName = node.path("data").asText();
				JsonNode searchNode = node.path("search");
				String searchVal = searchNode.path("value").asText();
				if (!searchVal.isEmpty()) {
					colMap.put(columnName, searchVal);
				}
			}
			for (Map.Entry<String, String> s : colMap.entrySet()) {
				if (searchStr.isEmpty()) {
					searchStr = s.getKey() + " LIKE '%" + s.getValue() + "%'";
				} else {
					searchStr = searchStr + " AND " + s.getKey() + " LIKE '%" + s.getValue() + "%'";
				}
			}
			con = oracleDataSource.getConnection();
			String query = "";
			if (searchStr.isEmpty()) {
				query = "SELECT * FROM ( SELECT tmp.*, rownum rn " + "FROM ( SELECT * FROM CITIES ) tmp "
						+ " WHERE rownum <= " + maxVal + ")" + " WHERE rn > " + start;
			} else {
				query = "SELECT * FROM ( SELECT tmp.*, rownum rn " + "FROM ( SELECT * FROM CITIES WHERE " + searchStr
						+ ") tmp " + " WHERE rownum <= " + maxVal + ")" + " WHERE rn > " + start;
			}
			System.out.println("QUERY IS "+query);
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			List<HashMap<String, String>> dataList = new LinkedList<>();
			while (rs.next()) {
				HashMap<String, String> dataMap = new HashMap<>();
				dataMap.put("CITY", rs.getString("CITY"));
				dataMap.put("SHORT_STATE", rs.getString("SHORT_STATE"));
				dataMap.put("FULL_STATE", rs.getString("FULL_STATE"));
				dataMap.put("COUNTY", rs.getString("COUNTY"));
				dataMap.put("CITY_ALIAS", rs.getString("CITY_ALIAS"));
				dataList.add(dataMap);
			}
			resultMap.put("data", dataList);
			resultMap.put("draw", draw);
			pst1 = con.prepareStatement("SELECT COUNT(*) FROM CITIES");
			rs1 = pst1.executeQuery();
			if (rs1.next()) {
				resultMap.put("recordsTotal", rs1.getInt(1));
			}
			if (searchStr.isEmpty()) {
				pst2 = con.prepareStatement("SELECT COUNT(*) FROM CITIES");
			} else {
				pst2 = con.prepareStatement("SELECT COUNT(*) FROM CITIES WHERE " + searchStr);
			}
			rs2 = pst2.executeQuery();
			if (rs2.next()) {
				resultMap.put("recordsFiltered",rs2.getInt(1));
			}
		}

		catch (SQLException ex) {
			System.out.println("ERROR IN getProjectTable " + ex);
		} catch (JsonProcessingException ex) {
			System.out.println("ERROR IN getProjectTable " + ex);
		} catch (IOException ex) {
			System.out.println("ERROR IN getProjectTable " + ex);
		}
		finally {
			if(rs!=null)
				rs.close();
			if(rs1!=null)
				rs1.close();
			if(rs2!=null)
				rs2.close();
			if(con!=null)
				con.close();
			if(pst!=null)
				pst.close();
			if(pst1!=null)
				pst1.close();
			if(pst2!=null)
				pst2.close();
		}
		System.out.println("ENDS getProjectTable");
		return resultMap;
	}
}
