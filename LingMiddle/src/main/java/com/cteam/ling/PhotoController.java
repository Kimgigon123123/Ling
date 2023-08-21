package com.cteam.ling;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;

import photo.FolderVO;
import photo.PhotoDAO;
import photo.PhotoVO;
import schedule.ScheAddVO;

@RestController
public class PhotoController {
	
	@Autowired PhotoDAO dao;
	@Autowired SqlSession sql;
	public static String ip = "192.168.0.28";
	public static String folderPath = "D:\\Ling\\Ling\\image\\photo\\";
//	public static String folderPath = "D:\\WorkSpace\\Ling\\image\\photo\\";
	
	@RequestMapping(value="/file.f", produces="text/html;charset=utf-8")
	public String list(HttpServletRequest req) throws IllegalStateException, IOException { //req(요청에 대한 모든정보), res
		System.out.println(req.getLocalAddr());
		System.out.println(req.getLocalPort());
		System.out.println(req.getContextPath() + "/폴더");
		
		
		MultipartRequest mReq = (MultipartRequest) req;
		MultipartFile file = mReq.getFile("file");
		
		//파일이 있는 상태의 요청을 받았는지에 따라서 유동적으로 MultipartRequest로 캐스팅
		if (file != null) {
		    String originalFilename = file.getOriginalFilename();
		    File targetFile = new File("D:\\Ling\\Ling\\image\\photo", originalFilename);
		    
		    try {
		        file.transferTo(targetFile);
		    } catch (IOException e) {
		        e.printStackTrace();
		        // 업로드 실패 처리
		    }
		} else {
		    // 파일이 업로드되지 않은 경우 처리
		}
		//파일을 빼오기
		//물리적으로 저장하기.
		//Middle/img/파일명을 크롬으로 요청하면 열리게 하기.
		//실제 파일은 D:\Android\폴더\...
		return new Gson().toJson("");
		
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
					 //couple_num
			List<FolderVO> list = dao.getFolder(param) ;

			Gson gson = new Gson();	
			
			return gson.toJson(list);
		
	}
	
	@RequestMapping(value="/folder_LastImg", produces="text/html;charset=utf-8")
	public String folder_LastImg(String id, String couple_num) {
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("couple_num", couple_num);
		//couple_num
		List<FolderVO> list = dao.getFolder(param) ;
		
		Gson gson = new Gson();	
		
		return gson.toJson(list);
		
	}
	
	
	@RequestMapping(value="/folder_insert", produces="text/html;charset=utf-8")
	public String folder_insert(String voJson, HttpServletRequest req, String id, String couple_num) {
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("couple_num", couple_num);
		
//		HashMap<String, Object> params = new HashMap<String, Object>();


		FolderVO vo = new Gson().fromJson(voJson, FolderVO.class);
		

		String tempPath = folderPath; 
		if (!vo.getFolder_name().isEmpty()) {
            // 특정 경로와 입력된 폴더 이름으로 폴더 경로를 생성
			tempPath = folderPath + "/" + vo.getCouple_num() + "/" + vo.getFolder_name();
            
            
//             
            // 폴더 생성 로직을 호출하여 폴더 생성
            createFolder(tempPath, req);
        }
		

			dao.FolderInsert(vo) ;
			
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
	
	@RequestMapping(value="/folder_delete",produces="text/html;charset=utf-8")
	public String Folder_Delete(FolderVO vo) {
		
		File folder = new File(folderPath);
		if(folder.exists()) {
			boolean delete = folder.delete();
			
			if(delete) {
				System.out.println("폴더가 삭제되었습니다.");
			}else {
				System.out.println("폴더 삭제에 실패했습니다.");
			}
		}
		
		int result = dao.FolderDelete(vo);
		
		Gson gson = new Gson();
		
		return gson.toJson(result);
	}
	
	
	
	
//  if (folder.exists() && folder.isDirectory()) {
//  File[] files = folder.listFiles();
//  if (files != null) {
//      for (File file : files) {
//          if (file.isFile() && isImageFile(file.getName())) {
//          	for (int i = 0; i < files.length; i++) {
//          	    System.out.println("file: " + files[i]);
//          	}
//
//          }
//      }
//  }
//}
//
		
	
    private boolean isImageFile(String fileName) {
        // 이미지 파일 확장자 체크 (여기에 필요한 이미지 확장자를 추가하세요)
        return fileName.toLowerCase().endsWith(".jpg")
                || fileName.toLowerCase().endsWith(".jpeg")
                || fileName.toLowerCase().endsWith(".png")
                || fileName.toLowerCase().endsWith(".gif");
    }
	
	

	
	
	private String replaceURL(HttpServletRequest req) {
		String replaceURL = req.getContextPath();
		return "http://" + req.getLocalAddr() + ":" + req.getLocalPort() + replaceURL + "/images/photo/";
	}
	
	
//	@RequestMapping(value="/latest_image", produces="text/html;charset=utf-8")
//	public String latest_image(HttpServletRequest req) {
//	    String imageFolderPath = folderPath; // 이미지 폴더 경로를 지정하세요
//	    File folder = new File(imageFolderPath);
//
//	    if (folder.exists() && folder.isDirectory()) {
//	        File[] imageFiles = folder.listFiles(new FilenameFilter() {
//	            @Override
//	            public boolean accept(File dir, String name) {
//	                String lowercaseName = name.toLowerCase();
//	                return lowercaseName.endsWith(".jpg") || lowercaseName.endsWith(".jpeg") ||
//	                        lowercaseName.endsWith(".png") || lowercaseName.endsWith(".gif");
//	            }
//	        });
//
//	        if (imageFiles != null && imageFiles.length > 0) {
//	            Arrays.sort(imageFiles, Comparator.comparingLong(File::lastModified).reversed());
//	            String latestImagePath = imageFiles[0].getAbsolutePath();
//	            
//	            Gson gson = new Gson();
//	            return gson.toJson(latestImagePath);
//	        }
//	    }
//
//	    return replaceURL(req);
//	}
	
	
//	String imageExtension = ""; // 이미지 확장자를 저장할 변수 초기화
//	
//	String imageName = "";
//	// 이미지 확장자가 "png" 또는 "jpg"일 때 처리
//	if (imageName.endsWith(".png") || imageName.endsWith(".jpg")) {
//	    imageExtension = imageName.substring(imageName.lastIndexOf(".") + 1);
//
//	    // 이미지 확장자에 따라 다른 이미지 URL을 생성
//	    if (!imageExtension.isEmpty()) {
//	        String imageUrl = "http://" + ip + ":8080/ling/image/photo/" + imageName;
//
//	}
////		ulleung.png
	
}
