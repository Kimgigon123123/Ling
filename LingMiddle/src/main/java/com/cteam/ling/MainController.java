package com.cteam.ling;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import chat.ChatVO;
import main.MainDAO;
import main.MainVO;
import photo.FolderVO;
import photo.PhotoDAO;
import storeco.StoreCOVO;

@RestController
public class MainController {

	
	@Autowired MainDAO dao;
	
	// 커플 로그인 했을때 커플 정보 보여주기 ( 가장 첫번째 화면 )
		@RequestMapping(value = "/select_couple_info", produces = "text/html;charset=utf-8")
		public String select_couple_info(MainVO vo) {
			List<MainVO> list = dao.select_couple_info(vo);
			Gson gson = new Gson();
			return gson.toJson(list);
		}
		
		
		
	// 프로필 이미지 조회
		@RequestMapping(value = "/select_couple_img", produces = "text/html;charset=utf-8")
		public String select_couple_img(String id, String couple_num, String profile) {
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("id", id);
			param.put("couple_num", couple_num);
			param.put("profile", profile);
					 //couple_num
			MainVO vo = dao.getProfile_img(param) ;

			Gson gson = new Gson();	
			
			return gson.toJson(vo);
		}
		
	// 프로필 이미지 변경
		
		
		// 애칭 변경 
		
		@RequestMapping(value = "/update_couple_name", produces = "text/html;charset=utf-8")
		public String update_couple_name(MainVO vo) {
			int result = dao.update_couple_name(vo);
			Gson gson = new Gson();
			return gson.toJson(result);
		}
		
		
		//애칭 select
		@RequestMapping(value = "/select_couplename", produces = "text/html;charset=utf-8")
		public String select_couple_name(MainVO vo) {
			List<MainVO> list = dao.select_couplename(vo);
			Gson gson = new Gson();
			return gson.toJson(list);
		}
		
	
}
