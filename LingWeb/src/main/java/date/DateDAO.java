package date;

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
}
