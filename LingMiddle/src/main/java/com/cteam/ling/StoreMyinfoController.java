package com.cteam.ling;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;



import storemyinfo.StoreMyinfoVO;

@RestController
public class StoreMyinfoController {

@Autowired @Qualifier ("test") SqlSession sql;
	
	@RequestMapping(value="/store_myinfo",produces="text/html;charset=utf-8")
	public String StoreMyinfo() {
		List<StoreMyinfoVO> list = sql.selectList("store_myinfo.myinfo");
		
		Gson gson = new Gson();
		
		return gson.toJson(list);
	}
	
	
	@RequestMapping(value="/store_charge",produces="text/html;charset=utf-8")
	public String charge(int edtMoney) {
		
		int result = sql.update("store_myinfo.charge",edtMoney);
		System.out.println("성공여부"+result);
		
		
		Gson gson = new Gson();
		return gson.toJson(result);
		
	}
	
	
	@RequestMapping(value="/store_buy",produces="text/html;charset=utf-8")
	public String buy(int totalPrice) {
		
		int result = sql.update("store_myinfo.buy",totalPrice);
		System.out.println("성공여부"+result);
		
		
		Gson gson = new Gson();
		return gson.toJson(result);
		
	}
	
	
}
