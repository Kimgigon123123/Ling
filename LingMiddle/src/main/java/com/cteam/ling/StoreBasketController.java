package com.cteam.ling;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import storebasket.StoreBasketVO;
import storeco.StorePurchaseListVO;
import storemyinfo.StoreMyinfoVO;

@RestController
public class StoreBasketController {
	
	@Autowired
	@Qualifier("test")
	SqlSession sql;
	
	@RequestMapping(value="/store_list_basket",produces="text/html;charset=utf-8")
	public String list_basket(StoreBasketVO vo) {
		List<StoreBasketVO> list = sql.selectList("store_basket.list_basket",vo);
		
		Gson gson = new Gson();
		
		return gson.toJson(list);
	}
	
	//장바구니 추가
	@RequestMapping(value="/store_insert_basket",produces="text/html;charset=utf-8")
	public String insert_basket(StoreBasketVO vo) {
		int result = sql.insert("store_basket.insert_basket",vo);
		
		Gson gson = new Gson();
		
		return gson.toJson(result);
	}
	
	//장바구니 갯수 추가
	@RequestMapping(value="/store_up_selection",produces="text/html;charset=utf-8")
	public String up_selection(StoreBasketVO vo) {
		int result = sql.update("store_basket.up_selection",vo);
		
		Gson gson = new Gson();
		
		return gson.toJson(result);
	}
	
	//장바구니 갯수 감소
	@RequestMapping(value="/store_down_selection",produces="text/html;charset=utf-8")
	public String down_selection(StoreBasketVO vo) {
		int result = sql.update("store_basket.down_selection",vo);
		
		Gson gson = new Gson();
		
		return gson.toJson(result);
	}
	
	//장바구니 금액 총합 보여주기
	@RequestMapping(value="/store_basket_totalprice",produces="text/html;charset=utf-8")
	public String basket_totalprice(StoreBasketVO vo) {
		List<StoreBasketVO> list = sql.selectList("store_basket.basket_totalprice",vo);
		
		Gson gson = new Gson();
		
		return gson.toJson(list);
	}
	
	//장바구니 삭제
	@RequestMapping(value="/store_delete_basket",produces="text/html;charset=utf-8")
	public String delete_basket(StoreBasketVO vo) {
		int result = sql.delete("store_basket.delete_basket",vo);
		
		Gson gson = new Gson();
		
		return gson.toJson(result);
	}
	
	//장바구니에서 구매한 물품을 구매한 목록에 넣기
	@RequestMapping(value="/insert_basket_buylist",produces="text/html;charset=utf-8")
	public String insert_basket_buylist(StorePurchaseListVO vo) {
		int result = sql.delete("store_basket.insert_basket_buylist",vo);
		
		Gson gson = new Gson();
		
		return gson.toJson(result);
	}
	
	//장바구니에서 구매하고 구매한 목록 삭제 시키기
	@RequestMapping(value="/delete_basket_buylist",produces="text/html;charset=utf-8")
	public String delete_basket_buylist(StorePurchaseListVO vo) {
		int result = sql.delete("store_basket.delete_basket_buylist",vo);
		
		Gson gson = new Gson();
		
		return gson.toJson(result);
	}
	
	
}
