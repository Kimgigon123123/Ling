package schedule;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


@Repository
public class ScheDAO {
	
	@Autowired @Qualifier ("test") SqlSession sql;
	
	public List<ScheAddVO> getList(){
		List<ScheAddVO>  list = sql.selectList("sche.list");
		return list;
	}
	
	public int insert(ScheAddVO vo){
		int result = sql.insert("sche.insert", vo);
		return result;
		
	}
}
