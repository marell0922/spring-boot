package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.GalleryService;
import com.douzone.security.Auth;
import com.douzone.security.Auth.Role;


@Controller
@RequestMapping("/gallery")
public class GalleryController {
	@Autowired
	private GalleryService gallaryService;
	
	@Autowired
	private FileUploadService fileuploadService;
	
	@RequestMapping("")
	public String index(Model model) {
		//List list=gallaryService.getGallaryList();
		//model.addAttribute("list", list);
		return "gallery/index";
	}
	
	@Auth(Role.ADMIN)
	@RequestMapping("/update")
	public String mainUpdate( @RequestParam(value="update-image")MultipartFile image) {
		String profileUrl=fileuploadService.restore(image);
		
		return "redirect:/admin/main";
	}
}
