package com.cteam.ling;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import chat.ChatVO;
import main.MainVO;
import storeco.StoreCOVO;

@RestController
public class MainController {

	@Autowired
	@Qualifier("test")
	SqlSession sql;
	
	// 커플 로그인 했을때 커플 정보 보여주기 ( 가장 첫번째 화면 )
		@RequestMapping(value = "/select_couple_info", produces = "text/html;charset=utf-8")
		public String select_couple_info(MainVO vo) {
			List<MainVO> list = sql.selectList("main.select_couple_info",vo);
			Gson gson = new Gson();
			return gson.toJson(list);
		}
		

		
	
}
