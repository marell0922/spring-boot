package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.dto.JSONResult;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.UserVo;

@Controller
public class MainController {
	
	@Autowired
	private SiteService siteService;
	
	@RequestMapping({"/", "/main"})
	public String index(Model model) {
		//SiteVo vo=siteService.getSite();
		model.addAttribute("siteVo", siteService.getData());
		return "main/index";
	}

	/*@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "<h1>안녕하세요?</h1>"; // 한글로 하게 되면 깨지게 된다. -> Spring http messageConverter 설정
	}
	*/
	/*@ResponseBody
	@RequestMapping("/hello2")
	public UserVo hello2() {
		
		JSONResult jsoin= JSONResult.success(); 
		
	}*/
}
