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
	
	

	/* 설명 : ....
	 * System.out.println(req.getLocalAddr());
	 * System.out.println(req.getLocalPort());
	 * System.out.println(req.getContextPath() + "/폴더");
	 */
	
	@RequestMapping(value="/file", produces="text/html;charset=utf-8")
	public String list(HttpServletRequest req,  String tempVo
						) throws IllegalStateException, IOException {
		FolderVO vo = null;
		if(req.getParameter("tempVo")!=null) {
		 vo = new Gson().fromJson(req.getParameter("tempVo"), FolderVO.class);
		}else {
			return "";
		}
		
		MultipartRequest mReq = (MultipartRequest) req;
		MultipartFile file = mReq.getFile("file");
		
		String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
		
		
		String filePath = "http://" + req.getLocalAddr() + ":" + req.getLocalPort() + req.getContextPath() 
		+ "/photo/" + vo.getCouple_num() + "/" +vo.getFolder_name() + "/" + filename;

		
		
		   PhotoVO photo_vo = null;  
		   
		   if(file!=null) {//? 
		  photo_vo = new PhotoVO();
		  photo_vo.setFolder_num(vo.getFolder_num());
		  photo_vo.setPho_img(filePath);
		
		   }			
		   File targetFile = new File("D:\\Ling\\Ling\\image\\photo\\"+ vo.getCouple_num() + "\\" + vo.getFolder_name() , filename);
					
					
					try {
						file.transferTo(targetFile);
						dao.photoInsert(photo_vo);
						dao.folder_lastPhoto(vo);
						
						
					} catch (Exception e) {
						System.out.println("사진 저장에 실패하였습니다.");
					}
					
					 
		
		
		//?
		List<FolderVO> list = dao.getFolderList(vo) ;

		Gson gson = new Gson();	
				
		return gson.toJson(list);
		
		
	}
	

	
	//폴더 생성 시 vo에 커플넘버 , 아이디 , 폴더명 받아와야함.
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
			
			
			return gson.toJson(dao.getFolderList(vo));
		
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

	
	
	
	//폴더 내부 사진목록 조회 시 폴더 NUM하나만필요한 파라메터
	
	@RequestMapping(value="/photo_list", produces="text/html;charset=utf-8")
	public String photo_list(int folder_num) {

		
//		vo = new Gson().fromJson("vo", PhotoVO.class);
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("folder_num", folder_num);
		
		
		List<PhotoVO> list = dao.getPhotoList(param) ;
		Gson gson = new Gson();	
		return gson.toJson(list);
}
	

	
	
	@RequestMapping(value="/folder_list", produces="text/html;charset=utf-8")
	public String folder_list( FolderVO vo) {
			
		
			List<FolderVO> list = dao.getFolderList(vo) ;
			Gson gson = new Gson();	
			return gson.toJson(list);
	}
	
	

	
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

	
}
