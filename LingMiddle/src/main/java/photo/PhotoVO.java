package photo;

public class PhotoVO {
	private int couple_num;
	private String id, pho_img, pho_date;
	
	public int getCouple_num() {
		return couple_num;
	}
	public void setCouple_num(int couple_num) {
		this.couple_num = couple_num;
	}
	public String getPho_img() {
		return pho_img;
	}
	public void setPho_img(String pho_img) {
		this.pho_img = pho_img;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPho_date() {
		return pho_date;
	}
	public void setPho_date(String pho_date) {
		this.pho_date = pho_date;
	}
	
}
