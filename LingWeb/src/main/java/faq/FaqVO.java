package faq;

import java.sql.Date;

public class FaqVO {
	private int faq_no;
	private String faq_category, faq_content,  faq_id, faq_title, search;
	private Date faq_date;
	
	
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getFaq_title() {
		return faq_title;
	}
	public void setFaq_title(String faq_title) {
		this.faq_title = faq_title;
	}
	public int getFaq_no() {
		return faq_no;
	}
	public void setFaq_no(int faq_no) {
		this.faq_no = faq_no;
	}
	public String getFaq_category() {
		return faq_category;
	}
	public void setFaq_category(String faq_category) {
		this.faq_category = faq_category;
	}
	public String getFaq_content() {
		return faq_content;
	}
	public void setFaq_content(String faq_content) {
		this.faq_content = faq_content;
	}

	
	public Date getFaq_date() {
		return faq_date;
	}
	public void setFaq_date(Date faq_date) {
		this.faq_date = faq_date;
	}
	public String getFaq_id() {
		return faq_id;
	}
	public void setFaq_id(String faq_id) {
		this.faq_id = faq_id;
	}
	
	
}
