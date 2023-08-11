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
	@RequestMapping(value = "/date_sigungu", produces = "text/html;charset=utf-8")
	public String sigungu(String sido) {
		List<String> sigungu = sql.selectList("date.sigungu", sido);
		return new Gson().toJson(sigungu);
	}

	// 관심목록_전체
	@RequestMapping(value = "/date_alldibs", produces = "text/html;charset=utf-8")
	public String allDibs(String id) {
		List<DateDibsVO> alldibs = sql.selectList("date.alldibs", id);
		return new Gson().toJson(alldibs);
	}

	// 관심목록_여행
	@RequestMapping(value = "/date_tourdibs", produces = "text/html;charset=utf-8")
	public String tourDibs(String id) {
		List<DateDibsVO> tourdibs = sql.selectList("date.tourdibs", id);
		return new Gson().toJson(tourdibs);
	}

	// 관심목록_맛집
	@RequestMapping(value = "/date_restaurantdibs", produces = "text/html;charset=utf-8")
	public String restaurantDibs(String id) {
		List<DateDibsVO> restaurantdibs = sql.selectList("date.restaurantdibs", id);
		return new Gson().toJson(restaurantdibs);
	}

	// 관심목록_축제
	@RequestMapping(value = "/date_festivaldibs", produces = "text/html;charset=utf-8")
	public String festivalDibs(String id) {
		List<DateDibsVO> festivaldibs = sql.selectList("date.festivaldibs", id);
		return new Gson().toJson(festivaldibs);
	}

	// 여행목록_전체
	@RequestMapping(value = "/date_tour", produces = "text/html;charset=utf-8")
	public String tourList() {
		List<DateInfoVO> tourlist = sql.selectList("date.tour");
		return new Gson().toJson(tourlist);
	}

	// 맛집목록_전체
	@RequestMapping(value = "/date_restaurant", produces = "text/html;charset=utf-8")
	public String restaurantList() {
		List<DateInfoVO> restaurantList = sql.selectList("date.restaurant");
		return new Gson().toJson(restaurantList);
	}

	// 축제목록_전체
	@RequestMapping(value = "/date_festival", produces = "text/html;charset=utf-8")
	public String festivalList() {
		List<DateInfoVO> festivalList = sql.selectList("date.festival");
		return new Gson().toJson(festivalList);
	}
	
	// 관심 목록 추가
	@RequestMapping(value="/date_insertdibs", produces = "text/html;charset=utf-8")
	public void insert_dibs(DateDibsVO vo) {
		int result = sql.insert("date.insert_dibs", vo);
	}
	
	// 관심 목록 제거
	@RequestMapping(value="/date_deletedibs", produces = "text/html;charset=utf-8")
	public void delete_dibs(DateDibsVO vo) {
		int result = sql.delete("date.delete_dibs", vo);
	}
	
	// 여행목록_지역별
	@RequestMapping(value="/date_tour_loc", produces = "text/html;charset=utf-8")
	public String tourList_Loc(DateInfoVO vo) {
		List<DateInfoVO> tourListLoc = sql.selectList("date.tourloc", vo);
		return new Gson().toJson(tourListLoc);
	}
	
}
