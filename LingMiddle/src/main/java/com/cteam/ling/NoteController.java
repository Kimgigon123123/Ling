package com.cteam.ling;

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
}
