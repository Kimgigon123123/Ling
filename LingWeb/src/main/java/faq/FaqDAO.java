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
}
