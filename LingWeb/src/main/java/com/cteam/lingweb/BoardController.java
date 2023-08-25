package com.cteam.lingweb;



import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import board.BoardDAO;
import board.BoardVO;
import member.MemberVO;






@Controller
public class BoardController {
	
	@Autowired
	@Qualifier("test")
	SqlSession sql;
	@Autowired
	BoardDAO dao;
	
	
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String board(HttpSession session, Model model,@RequestParam(defaultValue = "NOTICE") String board_cd) {
//		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("board_cd", board_cd);
//		params.put("keyword", keyword);
		
		session.setAttribute("active_category", "board");
		model.addAttribute("board_cd", board_cd);
		model.addAttribute("list", dao.selectBoard(board_cd));
		return "board";
	}
	
	@RequestMapping("/changeboardlist")
	public String changelist(String tablename, Model model, String board_cd ) {

		model.addAttribute("list", dao.selectBoard(board_cd));
		return "boardlist/folder/notice";
//		if(tablename.equals("NOTICE")) {
//			return "boardlist/folder/notice";			
//		}else if(tablename.equals("FREE")) {
//			return "boardlist/folder/notice";
//		}else if(tablename.equals("WORRY")) {
//			return "boardlist/folder/notice";
//		}else {
//			return "boardlist/folder/notice";
//		}
	}
	
//	//공지사항 목록 조회
//		@RequestMapping(value="/board.select", produces = "text/html;charset=utf-8")
//		public String noticelist(String board_cd, String keyword) {
//			HashMap<String, String> params = new HashMap<String, String>();
//			params.put("board_cd", board_cd);
//			params.put("keyword", keyword);
//			List<Object> vo = dao.selectBoard(params);
//			return new Gson().toJson(vo);		
//		}
//		
//		/*
//		 * //자유게시판 목록 조회
//		 * 
//		 * @RequestMapping(value="/board.freeselect", produces =
//		 * "text/html;charset=utf-8") public String freelist(String board_cd, String
//		 * keyword) { HashMap<String, String> params = new HashMap<String, String>();
//		 * params.put("board_cd", board_cd); params.put("keyword", keyword);
//		 * List<BoardVO> vo = dao.freeselect(params); return new Gson().toJson(vo); }
//		 */
//		
////		//고민상담소 목록 조회
////		@RequestMapping(value="/board.worryselect", produces = "text/html;charset=utf-8")
////		public String worrylist() {
////				
////			return new Gson().toJson(dao.worryselect());		
////		}	
//
////		//고민상담소 목록 조회
////		@RequestMapping(value="/board.playselect", produces = "text/html;charset=utf-8")
////		public String playlist() {
////					
////			return new Gson().toJson(dao.playselect());		
////		}		
//		
//		// 공지사항상세정보화면 요청
//		@RequestMapping(value="/board.content", produces = "text/html;charset=utf-8")
//		public String content(String id) {
//			//dao.board_read(id);
//			return new Gson().toJson(dao.notice_content(id));
//		}
//		
//		// 자유, 고민 게시판 상세정보화면 요청
//		@RequestMapping(value="/board.usercontent", produces = "text/html;charset=utf-8")
//		public String usercontent(String id , String board_cd) {
//			HashMap<String, String> param = new HashMap<String, String>();
//			param.put("id", id);
//			param.put("board_cd", board_cd);
//			dao.board_read(id);
//			return new Gson().toJson(dao.user_content(param));
//		}
//		
////		//조회수 증가처리
////		@RequestMapping(value="/board.read", produces = "text/html;charset=utf-8")
////		public String notice_read(String id) {
////			
////			return new Gson().toJson(dao.notice_read(id));
////		}
//		
	
		@RequestMapping(value = "/insertboard", method = RequestMethod.GET)
		public String insertboard(@RequestParam(name = "board_cd") String board_cd, Model model) {
			
			model.addAttribute("board_cd", board_cd);
			return "insertboard";
		}
		
		//신규 공지글 등록 처리 요청
		@RequestMapping("/insertboard")
		public String register(BoardVO vo) {
			
			
			dao.board_regist(vo);
			
			return "redirect:board";
		}
//		
//		@RequestMapping("/updateboard")
//		public String update(BoardVO vo, Model model) {
//
//			model.addAttribute("vo", dao.board_update(vo));
//			return "redirect:board";
//		}
		@RequestMapping("/updateboard")
		public String updateboard(BoardVO vo, Model model) {

			model.addAttribute("vo", dao.board_update(vo));
			return "redirect:board";
		}
		@RequestMapping(value = "/detailboard", method = RequestMethod.GET)
		public String detailboard(String id, Model model) {

			model.addAttribute("vo", dao.info(id));
			return "detailboard";
		}	
		
		//선택한 글 정보 수정처리 요청
		@RequestMapping("/deleteboard")
		public String delete(String ids, String board_cd, Model model) {
			
			 dao.board_delete(ids);
			 //return "redirect:board";
			 model.addAttribute("list", dao.selectBoard(board_cd));
			return "boardlist/folder/notice";
		}	
//
//		
//		//댓글 목록 조회
//		@RequestMapping(value="/board.commentList", produces = "text/html;charset=utf-8")
//		public String comment_list(String board_id) {
//			List<BoardCommentVO> vo = dao.board_comment_list(board_id);
//			
//			return new Gson().toJson(vo);
//		}
//		
//		//댓글 등록처리
//		@RequestMapping(value="/board.commentRegister", produces = "text/html;charset=utf-8")
//		public String comment_register(BoardCommentVO vo) {
//			//화면에서 입력한 댓글 정보를 DB에 신규저장
//			dao.board_comment_register(vo);
//			return new Gson().toJson(vo);
//		}
//		
//		//댓글 목록 조회
//		@RequestMapping(value="/board.RecommentList", produces = "text/html;charset=utf-8")
//		public String recomment_list(String comment_id) {
//			List<BoardReCommentVO> vo = dao.board_recomment_list(comment_id);
//			
//			return new Gson().toJson(vo);
//		}
//			
//		//댓글 등록처리
//		@RequestMapping(value="/board.RecommentRegister", produces = "text/html;charset=utf-8")
//		public String recomment_register(BoardReCommentVO vo) {
//			//화면에서 입력한 댓글 정보를 DB에 신규저장
//			dao.board_recomment_register(vo);
//			return new Gson().toJson(vo);
//		}
	
}
