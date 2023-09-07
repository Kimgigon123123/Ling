package faq;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


@Repository
public class FaqDAO {
	@Autowired @Qualifier ("test") SqlSession sql;
	
	
	//FAQ 글 목록
	public List<FaqVO> list(){
		List<FaqVO> list = sql.selectList("faq.list");
		return list;
	}
	
	//FAQ 글 추가
	public int faq_insert(FaqVO vo) {
		int result = sql.insert("faq.faq_insert", vo);
		return result;
	}
	
	
	//FAQ 글 정보
	public FaqVO faq_info(Integer faq_no) {
		return sql.selectOne("faq.faq_info", faq_no);
	}
	
	//FAQ 페이징
	public PageVO faq_list(PageVO page) {
		page.setTotalList((Integer) sql.selectOne("faq.totalList", page));
		page.setList(sql.selectList("faq.list",page));
		return page;
	}
	
	//FAQ 글 수정
	public int faq_update(FaqVO vo) {
		return sql.update("faq.faq_update", vo);
	}
	
	//FAQ 글 삭제
	public int faq_delete(Integer faq_no) {
		return sql.delete("faq.faq_delete", faq_no);
	}
}
