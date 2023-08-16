package com.cteam.ling;

import java.util.HashMap;
import java.util.UUID;



import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ling.common.CommonUtility;
import member.MemberDAO;
import member.MemberVO;

@RestController
public class MemberController {

	@Autowired
	@Qualifier("test")
	SqlSession sql;
	@Autowired
	MemberDAO dao;

	@RequestMapping(value = "/login", produces = "text/html;charset=utf-8")
	public String login(String id, String pw) {

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		params.put("pw", pw);
		MemberVO vo = dao.login(params);

		return new Gson().toJson(vo);
	}

	@RequestMapping(value = "/findid", produces = "text/html;charset=utf-8")
	public String findid(String name, String phone) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("name", name);
		params.put("phone", phone);
		MemberVO vo = dao.findid(params);

		return new Gson().toJson(vo);
	}

	// 비밀번호 찾기 화면 요청

	@RequestMapping(value = "/resetPassword", produces = "text/html; charset=utf-8")
	public String reset(MemberVO vo) {

		// 화면에서 입력한 아이디/이메일이 일치하는 회원에게 임시 비번을 발급한다.
		String name = dao.useridEmail(vo);
		if (name == null) {

		} else {
			vo.setName(name);
			// 임시 비번을 생성한 후 DB회원정보로 저장 임시 비번을 이메일로 보내준다.
			//오라클 오류 1111 , null ==> mybatis null param처리 x
			//내가 사용하는 맵퍼에 null인 파라메터가 있다.
			String pw = UUID.randomUUID().toString(); // skjdhfk-sjkdhfk2-sdhkj3wkj영문과 숫자가 막 섞여있는 문자열이 길게 만들어진다.
			pw = pw.substring(pw.lastIndexOf("-") + 1);
			StringBuilder msg = new StringBuilder();
			vo.setPw(pw);
			if (dao.resetPassword(vo) == 1 && new CommonUtility().sendPassword(vo, pw)) {
				msg.append("alert('임시비번 발급됬으니 . \\n 이메일을 확인하세요');");
				msg.append("location='login'"); // 임시비번을 로그인하도록 로그인화면 연결
			} else {
				msg.append("alert('확sddfd인하세요');");
				msg.append("history.go(-1)");
			}
		}
		return new Gson().toJson(vo);
	}
	
	//아이디 중복확인 처리 요청
		@ResponseBody @RequestMapping("/useridCheck")
		public String useridCheck(String id) {
			//화면에서 입력한 아이디가 DB에 있는지 여부를 확인
			//DB에 없는 아이디이면 사용가능, 있는 아이디이면 사용불가능
			//dao.info(userid)==null ? true : false
			MemberVO vo = dao.info(id);
			return new Gson().toJson(vo);
		}
		
		
		
		//회원가입 처리 요청
		@ResponseBody 
		@RequestMapping(value="/register", produces="text/html; charset=utf-8")
		public String join(String dto) {
			MemberVO vo = new Gson().fromJson(dto, MemberVO.class);
			//dao.join(vo);
			//HashMap.put("id",vo.getId());
			//return new Gson().toJson(dao.login())
			return new Gson().toJson(dao.join(vo));
			
		}
		
	
		@RequestMapping(value="/member.matching", produces="text/html; charset=utf-8")
		public String matching(String dto) {
			HashMap<String, Object> receive = new Gson().fromJson(dto,  new TypeToken<HashMap<String,Object>>(){}.getType());
			String mid = receive.get("mid").toString();
			String fid = receive.get("fid").toString();
			String gender = receive.get("gender").toString();
			//gender 가 M/상대는 fid 
			//gender 가 F/상대는 mid
			HashMap<String, Object> map = new HashMap<String, Object>();
			MemberVO member = null; //fid 회원정보 조회
			if( gender.equals("남") ) {
				//모든 회원중에 fid 인 회원의 성별이 M 이면 동성의 상대는 X
				member = dao.info(fid); //fid 회원정보 조회
				
			}else {
				member = dao.info(mid); //mid 회원정보 조회
			}
			map.put("status", member==null ? "N" : ( member.getGender().equals(gender) ? "T" : "F") );
			map.put("couple", member);
			
			
			map.put("mid", mid);
			map.put("fid", fid);
			
			if( map.get("status").equals("F") ) {
				dao.matching(map);
			}
			
			return new Gson().toJson(map);	
			
		}
		

		
		
}
