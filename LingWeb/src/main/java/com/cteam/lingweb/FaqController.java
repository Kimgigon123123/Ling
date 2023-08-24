package com.cteam.lingweb;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import faq.FaqDAO;
import faq.FaqVO;

@Controller @RequestMapping("/faq")
public class FaqController {
	
	@Autowired private FaqDAO dao;
	
	
	//FAQ 목록조회
	@RequestMapping("/list")
	public String list(Model model, HttpSession session) {
	session.setAttribute("category", "faq");
	
	List<FaqVO> list = dao.list();
	
	model.addAttribute("list", list);
	return "default/faq/list";
	}
	
	
	//신규 FAQ 글 작성화면
	@RequestMapping("/new")
	public String regist() {
		return "default/faq/new";
	}
	
	//신규 FAQ 글 등록처리
	@RequestMapping("/faq_insert")
	public String faq_insert(FaqVO vo) {
		dao.faq_insert(vo);
		return "redirect:list";
	}
	
	//FAQ 글 정보 화면요청
	@RequestMapping("/faq_info")
	public String faq_content(int id, Model model) {
		
		return "default/faq/faq_info";
	}
	
}