package com.kosmo.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//Data Asccess Object
public class MemberDAO {
	public ArrayList<MemberVO> select() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member"; // where seq=?";
		ArrayList<MemberVO> list  = new ArrayList<MemberVO>();
		DBConn c = new DBConn();
		try {
			conn = c.conn();
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, 5);	//바인딩
			//pstmt.setString(2, "111"); 	//바인딩
			rs = pstmt.executeQuery();
	
			while (rs.next()) { 
				MemberVO vo = new MemberVO();
				vo.setSeq(rs.getInt("seq"));
				vo.setName(rs.getString("name"));
				list.add(vo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list; 
	}
}
