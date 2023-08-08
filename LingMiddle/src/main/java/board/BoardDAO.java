package board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


@Repository
public class BoardDAO {
	@Autowired @Qualifier("test") SqlSession sql;
	
	public List<BoardVO> noticelist(){
		return sql.selectList("board.noticeselect");
	}
	
	public List<BoardVO> freeselect(){
		return sql.selectList("board.freeselect");
	}
	
	public List<BoardVO> worryselect(){
		return sql.selectList("board.worryselect");
	}
	
	public List<BoardVO> playselect(){
		return sql.selectList("board.playselect");
	}	

}
