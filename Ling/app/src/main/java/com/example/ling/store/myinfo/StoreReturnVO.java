package com.example.ling.store.myinfo;

public class StoreReturnVO {
 int order_num,item_price,purchase_cnt,total_price;
 
 
 public int getTotal_price() {
	return total_price;
}
public void setTotal_price(int total_price) {
	this.total_price = total_price;
}
String item_code,item_name,delivery_state,item_img,id;
public String getItem_img() {
	return item_img;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public void setItem_img(String item_img) {
	this.item_img = item_img;
}
public int getOrder_num() {
	return order_num;
}
public void setOrder_num(int order_num) {
	this.order_num = order_num;
}
public int getItem_price() {
	return item_price;
}
public void setItem_price(int item_price) {
	this.item_price = item_price;
}
public int getPurchase_cnt() {
	return purchase_cnt;
}
public void setPurchase_cnt(int purchase_cnt) {
	this.purchase_cnt = purchase_cnt;
}
public String getItem_code() {
	return item_code;
}
public void setItem_code(String item_code) {
	this.item_code = item_code;
}
public String getItem_name() {
	return item_name;
}
public void setItem_name(String item_name) {
	this.item_name = item_name;
}
public String getDelivery_state() {
	return delivery_state;
}
public void setDelivery_state(String delivery_state) {
	this.delivery_state = delivery_state;
}

 
 
 
 
 
}
