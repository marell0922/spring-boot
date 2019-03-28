package com.douzone.emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.douzone.emaillist.vo.EmaillistVo;

@Repository
public class EmaillistDao {
	private static Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		
		Connection conn=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url="jdbc:mysql://localhost:3306/webdb";
			conn=DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public List<EmaillistVo> getList(){
		List<EmaillistVo> list=new ArrayList<EmaillistVo>();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			String sql=" select no, first_name, last_name, email from emaillist " + 
					" order by no desc";
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				long no=rs.getInt(1);
				String firstName=rs.getString(2);
				String lastName=rs.getString(3);
				String email=rs.getString(4);
				
				EmaillistVo emaillistVo=new EmaillistVo();
				emaillistVo.setNo(no);
				emaillistVo.setFirstName(firstName);
				emaillistVo.setLastName(lastName);
				emaillistVo.setEmail(email);
				
				list.add(emaillistVo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error : "+e);
		}finally {
			
				try {
					if(conn!=null &&! conn.isClosed())
						conn.close();
					if(pstmt!=null&&!pstmt.isClosed())
						pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return list;
	}
	
	public boolean insert(EmaillistVo emaillistVo) {
		boolean result=false;
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=getConnection();
			
			String sql=" insert into emaillist\r\n" + 
					" values(null, ?, ?, ?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, emaillistVo.getFirstName());
			pstmt.setString(2, emaillistVo.getLastName());
			pstmt.setString(3, emaillistVo.getEmail());
			
			int count = pstmt.executeUpdate();
			
			result= count==-1;
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
}
