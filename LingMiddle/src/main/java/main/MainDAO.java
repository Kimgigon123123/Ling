package main;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import photo.FolderVO;


@Repository
public class MainDAO {
	@Autowired
	@Qualifier("test")
	SqlSession sql;
	
	public List<MainVO> getProfile_img(HashMap<String, Object> param){
		List<MainVO> list = sql.selectList("main.select_couple_img",param);
		return list;
	}
	
	public List<MainVO> select_couple_info(MainVO vo){
		List<MainVO> list = sql.selectList("main.select_couple_info",vo);
		return list;
	}
}
