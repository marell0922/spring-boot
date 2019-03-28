package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.BoardDaoException;
import com.douzone.mysite.vo.BoardVo;
@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;

	public boolean delete(long no) throws BoardDaoException {
		// TODO Auto-generated method stub
		
		return 1==  sqlSession.delete("board.delete", no);
	}

	public BoardVo getVo(long no) throws BoardDaoException{
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.getVo", no);
	}
	
	public void upHit(long no) throws BoardDaoException{
	    sqlSession.update("board.upHit", no);
	   }

	public boolean modify(BoardVo vo) throws BoardDaoException{
		// TODO Auto-generated method stub
		return 1== sqlSession.update("board.modify", vo);
	}

	public boolean insert(BoardVo vo) throws BoardDaoException{
		// TODO Auto-generated method stub
		return 1== sqlSession.insert("board.insert", vo);
	}

	public List<BoardVo> getList(String keyword, int currentPage) throws BoardDaoException{
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("currentPage", 10*(currentPage-1));
		return sqlSession.selectList("board.getList", map);
	}

	public int getCount(String keyword) throws BoardDaoException{
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.getCount", keyword);
	}

	public boolean insert_reboard(BoardVo vo) throws BoardDaoException{
		// TODO Auto-generated method stub
		System.out.println("updateon");
		sqlSession.update("board.udpateOno", vo);
		System.out.println("insert_reboard");
		
		vo.setO_no(vo.getO_no()+1);
		vo.setDept(vo.getDept()+1);
		return 1==sqlSession.insert("board.insertReboard", vo);
		
	}

}
