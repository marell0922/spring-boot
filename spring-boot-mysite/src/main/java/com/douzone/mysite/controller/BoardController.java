package com.douzone.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.security.AuthUser;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="")
	public String list(Model model, 
			@RequestParam(value="kwd", required=false, defaultValue="") String kwd, 
			@RequestParam(value="currentPage", required=false, defaultValue="1")String currentPage) {
		model.addAllAttributes(boardService.list(kwd, currentPage));
		return "/board/list";
	}
	
	@RequestMapping(value="write", method=RequestMethod.GET)
	public String write(@AuthUser UserVo authuser) {
		
		if(authuser!=null) {
			return "/board/write";
			
		}
		return "redirect:/board";
	}
	
	@RequestMapping(value="write", method=RequestMethod.POST)
	public String write(@AuthUser UserVo authuser, @ModelAttribute BoardVo boardVo) {
		if(authuser!=null) {
			
			boardVo.setUser_no(authuser.getNo());
			
			if(boardVo.getG_no()!=0) {
				boardService.wrieteReboard(boardVo);
			}else {
				boardService.write(boardVo);
				
			}
		}
		
		return "redirect:/board";
	}
	
	@RequestMapping(value="view/{no}")
	public String view(Model model, @PathVariable(value="no") long no) {
		boardService.upHit(no);
		model.addAttribute("viewVo", boardService.view(no));
		return "/board/view";
	}
	
	@RequestMapping(value="modify/{no}", method=RequestMethod.GET)
	public String modify(Model model, @AuthUser UserVo authuser, @PathVariable("no") long no){

		if(authuser!=null) {
			BoardVo boardVo=boardService.view(no);
			model.addAttribute("modifyVo", boardVo);
			return "/board/modify";
			
		}
		return "redirect:/board";
	}
	
	@RequestMapping(value="modify", method=RequestMethod.POST)
	public String modify(@AuthUser UserVo authuser, @ModelAttribute BoardVo boardvo) {
		if(authuser!=null) {
			boardService.modifyView(boardvo);
		}
		
		return "redirect:/board";
	}
	
	@RequestMapping(value="write/{no}", method=RequestMethod.GET)
	public String writeReboard(@AuthUser UserVo authuser, Model model, @PathVariable("no") long no) {
		
		if(authuser!=null) {
			model.addAttribute("_boardVo", boardService.view(no));
			return "/board/write";
		}
		
		return "redirect:/board/view/"+no;
	}
	
	@RequestMapping(value="delete/{no}")
	public String delete(@AuthUser UserVo authuser, @PathVariable("no") long no) {

		if(authuser!=null) {
			boardService.delete(no);
		}
		return "redirect:/board";
	}

}
