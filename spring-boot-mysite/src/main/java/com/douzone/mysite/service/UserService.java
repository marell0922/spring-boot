package com.douzone.mysite.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.douzone.mysite.repository.UserDao;
import com.douzone.mysite.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public void join(final UserVo userVo){
		// TODO Auto-generated method stub
		//1. insert
		userDao.insert(userVo);
		//2. email 확인 메일 보내기.
	}

	/*public ModelAndView login(HttpSession session,final UserVo userVo) {
		// TODO Auto-generated method stub
		//1.login
		
		ModelAndView mav=new ModelAndView();
		
		UserVo resultVo=userDao.get(userVo.getEmail(), userVo.getPassword());
		
		if(resultVo!=null) {
			session.setAttribute("authuser", resultVo);
			mav.setViewName("redirect:/");
		}else {
			mav.addObject("result", false);
			mav.setViewName("/user/loginform");
		}
		
		return mav;
	}*/
	
	public UserVo getUser(final String email, final String password) {
		return userDao.get(email, password);
	}
	
	/*@Auth*/
	public UserVo modifyView(/*HttpSession session*/ UserVo uservo) {//HttpSession session
		// TODO Auto-generated method stub
			return userDao.get(uservo.getNo());
	}

	public void modify(final UserVo userVo) {
		// TODO Auto-generated method stub
		userDao.modify(userVo);
	}

	public void logout(HttpSession session) {
		// TODO Auto-generated method stub
		UserVo userVo=(UserVo)session.getAttribute("authuser");
		
		if(userVo!=null) {
			
			session.removeAttribute("authuser");
			session.invalidate();
		}
	}

	public boolean existEmail(String email) {
		// TODO Auto-generated method stub
		UserVo userVo=userDao.get(email);
		return userVo!=null;
	}

	
}
