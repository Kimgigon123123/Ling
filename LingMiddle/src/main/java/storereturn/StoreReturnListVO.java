package storereturn;

public class StoreReturnListVO {
	
	int purchase_cnt,  delivery_code,  order_num,return_code;
	String item_name,item_img,item_content;
	int item_price;
	public int getReturn_code() {
		return return_code;
	}

	public void setReturn_code(int return_code) {
		this.return_code = return_code;
	}
	String id, category_code,item_code;

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
	public int getDelivery_code() {
		return delivery_code;
	}
	public void setDelivery_code(int delivery_code) {
		this.delivery_code = delivery_code;
	}
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategory_code() {
		return category_code;
	}
	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	
	
	
}
