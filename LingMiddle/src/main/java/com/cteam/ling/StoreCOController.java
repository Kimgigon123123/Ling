package com.cteam.ling;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import storeco.StoreCOVO;

@RestController
public class StoreCOController {
	@Autowired @Qualifier ("test") SqlSession sql;
	
	@RequestMapping(value="/store_co",produces="text/html;charset=utf-8")
	public String test() {
		List<StoreCOVO> list = sql.selectList("store_co.list");
		
		Gson gson = new Gson();
		
		return gson.toJson(list);
	}
	
	
	
}
