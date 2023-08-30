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
	
//	public List<DateVO> travel_list(String search) {
//		return sql.selectList("date.travel", search);
//	}
	
	public PageVO travel_list(PageVO page) {
		// 건수 조회
		page.setTotalList(sql.selectOne("date.totalTravel", page));
		// 해당 페이지의 목록( 기본 10건 )
		page.setList(sql.selectList("date.travel", page));
		return page;
	}

	public PageVO restaurant_list(PageVO page) {
		// 건수 조회
		page.setTotalList(sql.selectOne("date.totalRestaurant", page));
		// 해당 페이지의 목록( 기본 10건 )
		page.setList(sql.selectList("date.restaurant", page));
		return page;
	}

	public PageVO festival_list(PageVO page) {
		// 건수 조회
		page.setTotalList(sql.selectOne("date.totalFestival", page));
		// 해당 페이지의 목록( 기본 10건 )
		page.setList(sql.selectList("date.festival", page));
		return page;
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
