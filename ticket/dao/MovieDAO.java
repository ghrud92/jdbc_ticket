package com.estsoft.ticket.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.estsoft.ticket.vo.MovieVO;

public class MovieDAO {
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
	         String url = "jdbc:mysql://localhost/webdb";
	         conn = DriverManager.getConnection(url, "webdb", "webdb");
		}catch (ClassNotFoundException e) {
	         System.out.println("드라이버를 찾을 수 없습니다." + e);
	         e.printStackTrace();
	    } 
		return conn;
	}
	
	public void insert(MovieVO movieVo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = getConnection();
			
			String sql = "insert into reserve values(null,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, movieVo.getName());
			
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
