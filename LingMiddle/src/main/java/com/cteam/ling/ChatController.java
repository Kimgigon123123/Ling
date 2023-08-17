package com.cteam.ling;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import chat.ChatVO;

@RestController
public class ChatController {

	@Autowired
	@Qualifier("test")
	SqlSession sql;
	
	//누구와 채팅하는지 알려주기
	@RequestMapping(value = "/select_couple_name", produces = "text/html;charset=utf-8")
	public String select_couple_name(ChatVO vo) {
		List<ChatVO> list = sql.selectList("chat.select_couple_name", vo);
		Gson gson = new Gson();
		return gson.toJson(list);
	}	

	// 채팅창 정보 보여주기
	@RequestMapping(value = "/select_chat", produces = "text/html;charset=utf-8")
	public String select_chat(ChatVO vo) {
		List<ChatVO> list = sql.selectList("chat.select_chat", vo);
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	// 채팅하기
	@RequestMapping(value = "/insert_chat", produces = "text/html;charset=utf-8")
	public String insert_chat(ChatVO vo) {
		int result = sql.insert("chat.insert_chat", vo);
		Gson gson = new Gson();
		return gson.toJson(result);
	}

}
