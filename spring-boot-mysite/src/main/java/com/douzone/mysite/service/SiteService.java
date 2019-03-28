package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.SiteDao;
import com.douzone.mysite.vo.SiteVo;

@Service
public class SiteService {

	@Autowired
	private SiteDao siteDao;
	
	public SiteVo getData() {
		// TODO Auto-generated method stub
		return siteDao.getData();
		
	}

	public void update(SiteVo siteVo) {
		// TODO Auto-generated method stub
		siteDao.update(siteVo);
	}

}
