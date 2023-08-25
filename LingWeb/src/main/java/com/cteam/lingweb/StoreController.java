package com.cteam.lingweb;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import store.StoreVO;
import storereturn.StoreReturnVO;

@Controller
public class StoreController {
	@Autowired @Qualifier ("test") SqlSession sql;
	
	@RequestMapping(value = "/store", method = RequestMethod.GET)
	public String store(HttpSession session, Model model) {
		session.setAttribute("active_category", "store");
		List<StoreVO> list = sql.selectList("store.store_select");
		String total_sales = sql.selectOne("store.store_select_totalsales");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_delivery", method = RequestMethod.GET)
	public String store_delivery(HttpSession session, Model model) {
		session.setAttribute("active_category", "store");
		List<StoreVO> list = sql.selectList("store.store_delivery_select");
		model.addAttribute("list",list);
		return "store_delivery";
	}
	
	@RequestMapping(value = "/store_return", method = RequestMethod.GET)
	public String store_return(HttpSession session, Model model) {
		session.setAttribute("active_category", "store");
		List<StoreVO> list = sql.selectList("store.store_return_select");
		model.addAttribute("list",list);
		return "store_return";
	}
	
	@ResponseBody@RequestMapping(value = "/accept_return", method = RequestMethod.GET)
	public String accept_return(HttpSession session, Model model,String returnCodes) {
		session.setAttribute("active_category", "store");
		int result = sql.update("store.store_return_update",returnCodes);
		
		return String.valueOf(result);
	}
	
	@ResponseBody@RequestMapping(value = "/cancel_return", method = RequestMethod.GET)
	public String cancel_return(HttpSession session, Model model,String returnCodes) {
		session.setAttribute("active_category", "store");
		int result = sql.update("store.store_return_cancel",returnCodes);
		
		return String.valueOf(result);
	}
	
	@ResponseBody@RequestMapping(value = "/accept_delivery", method = RequestMethod.GET)
	public String accept_delivery(HttpSession session, Model model,String orderNums) {
		session.setAttribute("active_category", "store");
		int result = sql.update("store.store_delivery_update",orderNums);
		System.out.println(result);
		
		return String.valueOf(result);
	}
	
	@ResponseBody@RequestMapping(value = "/cancel_delivery", method = RequestMethod.GET)
	public String cancel_delivery(HttpSession session, Model model,String orderNums) {
		session.setAttribute("active_category", "store");
		int result = sql.update("store.store_delivery_cancel",orderNums);
		System.out.println(result);
		
		return String.valueOf(result);
	}
	
	
	@RequestMapping(value="store_insert",method = RequestMethod.POST)
	public String store_insert(StoreVO vo,MultipartFile file,HttpServletRequest request) throws Exception {
		
		
		 if (file != null && !file.isEmpty()) {
				String uploadPath="D:\\Ling\\Ling\\image\\store\\";
				String filename = file.getOriginalFilename();
				File filePath = new File(uploadPath, filename);
				String item_img = "http://192.168.0.36:8080/ling/image/store/"+filename;
				vo.setItem_img(item_img);
				 file.transferTo(filePath);
				
		 }
		 
		 int result = sql.insert("store.store_insert",vo);
		
		
		return "redirect:/store";
	}
	
	// 상세 정보 화면
		@RequestMapping(value="/store_info", method = RequestMethod.GET)
		public String info(Model model, String item_code) {
			
			StoreVO vo = sql.selectOne("store.info", item_code);
			model.addAttribute("vo",vo );
			
			return "store_info";
		}
	
		
		// 수정 화면
				@RequestMapping(value="/store_update", method = RequestMethod.GET)
				public String store_update(Model model, String item_code) {
					
					StoreVO vo = sql.selectOne("store.info", item_code);
					model.addAttribute("vo",vo );
					
					return "store_update";
				}
		
				// 수정완료
				@RequestMapping(value="/store_complete_update", method = RequestMethod.POST)
				public String store_complete_update(Model model, StoreVO vo,MultipartFile file) throws Exception {
					
					
					if (file != null && !file.isEmpty()) {
						String uploadPath="D:\\Ling\\Ling\\image\\store\\";
						String filename = file.getOriginalFilename();
						File filePath = new File(uploadPath, filename);
						String item_img = "http://192.168.0.36:8080/ling/image/store/"+filename;
						vo.setItem_img(item_img);
						 file.transferTo(filePath);
						
				 }
					
					int result = sql.update("store.store_update",vo);
					
					
					return "redirect:/store";
				}
		
				
				@RequestMapping("/storelist")
				public String changelist(int tablename, Model model ) {
					if(tablename==0) {
						List<StoreVO> list = sql.selectList("store.store_select");
						String total_sales = sql.selectOne("store.store_select_totalsales");
						model.addAttribute("list",list);
						model.addAttribute("total_sales",total_sales);
						return "storelist/folder/all";	
					}
					else if(tablename==1) {
						List<StoreVO> list = sql.selectList("store.store_dr");
						String total_sales = sql.selectOne("store.store_total_dr");
						model.addAttribute("list",list);
						model.addAttribute("total_sales",total_sales);
						return "storelist/folder/all";			
					}else if(tablename==2) {
						List<StoreVO> list = sql.selectList("store.store_ri");
						String total_sales = sql.selectOne("store.store_total_ri");
						model.addAttribute("list",list);
						model.addAttribute("total_sales",total_sales);
						return "storelist/folder/all";	
					}else if(tablename==3) {
						List<StoreVO> list = sql.selectList("store.store_gi");
						String total_sales = sql.selectOne("store.store_total_gi");
						model.addAttribute("list",list);
						model.addAttribute("total_sales",total_sales);
						return "storelist/folder/all";
					}
					else {
						List<StoreVO> list = sql.selectList("store.store_etc");
						String total_sales = sql.selectOne("store.store_total_etc");
						model.addAttribute("list",list);
						model.addAttribute("total_sales",total_sales);
						return "storelist/folder/all";
					}
//					else if(tablename==1) {
//						model.addAttribute("list", dao.couplelist());
//						return "memberlist/folder/couple";
//					}else {
//						model.addAttribute("list", dao.adminlist());
//						return "memberlist/folder/member";
//					}
				}
		
				
				@RequestMapping("/store_delete")
				public String store_delete(String item_code) {
					int result = sql.delete("store.store_delete",item_code);
					
					return "redirect:/store";
				}
	

	
	
	
	@RequestMapping(value = "/addstore", method = RequestMethod.GET)
	public String addproduct() {

		
		return "addstore";
	}
}
