package com.cteam.ling;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import date.DateDibsVO;
import date.DateInfoVO;

@RestController
public class DateController {

	@Autowired
	@Qualifier("test")
	SqlSession sql;

	// 시도
	@RequestMapping(value = "/date_sido", produces = "text/html;charset=utf-8")
	public String sido() {
		List<DateInfoVO> sido = sql.selectList("date.sido");
		return new Gson().toJson(sido);
	}
	
	// 시군구
	@RequestMapping(value="/date_sigungu", produces = "text/html;charset=utf-8")
	public String sigungu(String sido) {
		List<DateInfoVO> sigungu = sql.selectList("date.sigungu", sido);
		return new Gson().toJson(sigungu);
	}
	
	// 관심목록_전체
	@RequestMapping(value="/date_alldibs", produces = "text/html;charset=utf-8")
	public String allDibs() {
		List<DateDibsVO> alldibs = sql.selectList("date.alldibs");
		return new Gson().toJson(alldibs);
	}

}
