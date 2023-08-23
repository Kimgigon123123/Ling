package com.cteam.lingweb;



import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import board.BoardDAO;









@Controller
public class BoardController {
	
	
	
	
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String admin(HttpSession session) {
		
		session.setAttribute("active_category", "board");
		return "board";
	}
	
}
