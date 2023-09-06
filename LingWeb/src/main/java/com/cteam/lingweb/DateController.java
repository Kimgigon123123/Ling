package com.cteam.lingweb;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import date.DateDAO;
import date.DateVO;
import date.PageVO;

@Controller
public class DateController {
	@Autowired DateDAO dao;
	
	// 여행 목록
	@RequestMapping("/travel")
	public String travel(HttpSession session, Model model, PageVO page) {
		session.setAttribute("active_category", "travel");
		model.addAttribute("page", dao.travel_list(page));
		return "travel";
	}
	
	// 맛집 목록
	@RequestMapping("/restaurant")
	public String restaurant(HttpSession session, Model model, PageVO page) {
		session.setAttribute("active_category", "travel");
		model.addAttribute("page", dao.restaurant_list(page));
		return "restaurant";
	}
	
	// 축제 목록
	@RequestMapping("/festival")
	public String festival(HttpSession session, Model model, PageVO page) {
		session.setAttribute("active_category", "travel");
		model.addAttribute("page", dao.festival_list(page));
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
	
	String ip = "192.168.0.28";
	
	// 신규 등록 저장
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String register(DateVO vo, MultipartFile file, HttpServletRequest request) throws Exception {
		if(file != null && !file.isEmpty()) {
			String uploadPath="D:\\lingimg";
			String filename = file.getOriginalFilename();
			File filePath = new File(uploadPath, filename);
			String date_img = "http://"+ip+":"+request.getLocalPort()+"/ling/image"+"/date/"+filename;
			vo.setDate_img(date_img);
			 file.transferTo(filePath);
		}
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
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(DateVO vo, Model model, MultipartFile file, HttpServletRequest request) throws Exception {
		if(file != null && !file.isEmpty()) {
			String uploadPath="D:\\lingimg";
			String filename = file.getOriginalFilename();
			File filePath = new File(uploadPath, filename);
			String date_img = "http://"+ip+":"+request.getLocalPort()+"/ling/image"+"/date/"+filename;
			vo.setDate_img(date_img);
			 file.transferTo(filePath);
		}
		dao.date_update(vo);
		return "redirect:info?date_id=" + vo.getDate_id();
	}

}
