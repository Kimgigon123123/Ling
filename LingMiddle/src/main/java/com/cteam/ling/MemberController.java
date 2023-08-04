package com.cteam.ling;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import login.MemberDAO;
import login.MemberVO;
import test.TestDAO;



@RestController
public class MemberController {
	
	@Autowired @Qualifier("test") SqlSession sql;

	@Autowired TestDAO dao;
	@RequestMapping(value="/login", produces = "text/html;charset=utf-8")
	public String amlogin(String id , String pw ) {
	
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		params.put("pw", pw);
	//	Ling_MemberVO vo = dao.login(params);
		
		
		return new Gson().toJson("");
	}
	
}
