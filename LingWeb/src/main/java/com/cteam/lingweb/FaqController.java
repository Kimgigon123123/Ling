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
	
	@RequestMapping("/list")
	public String list(Model model, HttpSession session) {
	session.setAttribute("category", "faq");
	
	List<FaqVO> list = dao.list();
	
	model.addAttribute("list", list);
	return "default/faq/list";
	}
}
