package com.cteam.lingweb;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	

	

}
