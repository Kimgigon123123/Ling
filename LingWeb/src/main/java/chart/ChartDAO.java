package chart;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ChartDAO implements ChartService {
	@Autowired @Qualifier("test") private SqlSession sql;
	
	@Override
	public List<HashMap<String, Object>> age() {
		return sql.selectList("chart.age");
	}

	@Override
	public List<HashMap<String, Object>> period() {
		return sql.selectList("chart.period");
	}

}
