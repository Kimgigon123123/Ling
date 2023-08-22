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
	
	public List<ScheAddVO> getList(HashMap<String, Object> param){
		List<ScheAddVO>  list = sql.selectList("sche.list", param);
		return list;
	}
	
	public int insert(ScheAddVO vo){
		int result = sql.insert("sche.insert", vo);
		return result;
		
	}
	
	public int delete(ScheAddVO vo) {
		int result = sql.delete("sche.delete", vo);
		return result;
	}
	
	public int update(ScheAddVO vo) {
		int result = sql.update("sche.update", vo);
		return result;
	}
	
	public List<ScheAddVO> getDday(ScheAddVO vo){
		List<ScheAddVO>  list = sql.selectList("sche.dday", vo);
		return list;
	}
}
