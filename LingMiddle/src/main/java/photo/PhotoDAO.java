package photo;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


@Repository
public class PhotoDAO {
	
	@Autowired @Qualifier ("test") SqlSession sql;
	
	public List<PhotoVO> getPhotoList(HashMap<String, Object> param){
		List<PhotoVO>  list = sql.selectList("photo.photo_list" , param);
		return list;
	}
	
	public List<FolderVO> getFolderList(String couple_num){
		List<FolderVO> list = sql.selectList("photo.folder_list",couple_num);
		return list;
	}
	
	 
	
	
	public void folderInsert(FolderVO vo) {
		int result = sql.insert("photo.folder_insert", vo);
		System.out.println("성공여부 : " + result);
	}
	
	
	public int photoInsert(PhotoVO vo) {
		int result = sql.insert("photo.photo_insert", vo);
		System.out.println("성공여부 : " + result);
		return result;
	}
	
	public void file(PhotoVO vo, String filePath) {
		int result = sql.insert("photo.file", vo);
		System.out.println("성공여부 : " + result);
	}
	
	public int folderDelete(FolderVO vo) {
		int result = sql.delete("photo.folder_delete", vo);
		return result;
	}
	
	public int fileDelete(PhotoVO vo) {
		int result = sql.delete("photo.file_delete", vo);
		return result;
	}
	
	
	public List<PhotoVO> getStorage(HashMap<String, Object> param){
		List<PhotoVO>  list = sql.selectList("photo.storage" , param);
		return list;
	}
}
