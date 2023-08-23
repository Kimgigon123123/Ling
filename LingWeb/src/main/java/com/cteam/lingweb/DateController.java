package com.cteam.lingweb;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import date.DateDAO;

@Controller
public class DateController {
	@Autowired DateDAO dao;
	
	// 여행 목록
	@RequestMapping("/travel")
	public String travel(HttpSession session, Model model) {
		session.setAttribute("active_category", "travel");
		model.addAttribute("list", dao.travel_list());
		return "travel";
	}
	
	// 맛집 목록
	@RequestMapping("/restaurant")
	public String restaurant(HttpSession session, Model model) {
		session.setAttribute("active_category", "restaurant");
		model.addAttribute("list", dao.restaurant_list());
		return "restaurant";
	}
	
	// 축제 목록
	@RequestMapping("/festival")
	public String festival(HttpSession session, Model model) {
		session.setAttribute("active_category", "festival");
		model.addAttribute("list", dao.festival_list());
		return "festival";
	}
	
	// 신규 등록 화면
	@RequestMapping("/new")
	public String date() {
		return "new";
	}
	

}
