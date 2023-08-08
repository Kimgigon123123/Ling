package com.cteam.ling;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import storebasket.StoreBasketVO;
import storemyinfo.StoreMyinfoVO;

@RestController
public class StoreBasketController {
	
	@Autowired
	@Qualifier("test")
	SqlSession sql;
	
	@RequestMapping(value="/store_list_basket",produces="text/html;charset=utf-8")
	public String list_basket() {
		List<StoreBasketVO> list = sql.selectList("store_basket.list_basket");
		
		Gson gson = new Gson();
		
		return gson.toJson(list);
	}

}
