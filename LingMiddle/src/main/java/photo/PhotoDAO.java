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
	
	
	//사진 목록
	public List<PhotoVO> getPhotoList(HashMap<String, Object> param){
		List<PhotoVO>  list = sql.selectList("photo.photo_list" , param);
		return list;
	}
	
	//폴더(앨범) 목록
	public List<FolderVO> getFolderList(FolderVO vo){
		List<FolderVO> list = sql.selectList("photo.folder_list",vo);
		return list;
	}
	
	//폴더(앨범) 추가
	public void folderInsert(FolderVO vo) {
		int result = sql.insert("photo.folder_insert", vo);
		System.out.println("성공여부 : " + result);
	}
	
	
	//파일 db 추가
	public int photoInsert(PhotoVO vo) {
		int result = sql.insert("photo.photo_insert", vo);
		System.out.println("성공여부 : " + result);
		return result;
	}
	
	//파일 생성
	public void file(PhotoVO vo, String filePath) {
		int result = sql.insert("photo.file", vo);
		System.out.println("성공여부 : " + result);
	}
	
	//폴더(앨범) 삭제
	public int folderDelete(FolderVO vo) {
		int result = sql.delete("photo.folder_delete", vo);
		return result;
	}
	
	//파일 삭제
	public int fileDelete(PhotoVO vo) {
		int result = sql.delete("photo.file_delete", vo);
		return result;
	}
	
}
