package com.cteam.ling;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import schedule.ScheAddVO;
import schedule.ScheDAO;

@RestController
public class ScheduleController {
	
	
	@Autowired ScheDAO dao;
	
	@RequestMapping(value="/sche_list",produces="text/html;charset=utf-8")
	public String sche_List(String id, String couple_num) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("couple_num", couple_num);
		
		List<ScheAddVO> list = dao.getList(param) ;
		Gson gson = new Gson();	
		
		return gson.toJson(list);
	}
	
	@RequestMapping(value="/sche_insert",produces="text/html;charset=utf-8")
	public String sche_Insert(ScheAddVO vo) {
		
		int result = dao.insert(vo);
		
		Gson gson = new Gson();
		
		return gson.toJson(result);
	}
	
	@RequestMapping(value="/sche_delete",produces="text/html;charset=utf-8")
	public String sche_Delete(ScheAddVO vo) {
		
		int result = dao.delete(vo);
		
		Gson gson = new Gson();
		
		return gson.toJson(result);
	}
	
	@RequestMapping(value="/sche_update",produces="text/html;charset=utf-8")
	public String sche_Update(ScheAddVO vo) {
		int result = dao.update(vo);
		
		Gson gson = new Gson();
		return gson.toJson(result);
	}
	
	@RequestMapping(value="/sche_dday",produces="text/html;charset=utf-8")
	public String sche_Dday(ScheAddVO vo, String id, String couple_num, String create_date) {
//		HashMap<>
		List<ScheAddVO> list = dao.getDday(vo) ;
		Gson gson = new Gson();	
		
		return gson.toJson(list);
	}
	
	
}
