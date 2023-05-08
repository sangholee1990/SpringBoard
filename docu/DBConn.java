package com.kosmo.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConn {

	final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	final String DB_USER = "sat11";
	final String DB_PASSWORD = "0000";

	public Connection conn() {
		Connection conn = null;
		//        PreparedStatement pstmt = null;
		//        ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e ) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch ( Exception e ) {
			e.printStackTrace();
		} 
		return conn;
	}

	public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			// ResultSet를 닫는다.
			if(rs !=null) rs.close();
			// PreparedStatement를 닫는다.
			if(pstmt !=null) pstmt.close();
			// Connection를 닫는다.
			if(conn != null) conn.close();
		} catch ( SQLException e ) {}
	}

}
