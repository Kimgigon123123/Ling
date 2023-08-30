package store;

public class StoreVO {

 	private String item_code,category_code, item_name,  item_img, 
 	item_content,zzim_product,st_sales_amount,st_item_price ;
	private int item_price,popular,sales,total_sales,sales_amount;

	
	
public String getSt_item_price() {
		return st_item_price;
	}

	public void setSt_item_price(String st_item_price) {
		this.st_item_price = st_item_price;
	}

public String getSt_sales_amount() {
		return st_sales_amount;
	}

	public void setSt_sales_amount(String st_sales_amount) {
		this.st_sales_amount = st_sales_amount;
	}

public int getSales_amount() {
		return sales_amount;
	}

	public void setSales_amount(int sales_amount) {
		this.sales_amount = sales_amount;
	}

public int getTotal_sales() {
		return total_sales;
	}

	public void setTotal_sales(int total_sales) {
		this.total_sales = total_sales;
	}

public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

public String getZzim_product() {
		return zzim_product;
	}

	public void setZzim_product(String zzim_product) {
		this.zzim_product = zzim_product;
	}

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

public String getItem_img() {
	return item_img;
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