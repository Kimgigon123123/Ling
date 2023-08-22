package com.cteam.lingweb;

import java.util.HashMap;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import member.MemberDAO;
import member.MemberVO;

@Controller
public class MemberController {

	@Autowired
	@Qualifier("test")
	SqlSession sql;
	@Autowired
	MemberDAO dao;

	@RequestMapping(value = "/adminlogin", produces = "text/html;charset=utf-8")
	public String login(String id, String pw) {

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		params.put("pw", pw);
		MemberVO vo = dao.login(params);
		if(vo==null) {
			return "login";
		}else {
			if(vo.getAdmin().equals("Y")) {
				return "admin";
			}else {
				return "login";
			}
		}
		
	}

	
		

		
		
}
