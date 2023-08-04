package login;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

	@Autowired
	@Qualifier("test")
	SqlSession sql;

	public MemberVO login(HashMap<String, String> params) {

		MemberVO vo = sql.selectOne("member.login", params);
		return vo;
	}
	
	public MemberVO findid(HashMap<String, String> params) {

		MemberVO vo = sql.selectOne("member.findid", params);
		return vo;
	}

}
