package com.cteam.lingweb;

import java.util.HashMap;


import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	
	@RequestMapping(value = "/lingmember2", method = RequestMethod.GET)
	public String lingmember2(HttpSession session, Model model) {
		
		session.setAttribute("active_category", "lingmember");
		model.addAttribute("list", dao.member_list());
		return "lingmember_o";
	}

	
	@RequestMapping(value = "/lingmember", method = RequestMethod.GET)
	public String lingmember(HttpSession session, Model model) {
		
		session.setAttribute("active_category", "lingmember");
		model.addAttribute("list", dao.member_list());
		return "lingmember";
	}
	
	//사원목록화면 요청
	@RequestMapping ("/list")
	public String list(HttpSession session, Model model) {
		session.setAttribute("category_active", "lingmember");
		//비지니스로직: DB에서 사원정보목록을 조회한 후
		//			목록화면에 출력할 수 있도록 Model 객체에 담는다.
		
		//사원 목록 조회
		model.addAttribute("list", dao.member_list());
		// 프리젠테이션로직: 응답화면 연결 - 목록화면
		return "lingmember";
	}
	
	@RequestMapping(value = "/detailmember", method = RequestMethod.GET)
	public String detailmember(String id, Model model) {

		model.addAttribute("vo", dao.info(id));
		return "detailmember";
	}
	
	@RequestMapping("/updatemember")
	public String update(MemberVO vo, Model model) {

		model.addAttribute("vo", dao.updatemember(vo));
		return "redirect:lingmember";
	}
		
	@RequestMapping(value = "/insertmember", method = RequestMethod.GET)
	public String insertmember() {

		//model.addAttribute(dao.join(vo));
		return "insertmember";
	}
	
	@PostMapping("/insertmember")
	public String insert(MemberVO vo) {

		dao.join(vo);
		return "redirect:lingmember";
	}
	
	@RequestMapping("/delete")
	public String delete(String ids) {
		
		dao.delete(ids);
		return "redirect:lingmember";
	}
	
	@RequestMapping("/changelist")
	public String changelist(int tablename, Model model ) {
		if(tablename==0) {
			model.addAttribute("list", dao.member_list());
			return "memberlist/folder/member";			
		}else if(tablename==1) {
			model.addAttribute("list", dao.couplelist());
			return "memberlist/folder/couple";
		}else {
			model.addAttribute("list", dao.adminlist());
			return "memberlist/folder/member";
		}
	}
		
		
}
