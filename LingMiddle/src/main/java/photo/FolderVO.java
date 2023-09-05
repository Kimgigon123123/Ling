package photo;

public class FolderVO {
	private String id,  folder_name, couple_num, last_photo;
	private int folder_num;
	
	
	
	public int getFolder_num() {
		return folder_num;
	}
	public void setFolder_num(int folder_num) {
		this.folder_num = folder_num;
	}
	
	public String getLast_photo() {
		return last_photo;
	}
	public void setLast_photo(String last_photo) {
		this.last_photo = last_photo;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFolder_name() {
		return folder_name;
	}
	public void setFolder_name(String folder_name) {
		this.folder_name = folder_name;
	}
	public String getCouple_num() {
		return couple_num;
	}
	public void setCouple_num(String couple_num) {
		this.couple_num = couple_num;
	}


	
}
