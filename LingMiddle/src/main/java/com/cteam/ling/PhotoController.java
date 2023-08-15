package com.cteam.ling;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import schedule.ScheDAO;

@RestController
public class PhotoController {
	
	@Autowired PhotoDAO dao;
	String ip = "192.168.0.28"; // 여기에 컴퓨터의 IP 주소를 넣으세요
	
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
	public String photo_list() {
			


			

		List<PhotoVO> list = dao.getList() ;

		String imageExtension = ""; // 이미지 확장자를 저장할 변수 초기화
		
		String imageName = "";
		// 이미지 확장자가 "png" 또는 "jpg"일 때 처리
		if (imageName.endsWith(".png") || imageName.endsWith(".jpg")) {
		    imageExtension = imageName.substring(imageName.lastIndexOf(".") + 1);

		    // 이미지 확장자에 따라 다른 이미지 URL을 생성
		    if (!imageExtension.isEmpty()) {
		        String imageUrl = "http://" + ip + ":8080/ling/image/photo/" + imageName;

		        // imageUrl을 이용하여 이미지를 서버로부터 가져오는 코드를 추가하세요
		        // 예: 이미지를 로드하는 라이브러리나 네트워크 요청 코드를 사용하여 이미지를 가져옴
		        // 예를 들어, Android의 Picasso, Glide, Volley, Retrofit 등을 활용할 수 있습니다.
		    }
		}
//			ulleung.png
			
			Gson gson = new Gson();	
			
			return gson.toJson(list);
		
	}
	
	@RequestMapping(value="/folder_list", produces="text/html;charset=utf-8")
	public String folder_list() {
			

			List<FolderVO> list = dao.getFolder() ;
			
			Gson gson = new Gson();	
			
			return gson.toJson(list);
		
	}
	
	
	@RequestMapping(value="/folder_insert", produces="text/html;charset=utf-8")
	public String folder_insert(FolderVO vo) {
			

			dao.FolderInsert(vo) ;
			
			Gson gson = new Gson();	
			
			return gson.toJson(vo);
		
	}
	
	
}
