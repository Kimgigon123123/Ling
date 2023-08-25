package com.cteam.lingweb;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import chart.ChartDAO;
import store.StoreVO;


@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		
		return "default/home";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		
		
		return "default/login";
	}
	
	
	
//	@RequestMapping(value = "/date", method = RequestMethod.GET)
//	public String date(HttpSession session) {
//		session.setAttribute("active_category", "date");
//		
//		return "date";
//	}
	
	
	@Autowired private ChartDAO service;
	
	@ResponseBody @RequestMapping("/age")
	public Object age() {
		//DB에서 연령별 유저수 조회
		return service.age();
	}
	
	@ResponseBody @RequestMapping("/period")
	public Object period() {
		//DB에서 부서별 사원수를 조회해와 응답한다.
		return service.period();
	}
	
	
	@ResponseBody @RequestMapping("/item_rank")
	public Object item_rank() {
		return service.item_top3();
	}
	

}
