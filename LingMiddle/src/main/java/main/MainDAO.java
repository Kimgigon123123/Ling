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
	
	public MainVO getProfile_img(HashMap<String, Object> param){
		MainVO vo = sql.selectOne("main.select_couple_img",param);
		return vo;
	}
	
	public List<MainVO> select_couple_info(MainVO vo){
		List<MainVO> list = sql.selectList("main.select_couple_info",vo);
		return list;
	}
	
	public int update_couple_name(MainVO vo) {
		int result = sql.update("main.update_couple_name",vo);
		return result;
	}
	
	public List<MainVO> select_couplename(MainVO vo){
		List<MainVO> list = sql.selectList("main.select_couplename",vo);
		return list;
	}
}
