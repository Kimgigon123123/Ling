package photo;

public class PhotoVO {
	private String folder_name, pho_img, pho_date;
	private int folder_num;
	
	

	public int getFolder_num() {
		return folder_num;
	}

	public void setFolder_num(int folder_num) {
		this.folder_num = folder_num;
	}



	public String getFolder_name() {
		return folder_name;
	}

	public void setFolder_name(String folder_name) {
		this.folder_name = folder_name;
	}

	public String getPho_img() {
		return pho_img;
	}

	public void setPho_img(String pho_img) {
		this.pho_img = pho_img;
	}

	public String getPho_date() {
		return pho_date;
	}

	public void setPho_date(String pho_date) {
		this.pho_date = pho_date;
	}
	

	
}
