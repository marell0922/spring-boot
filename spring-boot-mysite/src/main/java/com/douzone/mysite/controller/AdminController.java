package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.security.Auth;
import com.douzone.security.Auth.Role;

@Controller
@Auth(Role.ADMIN)
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private FileUploadService fileuploadService;

	@RequestMapping({"", "/main"})
	public String main(Model model) {
		model.addAttribute("siteVo", siteService.getData());
		return "/admin/main";
	}
	
	@RequestMapping("/main/update")
	public String mainUpdate(@ModelAttribute SiteVo siteVo, @RequestParam(value="profiles")MultipartFile profiles) {
		
		String profileUrl=fileuploadService.restore(profiles);
		
		siteVo.setProfile(profileUrl);
		System.out.println(siteVo);
		siteService.update(siteVo);
		return "redirect:/admin/main";
	}
	
	@RequestMapping("/board")
	public String board(){
		return "/admin/board";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "/admin/guestbook";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "/admin/user";
	}
}
