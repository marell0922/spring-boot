package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.SiteVo;

@Repository
public class SiteDao {
	
	@Autowired
	private SqlSession sqlSession;

	public SiteVo getData() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("site.getData");
	}

	public void update(SiteVo siteVo) {
		// TODO Auto-generated method stub
		sqlSession.update("site.update", siteVo);
	}

}
