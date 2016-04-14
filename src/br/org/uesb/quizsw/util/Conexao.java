package br.org.uesb.quizsw.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {
	
	public static String status = "Not connected";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            String driverName = "org.postgresql.Driver";
            Class.forName(driverName);

            String serverName = "localhost";
            String mydatabase = "quizsw";
            String url = "jdbc:postgresql://" + serverName + "/" + mydatabase;
            String username = "postgres";
            String password = "t1v1k!";//"s31l4c4r4";

            connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {
                status = "Sucessfully connected";
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Wasn't possible connect.\n" + e);
        } finally {
            return connection;
        }
    }

    public static String statusConnection() {
        return status;
    }

    public static boolean closeConnection(Connection connection) {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            System.err.println("Wasn't possible close connection.\n" + e);
            return false;
        }
    }
    
    public static boolean rollback(Connection connection) {
        try {
            connection.rollback();
            return true;
        } catch (SQLException e) {
            System.err.println("Wasn't possible rollback connection.\n" + e);
            return false;
        }
    }
    
    public Connection restartConnection(Connection connection) {
        closeConnection(connection);
        return Conexao.getConnection();
    }
    
    public static synchronized int getCode(String nmTable) {
    	int code = 0;
    	try {
    		PreparedStatement pstmt = getConnection().prepareStatement("SELECT MAX(cd_"+nmTable+") as code FROM "+nmTable);
    		ResultSet rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			code = rs.getInt("code");
    		}
    		
    		return code+1;
    		
    	} catch(Exception e) {
    		e.printStackTrace(System.err);
    		return -1;
    	}
    }

}
