package com.example.ling.calendar;

import java.sql.Date;

public class ScheAddVO {
	private String id, sche_title, sche_typecode, sche_date, couple_num, create_date;
	private int d_day, sche_notice, sche_no;


	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public int getSche_no() {
		return sche_no;
	}
	public void setSche_no(int sche_no) {
		this.sche_no = sche_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSche_title() {
		return sche_title;
	}
	public void setSche_title(String sche_title) {
		this.sche_title = sche_title;
	}
	public String getSche_date() {
		return sche_date;
	}
	public void setSche_date(String sche_date) {
		this.sche_date = sche_date;
	}
	public int getSche_notice() {
		return sche_notice;
	}
	public void setSche_notice(int sche_notice) {
		this.sche_notice = sche_notice;
	}
	public String getSche_typecode() {
		return sche_typecode;
	}
	public void setSche_typecode(String sche_typecode) {
		this.sche_typecode = sche_typecode;
	}
	public int getD_day() {
		return d_day;
	}
	public void setD_day(int d_day) {
		this.d_day = d_day;
	}

	public String getCouple_num() {
		return couple_num;
	}

	public void setCouple_num(String couple_num) {
		this.couple_num = couple_num;
	}
}
