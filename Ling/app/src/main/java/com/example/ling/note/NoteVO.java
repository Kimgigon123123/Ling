package com.example.ling.note;

public class NoteVO {
	String id,content,write_date,view_range,name;
	int note_id,couple_num;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWrite_date() {
		return write_date;
	}
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}
	public String getView_range() {
		return view_range;
	}
	public void setView_range(String view_range) {
		this.view_range = view_range;
	}
	public int getNote_id() {
		return note_id;
	}
	public void setNote_id(int note_id) {
		this.note_id = note_id;
	}
	public int getCouple_num() {
		return couple_num;
	}
	public void setCouple_num(int couple_num) {
		this.couple_num = couple_num;
	}
	
	
	
}
