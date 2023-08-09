package com.cteam.ling;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import board.BoardDAO;

@RestController
public class BoardController {
	@Autowired @Qualifier("test") SqlSession sql;
	@Autowired BoardDAO dao;
	
	//공지사항 목록 조회
	@RequestMapping(value="/board.noticeselect", produces = "text/html;charset=utf-8")
	public String noticelist() {
		
		return new Gson().toJson(dao.noticelist());		
	}
	
	//자유게시판 목록 조회
	@RequestMapping(value="/board.freeselect", produces = "text/html;charset=utf-8")
	public String freelist() {
		
		return new Gson().toJson(dao.freeselect());		
	}
	
	//고민상담소 목록 조회
	@RequestMapping(value="/board.worryselect", produces = "text/html;charset=utf-8")
	public String worrylist() {
			
		return new Gson().toJson(dao.worryselect());		
	}	

	//고민상담소 목록 조회
	@RequestMapping(value="/board.playselect", produces = "text/html;charset=utf-8")
	public String playlist() {
				
		return new Gson().toJson(dao.playselect());		
	}		
}
