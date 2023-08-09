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
	
	// 관심목록_여행
	@RequestMapping(value="/date_tourdibs", produces = "text/html;charset=utf-8")
	public String tourDibs() {
		List<DateDibsVO> tourdibs = sql.selectList("date.tourdibs");
		return new Gson().toJson(tourdibs);
	}
	
	// 관심목록_맛집
	@RequestMapping(value="/date_restaurantdibs", produces = "text/html;charset=utf-8")
	public String RestaurantDibs() {
		List<DateDibsVO> restaurantdibs = sql.selectList("date.restaurantdibs");
		return new Gson().toJson(restaurantdibs);
	}
	
	// 관심목록_축제
	@RequestMapping(value="/date_festivaldibs", produces = "text/html;charset=utf-8")
	public String FestivalDibs() {
		List<DateDibsVO> festivaldibs = sql.selectList("date.festivaldibs");
		return new Gson().toJson(festivaldibs);
	}
}
