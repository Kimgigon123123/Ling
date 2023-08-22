package com.cteam.ling;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import capsule.CapsuleVO;
import storemyinfo.StoreZzimListVO;

@RestController
public class CapsuleController {

	@Autowired
	@Qualifier("test")
	SqlSession sql;

	// 타임캡슐 추가
	@RequestMapping(value = "/capsule_insert", produces = "text/html;charset=utf-8")
	public String capsule_insert(CapsuleVO vo) {
		int result = sql.insert("capsule.capsule_insert", vo);
		Gson gson = new Gson();
		return gson.toJson(result);
	}

	// select capsule
	@RequestMapping(value = "/capsule_select", produces = "text/html;charset=utf-8")
	public String capsule_select(CapsuleVO vo) {
		List<CapsuleVO> list = sql.selectList("capsule.capsule_select", vo);
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	// select letter
	@RequestMapping(value = "/letter_select", produces = "text/html;charset=utf-8")
	public String letter_select(CapsuleVO vo) {
		List<CapsuleVO> list = sql.selectList("capsule.letter_select", vo);
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	// update capsule_state
	@RequestMapping(value = "/update_capsule_state", produces = "text/html;charset=utf-8")
	public String update_capsule_state(CapsuleVO vo) {
		int result = sql.update("capsule.update_capsule_state", vo);
		Gson gson = new Gson();
		return gson.toJson(result);
	}

	// update capsule_state
	@RequestMapping(value = "/delete_capsule", produces = "text/html;charset=utf-8")
	public String delete_capsule(CapsuleVO vo) {
		int result = sql.update("capsule.delete_capsule", vo);
		Gson gson = new Gson();
		return gson.toJson(result);
	}

}
