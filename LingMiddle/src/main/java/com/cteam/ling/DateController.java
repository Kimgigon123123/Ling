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
import member.MemberVO;

@RestController
public class DateController {

	@Autowired
	@Qualifier("test")
	SqlSession sql;

	// 시도
	@RequestMapping(value = "/date_sido", produces = "text/html;charset=utf-8")
	public String sido() {
		List<String> sido = sql.selectList("date.sido");
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
	public String tourList(String id) {
		List<DateInfoVO> tourlist = sql.selectList("date.tour", id);
		return new Gson().toJson(tourlist);
	}

	// 맛집목록_전체
	@RequestMapping(value = "/date_restaurant", produces = "text/html;charset=utf-8")
	public String restaurantList(String id) {
		List<DateInfoVO> restaurantList = sql.selectList("date.restaurant", id);
		return new Gson().toJson(restaurantList);
	}

	// 축제목록_전체
	@RequestMapping(value = "/date_festival", produces = "text/html;charset=utf-8")
	public String festivalList(String id) {
		List<DateInfoVO> festivalList = sql.selectList("date.festival", id);
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
	
	// 여행 검색
	@RequestMapping(value="/date_searchtour", produces="text/html;charset=utf-8")
	public String searchTour(DateInfoVO vo) {
		List<DateInfoVO> searchTour = sql.selectList("date.searchtour", vo);
		return new Gson().toJson(searchTour);
	}
	
	// 맛집 검색
	@RequestMapping(value="/date_searchrest", produces="text/html;charset=utf-8")
	public String searchRestaurant(DateInfoVO vo) {
		List<DateInfoVO> searchRest = sql.selectList("date.searchrest", vo);
		return new Gson().toJson(searchRest);
	}
	
	// 축제 검색
	@RequestMapping(value="/date_searchfest", produces="text/html;charset=utf-8")
	public String searchFestival(DateInfoVO vo) {
		List<DateInfoVO> searchFest = sql.selectList("date.searchfest", vo);
		return new Gson().toJson(searchFest);
	}
	
	// 여행 목록 5개
	@RequestMapping(value="/date_tour5", produces="text/html;charset=utf-8")
	public String tour5() {
		List<DateInfoVO> tour5 = sql.selectList("date.tour5");
		return new Gson().toJson(tour5);
	}
	
	// 맛집 목록 5개
	@RequestMapping(value="/date_restaurant5", produces="text/html;charset=utf-8")
	public String restaurant5() {
		List<DateInfoVO> restaurant5 = sql.selectList("date.restaurant5");
		return new Gson().toJson(restaurant5);
	}
	
	// 축제 목록 5개
	@RequestMapping(value="/date_festival5", produces="text/html;charset=utf-8")
	public String festival5() {
		List<DateInfoVO> festival5 = sql.selectList("date.festival5");
		return new Gson().toJson(festival5);
	}
	
	// 상대방 위도경도
	@RequestMapping(value="/select_location", produces="text/html;charset=utf-8")
	public String location(MemberVO vo) {
		MemberVO location = sql.selectOne("date.location", vo);
		return new Gson().toJson(location);
	}
	
}
