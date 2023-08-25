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
import org.springframework.web.bind.annotation.ResponseBody;

import member.MemberDAO;
import member.MemberVO;

@Controller 
public class MemberController {

	@Autowired
	@Qualifier("test")
	SqlSession sql;
	@Autowired
	MemberDAO dao;

//	@RequestMapping(value = "default/login", produces = "text/html;charset=utf-8")
//	public String login(String id, String pw) {
//
//		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("id", id);
//		params.put("pw", pw);
//		MemberVO vo = dao.login(params);
//		if(vo==null) {
//			return "default/login";
//		}else {
//			if(vo.getAdmin().equals("Y")) {
//				return "admin";
//			}else {
//				return "default/login";
//			}
//		}
//		
//	}
	
	@RequestMapping("default/login") @ResponseBody
	public String admin(HttpSession session , String id, String pw) {
		// ajax처리 필요함. 로그인 성공 실패에 따라서 스크립트로 페이지 이동.
		// Session에 로그인 정보 담기,.
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		params.put("pw", pw);
		MemberVO vo = dao.login(params);
		if(vo==null) {
			return "failure";
		}else {
			if(vo.getAdmin().equals("Y")) {
				session.setAttribute("loginId", id);
				return "success";
			}else {
				return "failure";
			}
		}
	}
	
	@RequestMapping("/admin")
	public String admin(HttpSession session) {
		
		session.setAttribute("active_category", "admin");
		return "admin";
	}
	
//	@RequestMapping("default/login")
//	@ResponseBody
//	public String admin(HttpSession session , String id, String pw) {
//		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("id", id);
//		params.put("pw", pw);
//		MemberVO vo = dao.login(params);
//	    if (vo == null) {
//	        return "failure";
//	    } else {
//	        if (vo.getAdmin().equals("Y")) {
//	            // 세션에 로그인한 아이디 저장
//	            session.setAttribute("loginId", id);
//	            return "success";
//	        } else {
//	            return "failure";
//	        }
//	    }
//	}
	

	
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
