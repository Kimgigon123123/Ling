package board;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import member.MemberVO;








@Repository
public class BoardDAO {
	@Autowired @Qualifier("test") SqlSession sql;
	
	public List<BoardVO> selectBoard(String board_cd){
		List<BoardVO> vo = sql.selectList("board.select_board", board_cd);
		return vo;
	}
	
	/*
	 * public List<BoardVO> freeselect(HashMap<String, String> params){
	 * List<BoardVO> vo = sql.selectList("board.freeselect", params); return vo; }
	 */
	
//	public List<BoardVO> worryselect(){
//		return sql.selectList("board.worryselect");
//	}
	
//	public List<BoardVO> playselect(){
//		return sql.selectList("board.playselect");
//	}	

	public BoardVO notice_content(String id){
		return sql.selectOne("board.content", id);
	}
	
	public BoardVO user_content(HashMap<String, String> param){
		return sql.selectOne("board.usercontent", param);
	}
	
	public BoardVO info(String id) {
		
		return sql.selectOne("board.info", id);
	}
	
	public int board_read(String id) {
		return sql.update("board.read", id);
	}
	
	public int board_regist(BoardVO vo) {
		return sql.insert("board.insert", vo);
	}
	
	public int board_update(BoardVO vo) {
		
		return sql.update("board.update", vo);
	}
	
	public int board_delete(String id) {
	
		return sql.delete("board.delete", id);
	}
	
	public List<BoardCommentVO> board_comment_list(String board_id) {
		return sql.selectList("board.commentList", board_id);
	}
	
	public int board_comment_register(BoardCommentVO vo) {
		return sql.insert("board.commentRegister", vo);
	}
	
	public List<BoardReCommentVO> board_recomment_list(String comment_id) {
		return sql.selectList("board.RecommentList", comment_id);
	}
	
	public int board_recomment_register(BoardReCommentVO vo) {
		return sql.insert("board.RecommentRegister", vo);
	}
}
