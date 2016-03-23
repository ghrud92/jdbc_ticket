package com.estsoft.ticket.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.ticket.vo.ReserveVO;

public class ReserveDAO {
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
	
	public void insert(ReserveVO reserveVo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = getConnection();
			
			String sql = "insert into reserve values(null,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reserveVo.getPhone());
			pstmt.setInt(2, reserveVo.getCount());
			pstmt.setLong(3, reserveVo.getMovie_no());
			
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
	
	public void delete(ReserveVO reserveVo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = reserveVo.getMovie_no().intValue() - 1;
		try{
			conn = getConnection();
			
			String sql = "select movie_no from reserve where phone = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reserveVo.getPhone());
			
			rs = pstmt.executeQuery();
			int i=0;
			long movieno = 0;
			while(rs.next()){
				movieno = rs.getLong(1);
				if(i==count){
					break;
				}
				i++;
			}
			reserveVo.setMovie_no(movieno);
			
			
			sql = "delete from reserve where phone = ? and movie_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reserveVo.getPhone());
			pstmt.setLong(2, reserveVo.getMovie_no());
			
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean check(ReserveVO reserveVo){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			
			String sql = "select * from reserve where phone = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reserveVo.getPhone());
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				flag = true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public boolean insertCheck(ReserveVO reserveVo){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			
			String sql = "select * from reserve where phone = ? and movie_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reserveVo.getPhone());
			pstmt.setLong(2, reserveVo.getMovie_no());
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				flag = true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public List<ReserveVO> getList(ReserveVO reserveVo){
		List<ReserveVO> list = new ArrayList<ReserveVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			
			String sql = "select m.name, r.count from movie m, reserve r where m.no = r.movie_no and r.count <> 0"
					+ " and r.phone = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reserveVo.getPhone());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				String movie = rs.getString(1);
				int count = rs.getInt(2);
				
				ReserveVO reserveVo2 = new ReserveVO();
				reserveVo2.setMovie_name(movie);
				reserveVo2.setCount(count);
				
				list.add(reserveVo2);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
