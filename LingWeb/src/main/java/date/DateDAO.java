package date;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DateDAO {
	@Autowired @Qualifier("test")
	SqlSession sql;
	
	public List<DateVO> travel_list() {
		return sql.selectList("date.travel");
	}

	public List<DateVO> restaurant_list() {
		return sql.selectList("date.restaurant");
	}

	public List<DateVO> festival_list() {
		return sql.selectList("date.festival");
	}
	
	public DateVO date_info(int date_id) {
		return sql.selectOne("date.info", date_id);
	}
	
	public void date_insert(DateVO vo) {
		sql.insert("date.insert", vo);
	}
	
	public void date_delete(DateVO vo) {
		sql.delete("date.delete", vo);
	}
	public void date_delete(HashMap<String, String> map) {
		sql.delete("date.multipledelete", map);
	}
	
	public void date_update(DateVO vo) {
		sql.update("date.update", vo);
	}
}
