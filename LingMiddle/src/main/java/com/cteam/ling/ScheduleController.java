package com.cteam.ling;

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
	public String sche_List() {
		List<ScheAddVO> list = dao.getList() ;
		Gson gson = new Gson();	
		
		return gson.toJson(list);
	}
	
	@RequestMapping(value="/sche_insert",produces="text/html;charset=utf-8")
	public String sche_Insert(ScheAddVO vo) {
		
		int result = dao.insert(vo);
		
		Gson gson = new Gson();
		
		return gson.toJson(result);
	}
}
