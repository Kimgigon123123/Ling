package com.cteam.ling;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import storebasket.StoreBasketVO;
import storeco.StoreCOVO;

@RestController
public class StoreCategoryContoller {

	@Autowired
	@Qualifier("test")
	SqlSession sql;
	
	
	//커플옷 보여주기 최신순22
		@RequestMapping(value="/storelist_dr",produces="text/html;charset=utf-8")
		public String by_recent(String orderby, String id) {
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("orderby", orderby);
			params.put("id", id);
			List<StoreCOVO> list = sql.selectList("store_category.store_list_dr" , params);
			
			Gson gson = new Gson();
			
			return gson.toJson(list);
		}
		
		
		// 커플반지 리스트 보여주기 최신순22
		@RequestMapping(value = "/storelist_ri", produces = "text/html;charset=utf-8")
		public String ri_byrecent(String orderby,String id) {
			HashMap<String,String> params = new HashMap<String,String>();
			params.put("orderby",orderby);
			params.put("id",id);
			List<StoreCOVO> list = sql.selectList("store_category.store_list_ri",params);

			Gson gson = new Gson();

			return gson.toJson(list);
		}
		
		
		
		// 상품권 리스트 보여주기 최신순22
		@RequestMapping(value = "/storelist_gi", produces = "text/html;charset=utf-8")
		public String gi_byrecent(String orderby,String id) {
			HashMap<String,String> params = new HashMap<String,String>();
			params.put("orderby", orderby);
			params.put("id", id);
			List<StoreCOVO> list = sql.selectList("store_category.store_list_gi",params);

			Gson gson = new Gson();

			return gson.toJson(list);
		}
		
		
		// 기타 리스트 보여주기 최신순222
		@RequestMapping(value = "/storelist_etc", produces = "text/html;charset=utf-8")
		public String etc_byrecent(String orderby,String id) {
			HashMap<String,String> params = new HashMap<String,String>();
			params.put("orderby", orderby);
			params.put("id",id);
			List<StoreCOVO> list = sql.selectList("store_category.store_list_etc",params);
			
			Gson gson = new Gson();
			
			return gson.toJson(list);
			
		}
		

	// 커플옷 리스트 보여주기 최신순
	@RequestMapping(value = "/store_dr_byrecent", produces = "text/html;charset=utf-8")
	public String dr_byrecent() {
		List<StoreCOVO> list = sql.selectList("store_category.dr_byrecent");

		Gson gson = new Gson();

		return gson.toJson(list);
	}

	// 커플옷 이름순
	@RequestMapping(value = "/store_dr_byname", produces = "text/html;charset=utf-8")
	public String dr_byname() {
		List<StoreCOVO> list = sql.selectList("store_category.dr_byname");

		Gson gson = new Gson();

		return gson.toJson(list);
	}

	// 커플옷 인기순
	@RequestMapping(value = "/store_dr_bypopular", produces = "text/html;charset=utf-8")
	public String dr_bypopular() {
		List<StoreCOVO> list = sql.selectList("store_category.dr_bypopular");

		Gson gson = new Gson();

		return gson.toJson(list);
	}

	// 커플옷 가격순
	@RequestMapping(value = "/store_dr_byprice", produces = "text/html;charset=utf-8")
	public String dr_byprice() {
		List<StoreCOVO> list = sql.selectList("store_category.dr_byprice");

		Gson gson = new Gson();

		return gson.toJson(list);
	}

	// 커플반지 리스트 보여주기 최신순
	@RequestMapping(value = "/store_ri_byrecent", produces = "text/html;charset=utf-8")
	public String ri_byrecent() {
		List<StoreCOVO> list = sql.selectList("store_category.ri_byrecent");

		Gson gson = new Gson();

		return gson.toJson(list);
	}

	// 커플반지 이름순
	@RequestMapping(value = "/store_ri_byname", produces = "text/html;charset=utf-8")
	public String ri_byname() {
		List<StoreCOVO> list = sql.selectList("store_category.ri_byname");

		Gson gson = new Gson();

		return gson.toJson(list);
	}

	// 커플반지 인기순
	@RequestMapping(value = "/store_ri_bypopular", produces = "text/html;charset=utf-8")
	public String ri_bypopular() {
		List<StoreCOVO> list = sql.selectList("store_category.ri_bypopular");

		Gson gson = new Gson();

		return gson.toJson(list);
	}

	// 커플반지 가격순
	@RequestMapping(value = "/store_ri_byprice", produces = "text/html;charset=utf-8")
	public String ri_byprice() {
		List<StoreCOVO> list = sql.selectList("store_category.ri_byprice");

		Gson gson = new Gson();

		return gson.toJson(list);
	}

	// 상품권 리스트 보여주기 최신순
	@RequestMapping(value = "/store_gi_byrecent", produces = "text/html;charset=utf-8")
	public String gi_byrecent() {
		List<StoreCOVO> list = sql.selectList("store_category.gi_byrecent");

		Gson gson = new Gson();

		return gson.toJson(list);
	}

	// 상품권 이름순
	@RequestMapping(value = "/store_gi_byname", produces = "text/html;charset=utf-8")
	public String gi_byname() {
		List<StoreCOVO> list = sql.selectList("store_category.gi_byname");

		Gson gson = new Gson();

		return gson.toJson(list);
	}

	// 상품권 인기순
	@RequestMapping(value = "/store_gi_bypopular", produces = "text/html;charset=utf-8")
	public String gi_bypopular() {
		List<StoreCOVO> list = sql.selectList("store_category.gi_bypopular");

		Gson gson = new Gson();

		return gson.toJson(list);
	}

	// 상품권 가격순
	@RequestMapping(value = "/store_gi_byprice", produces = "text/html;charset=utf-8")
	public String gi_byprice() {
		List<StoreCOVO> list = sql.selectList("store_category.gi_byprice");

		Gson gson = new Gson();

		return gson.toJson(list);
	}

	// 기타 리스트 보여주기 최신순
	@RequestMapping(value = "/store_etc_byrecent", produces = "text/html;charset=utf-8")
	public String etc_byrecent() {
		List<StoreCOVO> list = sql.selectList("store_category.etc_byrecent");

		Gson gson = new Gson();

		return gson.toJson(list);
	}

	// 기타 이름순
	@RequestMapping(value = "/store_etc_byname", produces = "text/html;charset=utf-8")
	public String etc_byname() {
		List<StoreCOVO> list = sql.selectList("store_category.etc_byname");

		Gson gson = new Gson();

		return gson.toJson(list);
	}

	// 기타 인기순
	@RequestMapping(value = "/store_etc_bypopular", produces = "text/html;charset=utf-8")
	public String etc_bypopular() {
		List<StoreCOVO> list = sql.selectList("store_category.etc_bypopular");

		Gson gson = new Gson();

		return gson.toJson(list);
	}

	// 기타 가격순
	@RequestMapping(value = "/store_etc_byprice", produces = "text/html;charset=utf-8")
	public String etc_byprice() {
		List<StoreCOVO> list = sql.selectList("store_category.etc_byprice");

		Gson gson = new Gson();

		return gson.toJson(list);
	}
}
