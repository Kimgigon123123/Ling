package member;

import java.util.HashMap;
import java.util.List;

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
	
	
	public String useridEmail(MemberVO vo) {
		
		return sql.selectOne("member.useridEmail", vo);
	}

	
	public int resetPassword(MemberVO vo) {
		
		return sql.update("member.resetPassword", vo);
	}
	
	public MemberVO info(String id) {
		
		return sql.selectOne("member.info", id);
	}
	// sql.insert , update ,delete => 작업 성공 여부를 int로 받는다. "1"=> Ling_MemberVO
	// sql.selectOne , List  => resultTpye 해당하는타입으로 결과를 받는다. 
	public int join(MemberVO vo) {
		return sql.insert("member.join", vo);
	}
	
	public int matching(HashMap<String, Object> map) {
		return sql.insert("member.matching", map);
	}
	
	public List<MemberVO> member_list() {
		
		return sql.selectList("member.list");
	}
	
	public List<MemberVO> member_list_id_by(HashMap<String, Object> map) {
		
		return sql.selectList("member.list", map);
	}
	
	public List<MemberVO> adminlist() {
		
		return sql.selectList("member.adminlist");
	}
	
	public List<MemberVO> couplelist() {
		
		return sql.selectList("member.couplelist");
	}
	
	public List<MemberVO> couplelist_order(HashMap<String, Object> map) {
		
		return sql.selectList("member.couplelist", map);
	}
	
	public int updatemember(MemberVO vo) {
		
		return sql.update("member.update", vo);
	}
	
	
	public int delete(String id) {
		return sql.delete("member.delete", id);
	}
	
}
