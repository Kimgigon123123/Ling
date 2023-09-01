package com.cteam.ling;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;

import photo.FolderVO;
import photo.PhotoDAO;
import photo.PhotoVO;

@RestController
public class PhotoController {
	
	@Autowired PhotoDAO dao;
	@Autowired SqlSession sql;
	public static String folderPath = "D:\\Ling\\Ling\\image\\photo\\";
//	public static String folderPath = "D:\\WorkSpace\\Ling\\image\\photo\\";

	
	
	//폴더명을 파라메터로 받아와야함/.
	@RequestMapping(value="/file", produces="text/html;charset=utf-8")
	public String list(HttpServletRequest req, String couple_num, String folder_name, String voJson, String pho_img
						) throws IllegalStateException, IOException {
		
		
		
		
		System.out.println(req.getLocalAddr());
		System.out.println(req.getLocalPort());
		System.out.println(req.getContextPath() + "/폴더");
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("couple_num", couple_num);
		
		MultipartRequest mReq = (MultipartRequest) req;
		MultipartFile file = mReq.getFile("file");
		
		String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();file.getOriginalFilename();
		
		String filePath = "http://" + req.getLocalAddr() + ":" + req.getLocalPort() + req.getContextPath() 
		+ "/images/photo/" + couple_num + folder_name + filename;
		
		FolderVO vo  = null;
		if(req.getParameter("folder")!=null) {
			 vo =   new Gson().fromJson(req.getParameter("folder"), FolderVO.class);
			folder_name = vo.getFolder_name();
			couple_num = vo.getCouple_num();
//			folder_num = vo.getFolder_num();
		}
	
		
		
		   PhotoVO photo_vo = null;  
		   if(req.getParameter("photo")!=null) { 
		  photo_vo = new Gson().fromJson(req.getParameter("test_and"), PhotoVO.class);
		  folder_name = photo_vo.getFolder_name();
		  pho_img = filePath;
		
		   }	
		//FolderVO vo = new Gson().fromJson(voJson, FolderVO.class);
		
		
		//			String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();file.getOriginalFilename();
					File targetFile = new File("D:\\Ling\\Ling\\image\\photo\\"+ couple_num + "\\" + folder_name , filename);
					
					
					try {
						file.transferTo(targetFile);
						
						
						
					} catch (Exception e) {
						System.out.println("사진 저장에 실패하였습니다.");
					}
					
					 
					 dao.file(vo, filePath);
		
		
		List<PhotoVO> list = dao.getList(param) ;

		Gson gson = new Gson();	
				
		return gson.toJson(list);
		
		
	}
	
//	public String photo_insert(PhotoVO vo, HttpServletRequest req, String id, String couple_num, String folder_name) {
//		
//		MultipartRequest mReq = (MultipartRequest) req;
//		MultipartFile file = mReq.getFile("file");
//		
//		String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
//	    String filePath = "http://" + req.getLocalAddr() + ":" + req.getLocalPort() + req.getContextPath() 
//	    					+ "/images/photo/" + couple_num + folder_name + filename;
//	    
//		HashMap<String, Object> param = new HashMap<String, Object>();
//		param.put("id", id);
//		param.put("couple_num", couple_num);
//		param.put("filePath", filePath);
//		
//		
//	    
//	    // photoInsert 메소드에서 param 대신 vo 객체를 넘겨주도록 수정
//	    
//		int result = dao.photoInsert(vo);
//		Gson gson = new Gson();
//		
//		return gson.toJson(result);
//		
//	}
	
	@RequestMapping(value="/folder_insert", produces="text/html;charset=utf-8")
	public String folder_insert(String voJson, HttpServletRequest req, String id, String couple_num) {
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("couple_num", couple_num);
		


		FolderVO vo = new Gson().fromJson(voJson, FolderVO.class);
		

		String tempPath = folderPath; 
		if (!vo.getFolder_name().isEmpty()) {
            // 특정 경로와 입력된 폴더 이름으로 폴더 경로를 생성
			tempPath = folderPath + "/" + vo.getCouple_num() + "/" + vo.getFolder_name();

//             
            // 폴더 생성 로직을 호출하여 폴더 생성
            createFolder(tempPath, req);
        }
		

			dao.folderInsert(vo) ;
			
			Gson gson = new Gson();	
			
			
			return gson.toJson(dao.getFolder(param));
		
	}
	
	private String createFolder(String folderPath, HttpServletRequest req) {
        File folder = new File(folderPath);

        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            if (created) {
                System.out.println("폴더가 생성되었습니다.");
            } else {
                System.out.println("폴더 생성에 실패하였습니다.");
            }
        }
        return replaceURL(req);
    }
	
	private String replaceURL(HttpServletRequest req) {
		String replaceURL = req.getContextPath();
		return "http://" + req.getLocalAddr() + ":" + req.getLocalPort() + replaceURL + "/images/photo/";
	}
	
	private String replaceURL(HttpServletRequest req , String couple_num, String file_name) {
		String replaceURL = req.getContextPath();
		return "http://" + req.getLocalAddr() + ":" + req.getLocalPort() + replaceURL + "/images/photo/" + couple_num + "/all" + file_name;
	}
	
	@RequestMapping(value="/photo_list", produces="text/html;charset=utf-8")
	public String photo_list(String id, String couple_num, String folder_name, String pho_img) {
		
		
		
		//folder_name을 비교해서 그 folder_name을 가진 view 클릭 시 그 폴더 내부의 이미지 조회
//		imageSelect(voJson, req);
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("couple_num", couple_num);
		param.put("folder_name", folder_name);
		param.put("pho_img", pho_img);		
		
		
		List<PhotoVO> list = dao.getList(param) ;

		Gson gson = new Gson();	
				
		return gson.toJson(list);
}
	
	
	
	@RequestMapping(value="/storage", produces="text/html;charset=utf-8")
	public String photo_storage(String id, String couple_num, String folder_name, String pho_img) {
		
		
		
		//folder_name을 비교해서 그 folder_name을 가진 view 클릭 시 그 폴더 내부의 이미지 조회
//		imageSelect(voJson, req);
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("couple_num", couple_num);
		param.put("folder_name", folder_name);
		param.put("pho_img", pho_img);		
		
		
		List<PhotoVO> list = dao.getList(param) ;
		
		Gson gson = new Gson();	
		
		return gson.toJson(list);
	}
	

	
	
	@RequestMapping(value="/folder_list", produces="text/html;charset=utf-8")
	public String folder_list(String id, String couple_num) {
			
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("id", id);
			param.put("couple_num", couple_num);
			List<FolderVO> list = dao.getFolder(param) ;

			Gson gson = new Gson();	
			
			return gson.toJson(list);
		
	}
	
	
	  @RequestMapping(value="/folder_LastImg", produces="text/html;charset=utf-8")
	  public String folder_LastImg(String id, String couple_num, String last_photo) {
	  
	  HashMap<String, Object> param = new HashMap<String, Object>();
	  param.put("id", id); 
	  param.put("couple_num", couple_num); //couple_num
	  List<FolderVO> list = dao.getFolder(param) ;
	  
	  Gson gson = new Gson();
	  
	  return gson.toJson(list);
	  
	  }
	 
	
	

	

	
//	@RequestMapping(value="/folder_delete",produces="text/html;charset=utf-8")
//	public String Folder_Delete(FolderVO vo) {
//		
//		File folder = new File(folderPath);
//		if(folder.exists()) {
//			boolean delete = folder.delete();
//			
//			if(delete) {
//				System.out.println("폴더가 삭제되었습니다.");
//			}else {
//				System.out.println("폴더 삭제에 실패했습니다.");
//			}
//		}
//		
//		
//		int result = dao.FolderDelete(vo);
//		
//		Gson gson = new Gson();
//		
//		return gson.toJson(result);
//	}
	
	@RequestMapping(value = "/folder_delete", produces = "text/html;charset=utf-8")
	public String folder_Delete(FolderVO vo) {

	    // 폴더 삭제 함수 호출
		String addPath = "/" + vo.getCouple_num() + "/" + vo.getFolder_name();
		String fullPath = folderPath + addPath;
	    boolean deleteResult = deleteFolder(new File(fullPath));

	    int result = dao.folderDelete(vo);

	    Gson gson = new Gson();

	    return gson.toJson(result);
	}

	// 폴더와 하위 폴더, 파일들을 재귀적으로 삭제하는 함수
	private boolean deleteFolder(File folder) {
	    if (folder.exists()) {
	        File[] files = folder.listFiles();
	        if (files != null) {
	            for (File file : files) {
	                if (file.isDirectory()) {
	                    deleteFolder(file); // 하위 폴더 삭제
	                } else {
	                    file.delete(); // 파일 삭제
	                }
	            }
	        }
	        return folder.delete(); // 폴더 삭제
	    }
	    return false; // 폴더가 존재하지 않을 경우에도 실패로 간주
	}
	
	
	@RequestMapping(value = "/file_delete", produces = "text/html;charset=utf-8")
	public String file_Delete(PhotoVO vo) {
		
		int result = dao.fileDelete(vo);

	    Gson gson = new Gson();

	    return gson.toJson(result);
	
		
	}
	
	
	private boolean checkFolderExist(String folderPath) {
	    File folder = new File(folderPath);
	    return folder.exists();
	}
	


	
	

	
}
