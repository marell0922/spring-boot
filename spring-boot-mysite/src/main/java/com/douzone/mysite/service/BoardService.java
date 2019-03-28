package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;


@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	public Map<String, Object> list(String kwd, String cPage){
		
		int currentPage=Integer.parseInt(cPage);
		System.out.println("kwd : "+kwd);
		System.out.println("cPage : "+cPage);
		List<BoardVo> list=boardDao.getList(kwd, currentPage);
		int totalCount=boardDao.getCount(kwd);
		
		//pager 알고리즘
		int maxPage=getmaxPage(totalCount);
		int prevPage=getPrevPage(currentPage, maxPage);
		int postPage=getPostPage(currentPage, maxPage);
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", totalCount);
		map.put("currentPage", currentPage);
		map.put("maxPage", maxPage);
		map.put("prevPage", prevPage);
		map.put("postPage",postPage);
		
		//System.out.println(map);
		return map;
	}
	
	public int getmaxPage(int totalCount) {
		return (totalCount%10==0)?totalCount/10:totalCount/10+1;
	}
	
	public int getPrevPage(int currentPage, int maxPage) {
		return (currentPage-2<=0)?1:(currentPage<=3)?1:(maxPage<=5)?1:(maxPage<currentPage+2)?maxPage-4:currentPage-2;
	}
	
	public int getPostPage(int currentPage, int maxPage) {
		return (currentPage+2>=maxPage)?maxPage:(currentPage<=3)?(maxPage<=5)?maxPage:5: currentPage+2;
	}

	public void write(final BoardVo boardVo) {
		// TODO Auto-generated method stub
		boardDao.insert(boardVo);
	}

	public BoardVo view(long no) {
		// TODO Auto-generated method stub
		return boardDao.getVo(no);
		
	}

	public void upHit(long no) {
		// TODO Auto-generated method stub
		boardDao.upHit(no);
	}

	public void modifyView(BoardVo vo) {
		// TODO Auto-generated method stub
		boardDao.modify(vo);
	}

	public void wrieteReboard(BoardVo vo) {
		// TODO Auto-generated method stub
		boardDao.insert_reboard(vo);
	}

	public void delete(long no) {
		// TODO Auto-generated method stub
		boardDao.delete(no);
	}

}
