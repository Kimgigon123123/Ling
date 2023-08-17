package com.cteam.ling;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import storeco.StoreCOVO;
import storemyinfo.EdtMoneyVO;
import storemyinfo.StoreMyinfoVO;
import storemyinfo.StoreZzimListVO;

@RestController
public class StoreMyinfoController {

	@Autowired
	@Qualifier("test")
	SqlSession sql;

	@RequestMapping(value = "/store_myinfo", produces = "text/html;charset=utf-8")
	public String StoreMyinfo(StoreMyinfoVO vo) {
		List<StoreMyinfoVO> list = sql.selectList("store_myinfo.myinfo", vo);

		Gson gson = new Gson();

		return gson.toJson(list);
	}

	@RequestMapping(value = "/store_charge", produces = "text/html;charset=utf-8")
	public String charge(EdtMoneyVO vo) {

		int result = sql.update("store_myinfo.charge", vo);
		System.out.println("성공여부" + result);

		Gson gson = new Gson();
		return gson.toJson(result);

	}

	@RequestMapping(value = "/store_buy", produces = "text/html;charset=utf-8")
	public String buy(StoreMyinfoVO vo) {

		int result = sql.update("store_myinfo.buy", vo);
		System.out.println("성공여부" + result);

		Gson gson = new Gson();
		return gson.toJson(result);

	}

	// 찜목록 추가
	@RequestMapping(value = "/store_insert_zzim", produces = "text/html;charset=utf-8")
	public String insert_zzim(StoreZzimListVO vo) {

		int result = sql.insert("store_myinfo.insert_zzim", vo);

		Gson gson = new Gson();
		return gson.toJson(result);

	}

	// 찜목록 보기
	@RequestMapping(value = "/store_list_zzim", produces = "text/html;charset=utf-8")
	public String list_zzim(StoreZzimListVO vo) {

		List<StoreZzimListVO> list = sql.selectList("store_myinfo.zzimlist", vo);

		Gson gson = new Gson();
		return gson.toJson(list);
	}

	// 찜목록 삭제
	@RequestMapping(value = "/store_delete_zzim", produces = "text/html;charset=utf-8")
	public String delete_zzim(String item_code) {

		int result = sql.delete("store_myinfo.deletezzim", item_code);

		Gson gson = new Gson();
		return gson.toJson(result);
	}

	// 은행정보 등록
	@RequestMapping(value = "/store_update_bank_info", produces = "text/html;charset=utf-8")
	public String update_bank_info(StoreMyinfoVO vo) {

		int result = sql.update("store_myinfo.bank", vo);
//		
//		Gson gson = new Gson();
//		return	gson.toJson(result);
		return "sdfadsfasdasdf";
	}

	// 은행정보 등록하면 충전할때 자동으로 보여주기

	@RequestMapping(value = "/store_select_bank", produces = "text/html;charset=utf-8")
	public String select_bank(StoreMyinfoVO vo) {
		List<StoreMyinfoVO> list = sql.selectList("store_myinfo.select_bank", vo);

		Gson gson = new Gson();

		return gson.toJson(list);
	}

	// 주소 저장
	@RequestMapping(value = "/store_update_address", produces = "text/html;charset=utf-8")
	public String update_address(StoreMyinfoVO vo) {
		int result = sql.update("store_myinfo.update_address", vo);

		Gson gson = new Gson();

		return gson.toJson(result);

	}

	// 결제창에서 주소 보여주기
	@RequestMapping(value = "/store_select_address", produces = "text/html;charset=utf-8")
	public String select_address(StoreMyinfoVO vo) {
		List<StoreMyinfoVO> list = sql.selectList("store_myinfo.select_address", vo);
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	// 상세주소 저장
	@RequestMapping(value = "/store_update_detailadd", produces = "text/html;charset=utf-8")
	public String update_detailadd(StoreMyinfoVO vo) {
		int result = sql.update("store_myinfo.update_detailadd", vo);
		Gson gson = new Gson();
		return gson.toJson(result);

	}

	// 결제창에서 상세주소 보여주기
	@RequestMapping(value = "/store_select_detailadd", produces = "text/html;charset=utf-8")
	public String select_detailadd(StoreMyinfoVO vo) {
		List<StoreMyinfoVO> list = sql.selectList("store_myinfo.select_detailadd", vo);
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	// 구매목록 삭제
	@RequestMapping(value = "/store_delete_buylist", produces = "text/html;charset=utf-8")
	public String delete_buylist(String order_num) {

		int result = sql.delete("store_myinfo.deletebuylist", order_num);

		Gson gson = new Gson();
		return gson.toJson(result);
	}
	
	// 반품목록 삭제
		@RequestMapping(value = "/store_delete_return", produces = "text/html;charset=utf-8")
		public String delete_return(int return_code) {

			int result = sql.delete("store_myinfo.deletereturn", return_code);

			Gson gson = new Gson();
			return gson.toJson(result);
		}	
		
	//회원가입시 store_myinfo에 정보 추가
		@RequestMapping(value = "/store_insert_myinfo", produces = "text/html;charset=utf-8")
		public String insert_myinfo(String id) {

			int result = sql.insert("store_myinfo.insert_myinfo", id);

			Gson gson = new Gson();
			return gson.toJson(result);
		}	

}
