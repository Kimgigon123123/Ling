package board;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;





@Repository
public class BoardDAO {
	@Autowired @Qualifier("test") SqlSession sql;
	
	public List<Object> noticelist(HashMap<String, String> params){
		List<Object> vo = sql.selectList("board.noticeselect", params);
		return vo;
	}
	
	public List<BoardVO> freeselect(HashMap<String, String> params){
		List<BoardVO> vo = sql.selectList("board.freeselect", params);
		return vo;
	}
	
//	public List<BoardVO> worryselect(){
//		return sql.selectList("board.worryselect");
//	}
	
//	public List<BoardVO> playselect(){
//		return sql.selectList("board.playselect");
//	}	

	public BoardVO notice_content(String id){
		return sql.selectOne("board.content", id);
	}
	
	public BoardVO user_content(String id){
		return sql.selectOne("board.content", id);
	}
	
	public int board_read(String id) {
		return sql.update("board.read", id);
	}
	
	
}
