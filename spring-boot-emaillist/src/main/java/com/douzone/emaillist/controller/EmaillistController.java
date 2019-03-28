package com.douzone.emaillist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.emaillist.dao.EmaillistDao;
import com.douzone.emaillist.vo.EmaillistVo;

@Controller
public class EmaillistController {
	
	@Autowired // 여기서 찾아서 바로 .주입가능.
	private EmaillistDao dao;
	
	
	//@ResponseBody
	/*@RequestMapping("")
	public ModelAndView index() { //ModelAndView : View와 data를 구분해서 전달한다.
		
		List<EmaillistVo> list=dao.getList();
		System.out.println(list);
		ModelAndView mav=new ModelAndView();
		mav.addObject("list",list);
		mav.setViewName("WEB-INF/views/list.jsp");
		return "list()";
		return mav; 
	}*/
	
	/*
	 *  ContextLoadListener -> contextInitialized.
	 * 
	 * */
	
	@RequestMapping("")
	public String list(Model model) {
		model.addAttribute("list", dao.getList());
		return "list";
	}
	
	@RequestMapping("/form")
	public String form() {
		return "form";
	}
	
	@RequestMapping("add")
	public String add(EmaillistVo vo) {
		dao.insert(vo);
		
		//redirect
		return "redirect:/";
	}
}
