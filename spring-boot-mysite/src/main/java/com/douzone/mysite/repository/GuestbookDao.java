package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.GuestbookException;
import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	@Autowired
	private SqlSession sqlSession;
	
	public int delete( GuestbookVo vo ) throws GuestbookException{
		return sqlSession.delete("guestbook.delete", vo);
	}

	public long insert(GuestbookVo vo) throws GuestbookException{
		 sqlSession.insert("guestbook.insert", vo);
		 return vo.getNo();
	}

	public List<GuestbookVo> getList() throws GuestbookException{
		List<GuestbookVo> list=sqlSession.selectList("guestbook.getlist");
		return list;
	}

	public GuestbookVo getVo(long no) throws GuestbookException{
		// TODO Auto-generated method stub
		return sqlSession.selectOne("guestbook.getByNo", no);	
	}	
	
	
}
