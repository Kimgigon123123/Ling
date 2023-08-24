package faq;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


@Repository
public class FaqDAO {
	@Autowired @Qualifier ("test") SqlSession sql;
	
	public List<FaqVO> list(){
		List<FaqVO> list = sql.selectList("faq.list");
		return list;
	}
	
	public int faq_insert(FaqVO vo) {
		int result = sql.insert("faq.faq_insert");
		return result;
	}
	
	public int faq_info(int id) {
		int result = sql.selectOne("faq.faq_info");
		return result;
	}
}
