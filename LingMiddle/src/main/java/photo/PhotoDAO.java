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
	
	public List<PhotoVO> getList(HashMap<String, Object> param){
		List<PhotoVO>  list = sql.selectList("photo.list" , param);
		return list;
	}
	
	public List<FolderVO> getFolder(HashMap<String, Object> param){
		List<FolderVO> list = sql.selectList("photo.folder",param);
		return list;
	}
	
	
	  public List<FolderVO> getFolder_LastImg(HashMap<String, Object> param){
	  List<FolderVO> list = sql.selectList("photo.folder_LastImg",param); return
	  list; }
	 
	
	
	public void folderInsert(FolderVO vo) {
		int result = sql.insert("photo.folder_insert", vo);
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
