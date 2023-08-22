package com.cteam.lingweb;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		
		return "default/home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		
		
		return "default/login";
	}
	
	@RequestMapping(value = "/store", method = RequestMethod.GET)
	public String store(HttpSession session) {
		session.setAttribute("active_category", "store");
		return "store";
	}
	
	@RequestMapping(value = "/addstore", method = RequestMethod.GET)
	public String addproduct() {

		
		return "addstore";
	}
	
	@RequestMapping(value = "/travel", method = RequestMethod.GET)
	public String travel(HttpSession session) {
		session.setAttribute("active_category", "travel");
		
		return "travel";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(HttpSession session) {
		
		session.setAttribute("active_category", "admin");
		return "admin";
	}
	

}
