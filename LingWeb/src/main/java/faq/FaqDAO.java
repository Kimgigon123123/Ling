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
		int result = sql.insert("faq.faq_insert", vo);
		return result;
	}
	
	public FaqVO faq_info(int faq_no) {
		return sql.selectOne("faq.faq_info", faq_no);
	}
	
	public PageVO faq_list(PageVO page) {
		page.setTotalList((Integer) sql.selectOne("faq.totalList", page));
		page.setList(sql.selectList("faq.list",page));
		return page;
	}
}
