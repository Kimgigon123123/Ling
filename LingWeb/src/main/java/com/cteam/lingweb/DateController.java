package com.cteam.lingweb;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import date.DateDAO;
import date.DateVO;

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
	
	// 상세 정보 화면
	@RequestMapping(value="/info", method = RequestMethod.GET)
	public String info(Model model, int date_id) {
		model.addAttribute("vo", dao.date_info(date_id));
		return "info";
	}
	
	// 신규 등록 화면
	@RequestMapping("/new")
	public String date() {
		return "new";
	}
	
	// 신규 등록 저장
	@RequestMapping("/register")
	public String register(DateVO vo) {
		dao.date_insert(vo);
		if(vo.getDate_category_code().equals("TO")) {
			return "redirect:travel";			
		}else if(vo.getDate_category_code().equals("RE")) {
			return "redirect:restaurant";
		}else {
			return "redirect:festival";
		}
	}
	
	// 삭제
	@RequestMapping("/deletedate")
	public String delete(DateVO vo) {
		dao.date_delete(vo);
		if(vo.getDate_category_code().equals("TO")) {
			return "redirect:travel";			
		}else if(vo.getDate_category_code().equals("RE")) {
			return "redirect:restaurant";
		}else {
			return "redirect:festival";
		}
	}
	
	// 다중삭제
	@RequestMapping("/multipledelete")
	@ResponseBody
	public void multipledelete(DateVO vo, String tdata) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("date_category_code", vo.getDate_category_code());
		map.put("date_id", tdata);
		dao.date_delete(map);
	}
	
	// 수정 화면 요청
	@RequestMapping("/modify")
	public String modify(@RequestParam Integer date_id, Model model) {
		model.addAttribute("vo", dao.date_info(date_id));
		return "modify";
	}
	
	// 수정 저장
	@RequestMapping("/update")
	public String update(DateVO vo) {
		dao.date_update(vo);
		return "redirect:info?date_id=" + vo.getDate_id();
	}

}
