package com.cteam.ling;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import capsule.CapsuleVO;
import note.NoteVO;

@RestController
public class NoteController {

	@Autowired
	@Qualifier("test")
	SqlSession sql;
	
	
	// 노트추가
		@RequestMapping(value = "/note_insert", produces = "text/html;charset=utf-8")
		public String note_insert(NoteVO vo) {
			int result = sql.insert("note.note_insert", vo);
			Gson gson = new Gson();
			return gson.toJson(result);
		}
		
		// 노트 전체
		@RequestMapping(value="/select_note_all",produces = "text/html;charset=utf-8")
		public String select_note_all(NoteVO vo) {
			List<NoteVO> list = sql.selectList("note.select_note_all",vo);
			Gson gson = new Gson();
			return gson.toJson(list);
		}
		
		// 노트 커플꺼만
				@RequestMapping(value="/select_note_couple",produces = "text/html;charset=utf-8")
				public String select_note_couple(NoteVO vo) {
					List<NoteVO> list = sql.selectList("note.select_note_couple",vo);
					Gson gson = new Gson();
					return gson.toJson(list);
				}

				// 노트 개인꺼만
				@RequestMapping(value="/select_note_private",produces = "text/html;charset=utf-8")
				public String select_note_private(NoteVO vo) {
					List<NoteVO> list = sql.selectList("note.select_note_private",vo);
					Gson gson = new Gson();
					return gson.toJson(list);
				}
				
				// 노트 수정
				@RequestMapping(value="/update_note",produces = "text/html;charset=utf-8")
				public String update_note(NoteVO vo) {
					int result = sql.update("note.update_note",vo);
					Gson gson = new Gson();
					return gson.toJson(result);
				}
				// 노트 삭제
				@RequestMapping(value="/delete_note",produces = "text/html;charset=utf-8")
				public String delete_note(NoteVO vo) {
					int result = sql.delete("note.delete_note",vo);
					Gson gson = new Gson();
					return gson.toJson(result);
				}
}
