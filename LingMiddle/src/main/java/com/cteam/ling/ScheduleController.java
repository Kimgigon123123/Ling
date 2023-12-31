package com.cteam.ling;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import schedule.ScheAddVO;
import schedule.ScheDAO;

@RestController
public class ScheduleController {
	
	
	@Autowired ScheDAO dao;
	
	//일정 메인화면
	@RequestMapping(value="/sche_list",produces="text/html;charset=utf-8")
	public String sche_List(String id, String couple_num) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("couple_num", couple_num);
		
		List<ScheAddVO> list = dao.getList(param) ;
		Gson gson = new Gson();	
		
		return gson.toJson(list);
	}
	
	//일정 메인화면에서 d-day가 음수가 될 경우
	@RequestMapping(value="/plus_List",produces="text/html;charset=utf-8")
	public String plus_List(String id, String couple_num) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("couple_num", couple_num);
		
		List<ScheAddVO> list = dao.plus_List(param) ;
		Gson gson = new Gson();	
		
		return gson.toJson(list);
	}
	
	
	//일정 추가
	@RequestMapping(value="/sche_insert",produces="text/html;charset=utf-8")
	public String sche_Insert(ScheAddVO vo) {
		
		int result = dao.insert(vo);
		
		Gson gson = new Gson();
		
		return gson.toJson(result);
	}
	
	//일정 삭제
	@RequestMapping(value="/sche_delete",produces="text/html;charset=utf-8")
	public String sche_Delete(ScheAddVO vo) {
		
		int result = dao.delete(vo);
		
		Gson gson = new Gson();
		return gson.toJson(result);
	}
	
	//일정 수정
	@RequestMapping(value="/sche_update",produces="text/html;charset=utf-8")
	public String sche_Update(ScheAddVO vo) {
		int result = dao.update(vo);
		
		Gson gson = new Gson();
		return gson.toJson(result);
	}
	
	
	//일정 d-day 
	@RequestMapping(value="/sche_dday",produces="text/html;charset=utf-8")
	public String sche_Dday(String id, String couple_num, ScheAddVO vo) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("couple_num", couple_num);
		List<ScheAddVO> list = dao.getDday(vo) ;
		Gson gson = new Gson();	
		
		return gson.toJson(list);
	}
	
	
}
