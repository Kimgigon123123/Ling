package com.example.ling.store.storeCO;

public class StoreCOVO {

 	private String item_code;
	private String category_code;
	private String item_name;

	public String getItem_img() {
		return item_img;
	}

	private String item_img;
	private String item_content ;
	private int item_price,popular;

	
public int getPopular() {
		return popular;
	}

	public void setPopular(int popular) {
		this.popular = popular;
	}

public String getCategory_code() {
	return category_code;
}

public void setCategory_code(String category_code) {
	this.category_code = category_code;
}

public String getItem_name() {
	return item_name;
}

public void setItem_name(String item_name) {
	this.item_name = item_name;
}


public void setItem_img(String item_img) {
	this.item_img = item_img;
}

public String getItem_content() {
	return item_content;
}

public void setItem_content(String item_content) {
	this.item_content = item_content;
}

public String getItem_code() {
	return item_code;
}

public void setItem_code(String item_code) {
	this.item_code = item_code;
}

public int getItem_price() {
	return item_price;
}

public void setItem_price(int item_price) {
	this.item_price = item_price;
}
}