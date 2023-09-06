package schedule;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;



@Repository
public class ScheDAO {
	
	@Autowired @Qualifier ("test") SqlSession sql;
	
	
	//메인화면 일정 목록
	public List<ScheAddVO> getList(HashMap<String, Object> param){
		List<ScheAddVO>  list = sql.selectList("sche.list", param);
		return list;
	}
	
	//메인화면 음수 d-day 목록 처리  
	public List<ScheAddVO> plus_List(HashMap<String, Object> param){
		List<ScheAddVO>  list = sql.selectList("sche.plus_List", param);
		return list;
	}
	
	//일정 추가
	public int insert(ScheAddVO vo){
		int result = sql.insert("sche.insert", vo);
		return result;
		
	}
	
	//일정 삭제
	public int delete(ScheAddVO vo) {
		int result = sql.delete("sche.delete", vo);
		return result;
	}
	
	//일정 수정
	public int update(ScheAddVO vo) {
		int result = sql.update("sche.update", vo);
		return result;
	}
	
	//일정 d-day
	public List<ScheAddVO> getDday(ScheAddVO vo){
		List<ScheAddVO> list = sql.selectList("sche.dday", vo);
		return list;
	}
	

}
