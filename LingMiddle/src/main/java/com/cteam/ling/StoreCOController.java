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
	
	//최신순
	@RequestMapping(value="/store_by_recent",produces="text/html;charset=utf-8")
	public String by_recent() {
		List<StoreCOVO> list = sql.selectList("store_co.by_recent");
		
		Gson gson = new Gson();
		
		return gson.toJson(list);
	}
	
	//이름순
	@RequestMapping(value="/store_by_name",produces="text/html;charset=utf-8")
	public String by_name() {
		List<StoreCOVO> list = sql.selectList("store_co.by_name");
		
		Gson gson = new Gson();
		
		return gson.toJson(list);
	}
	
	
	//아이템 누를때마다 인기도 증가
		@RequestMapping(value="/store_popular_up",produces="text/html;charset=utf-8")
		public String popular_up(int item_code) {
			int result = sql.update("store_co.popular_up",item_code);
			
			Gson gson = new Gson();
			
			return gson.toJson(result);
		}
		
		//인기순
		@RequestMapping(value="/store_by_popular",produces="text/html;charset=utf-8")
		public String by_popular() {
			List<StoreCOVO> list = sql.selectList("store_co.by_popular");
			
			Gson gson = new Gson();
			
			return gson.toJson(list);
		}
	
	
	// 가격순
		@RequestMapping(value="/store_by_price",produces="text/html;charset=utf-8")
		public String by_price() {
			List<StoreCOVO> list = sql.selectList("store_co.by_price");
			
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
