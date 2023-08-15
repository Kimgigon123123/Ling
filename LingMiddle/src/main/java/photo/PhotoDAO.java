package photo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


@Repository
public class PhotoDAO {
	
	@Autowired @Qualifier ("test") SqlSession sql;
	
	public List<PhotoVO> getList(){
		List<PhotoVO>  list = sql.selectList("photo.list");
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
}
