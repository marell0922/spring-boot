package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.CommentsVo;
@Repository
public class CommentsDao {
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			//1. 드라이버 로딩
			Class.forName( "com.mysql.jdbc.Driver" );
			
			//2. 연결하기
			String url="jdbc:mysql://localhost/webdb?characterEncoding=utf8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch( ClassNotFoundException e ) {
			System.out.println( "드러이버 로딩 실패:" + e );
		} 
		
		return conn;
	}
	
	public boolean delete(long no) {
		// TODO Auto-generated method stub
		boolean result=false;
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			
			String sql="delete from comments where no=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1, no);

	        int count = pstmt.executeUpdate();
	         
	        result = count == 1;

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)
				conn.close();
				
				if(pstmt!=null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean insert(CommentsVo vo) {
		// TODO Auto-generated method stub
		boolean result=false;
		
		Connection conn = null;
	      PreparedStatement pstmt = null;
	      try {
	         conn = getConnection();
	         String sql = "insert into comments values "
	         		+ "(null, ?, now() , ?,?);";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, vo.getContent());
	         pstmt.setLong(2, vo.getBoard_no());
	         pstmt.setLong(3, vo.getUser_no());
	         
	         int count=pstmt.executeUpdate();
	         
	         result=count ==1;
	         
	      } catch (SQLException e) {
	         System.out.println("error :" + e);
	      } finally {
	         try {
	            if (pstmt != null) {
	               pstmt.close();
	            }
	            if (conn != null) {
	               conn.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
		
		return result;
	}
	
	public List<CommentsVo> getList(long board_no) {
		// TODO Auto-generated method stub
		List<CommentsVo> list=new ArrayList<CommentsVo>();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			
			String sql="select no, content, write_date, user_no, b.name  "
					+ "from user a, comments b where a.no=b.user_no where board_no=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1, board_no);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				long no=rs.getLong(1);
				String contents=rs.getString(2);
				String write_date=rs.getString(3);
				long user_no=rs.getLong(4);
				String user_name=rs.getString(5);
				
				CommentsVo vo=new CommentsVo();
				vo.setNo(no);
				vo.setContent(contents);
				vo.setWrite_date(write_date);
				vo.setUser_no(user_no);
				vo.setUser_name(user_name);
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)
				conn.close();
				
				if(pstmt!=null)
					pstmt.close();
				if(rs!=null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
