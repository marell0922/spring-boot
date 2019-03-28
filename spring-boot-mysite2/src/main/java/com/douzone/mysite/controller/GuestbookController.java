package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value="")
	public String list(Model model) {
		model.addAttribute("list", guestbookService.list());
		return "/guestbook/list";
	}
	
	@RequestMapping(value="/insert")
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		guestbookService.insert(guestbookVo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(Model model, @PathVariable("no") long no) {
		model.addAttribute("no", no);
		return "/guestbook/deleteform";
	}
	
	@RequestMapping(value="/delete",  method=RequestMethod.POST)
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		
		/////////////////// 수정 할 사항.
		guestbookService.delete(guestbookVo);
		return "redirect:/guestbook";
	}
	
	
}
