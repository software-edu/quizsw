package br.org.uesb.quizsw.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Util {
	
	public static ArrayList<HashMap<String, Object>> resultSetToList(ResultSet rs) {
		ArrayList<HashMap<String, Object>> rsList = new ArrayList<>();
		try {
			while(rs.next()) {
				HashMap<String, Object> line = new HashMap<>();
				for(int i=1; i<=rs.getMetaData().getColumnCount(); i++) {
					String column = rs.getMetaData().getColumnName(i);
					line.put(column, rs.getObject(column));
				}
				rsList.add(line);
			}
			
			return rsList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
