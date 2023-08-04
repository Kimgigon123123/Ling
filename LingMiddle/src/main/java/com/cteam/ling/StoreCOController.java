package com.cteam.ling;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import storeco.StoreCOVO;
import storeco.StorePurchaseListVO;

@RestController
public class StoreCOController {
	@Autowired @Qualifier ("test") SqlSession sql;
	
	@RequestMapping(value="/store_co",produces="text/html;charset=utf-8")
	public String test() {
		List<StoreCOVO> list = sql.selectList("store_co.list");
		
		Gson gson = new Gson();
		
		return gson.toJson(list);
	}
	
	//물건 구매했을때 구매목록에 추가
	
	@RequestMapping(value="/insert_purchase",produces="text/html;charset=utf-8")
	public String insert_purchase(StorePurchaseListVO vo) {
		int result = sql.insert("store_co.insert_purchase",vo);
		
		Gson gson = new Gson();
		
		return gson.toJson(result);
	}
	
	//구매한 물건 구매목록에 보이게 하기
	
	@RequestMapping(value="/list_purchase",produces="text/html;charset=utf-8")
	public String list_purchase(StorePurchaseListVO vo) {
		List<StoreCOVO> list = sql.selectList("store_co.list_purchase",vo);
		
		Gson gson = new Gson();
		
		return gson.toJson(list);
	}
	
	
	
}
