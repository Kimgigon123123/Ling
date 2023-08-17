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
	
	public List<FolderVO> getFolder(){
		List<FolderVO> list = sql.selectList("photo.folder");
		return list;
	}
	
	public void FolderInsert(FolderVO vo) {
		int result = sql.insert("photo.folder_insert", vo);
		System.out.println("성공여부 : " + result);
	}
	
	public int FolderDelete(FolderVO vo) {
		int result = sql.delete("photo.folder_delete", vo);
		return result;
	}
}
