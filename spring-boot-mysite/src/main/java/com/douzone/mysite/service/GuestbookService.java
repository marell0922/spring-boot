package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.douzone.mysite.repository.GuestbookDao;
import com.douzone.mysite.vo.GuestbookVo;
@Service
public class GuestbookService {
	@Autowired
	private GuestbookDao guestbookDao;

	public List<GuestbookVo> list() {
		// TODO Auto-generated method stub
		return guestbookDao.getList();
	}

	public void insert(GuestbookVo guestbookVo) {
		// TODO Auto-generated method stub
		guestbookDao.insert(guestbookVo);
	}

	public void delete(GuestbookVo guestbookVo) {
		// TODO Auto-generated method stub
		System.out.println("delete : "+ guestbookVo);
		guestbookDao.delete(guestbookVo);
	}
}
