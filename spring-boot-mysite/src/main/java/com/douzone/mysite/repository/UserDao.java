package com.douzone.mysite.repository;


import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.douzone.mysite.exception.UserDaoException;
import com.douzone.mysite.vo.UserVo;
@Repository
public class UserDao {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo get(String email, String password) throws UserDaoException{

		Map<String, String> map=new HashMap<String, String>();
		map.put("email", email);
		map.put("password", password);
		return sqlSession.selectOne("user.getByEmailAndPassword", map);
	}
	
	public UserVo get(long no) throws UserDaoException{
		return sqlSession.selectOne("user.getByNo", no);
	}
	
	public int insert(UserVo uservo) throws UserDaoException{
		return sqlSession.insert("user.insert", uservo);
	}

	public boolean modify(UserVo vo) throws UserDaoException{
		// TODO Auto-generated method stub
		return 1== sqlSession.update("user.modify", vo);

	}

	public UserVo get(String email) throws UserDaoException{
		// TODO Auto-generated method stub
		return sqlSession.selectOne("user.getByEmail", email);
	}	
}