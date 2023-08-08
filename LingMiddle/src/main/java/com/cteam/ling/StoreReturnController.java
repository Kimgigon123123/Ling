package com.cteam.ling;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import storeco.StorePurchaseListVO;
import storemyinfo.StoreMyinfoVO;
import storereturn.StoreReturnListVO;
import storereturn.StoreReturnVO;

@RestController
public class StoreReturnController {

	@Autowired
	@Qualifier("test")
	SqlSession sql;

	@RequestMapping(value = "/store_return", produces = "text/html;charset=utf-8")
	public String Store_return(StoreReturnVO vo) {
		List<StoreReturnVO> list = sql.selectList("store_return.store_return", vo);

		Gson gson = new Gson();

		return gson.toJson(list);
	}

	@RequestMapping(value = "/store_return_money", produces = "text/html;charset=utf-8")
	public String Store_return(StoreMyinfoVO vo) {
		int result = sql.update("store_return.store_return_money", vo);

		Gson gson = new Gson();

		return gson.toJson(result);
	}

	@RequestMapping(value = "/store_buylist_delete", produces = "text/html;charset=utf-8")
	public String Store_buylist_delete(StorePurchaseListVO vo) {
		int result = sql.delete("store_return.Store_buylist_delete", vo);

		Gson gson = new Gson();

		return gson.toJson(result);
	}

	// 환불신청하면 반품목록추가
	@RequestMapping(value = "/store_insert_return", produces = "text/html;charset=utf-8")
	public String store_insert_return(StorePurchaseListVO vo) {
		int result = sql.insert("store_return.store_insert_return", vo);

		Gson gson = new Gson();

		return gson.toJson(result);
	}

	// 환불리스트 보여주기

	// 환불신청하면 반품목록추가
	@RequestMapping(value = "/store_list_return", produces = "text/html;charset=utf-8")
	public String store_list_return(StoreReturnListVO vo) {
		List<StoreReturnListVO> list = sql.selectList("store_return.store_list_return", vo);
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	

}
