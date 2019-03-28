package com.douzone.mysite.controller;

import java.awt.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;
import com.douzone.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		return "user/joinform";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo,
			BindingResult result,
			Model model) {
		
		if(result.hasErrors()) {
			//필드가 잘못되는 경우
			/*List<ObjectError> list=result.getAllErrors();
			for(ObjectError error : list) {
				System.out.println(error);
			}*/
			model.addAllAttributes(result.getModel());  //map의 key 값을 알필요 x -> spring에서 설정
			return "user/joinform";
		}
		
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping(value="/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/loginform";
	}
	
	@RequestMapping(value="/auth", method=RequestMethod.POST)
	public void auth() {}
	
	//@Auth
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(Model model, @AuthUser UserVo authuser/*, HttpSession session*/) {
		
		if(authuser!=null) {
			model.addAttribute("vo", userService.modifyView(authuser));
			return "user/modify";
		}
		return "redirect:/user";
	}
	
	/*@Auth*/
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@AuthUser UserVo authuser, @ModelAttribute UserVo userVo) {
		if(authuser!=null) {
			userService.modify(userVo);
		}
		
		return "redirect:/user";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logout() {}
	
	
	
	/*@ExceptionHandler(UserDaoException.class) // 상위계층에서 exception 처리.
	public String handleUserDaoException() {
		//1.logging
		//2.page forward
		return "/error/exception";
	}*/
}
