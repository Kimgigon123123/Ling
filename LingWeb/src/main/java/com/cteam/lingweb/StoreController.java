package com.cteam.lingweb;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import store.StoreMyinfoVO;
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
	
	
	@RequestMapping(value = "/store_dr", method = RequestMethod.GET)
	public String store_dr(HttpSession session, Model model) {
		session.setAttribute("active_category", "Dr");
		List<StoreVO> list = sql.selectList("store.store_dr");
		String total_sales = sql.selectOne("store.store_total_dr");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_ri", method = RequestMethod.GET)
	public String store_ri(HttpSession session, Model model) {
		session.setAttribute("active_category", "Ri");
		List<StoreVO> list = sql.selectList("store.store_ri");
		String total_sales = sql.selectOne("store.store_total_ri");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_gi", method = RequestMethod.GET)
	public String store_gi(HttpSession session, Model model) {
		session.setAttribute("active_category", "Gi");
		List<StoreVO> list = sql.selectList("store.store_gi");
		String total_sales = sql.selectOne("store.store_total_gi");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_etc", method = RequestMethod.GET)
	public String store_etc(HttpSession session, Model model) {
		session.setAttribute("active_category", "Etc");
		List<StoreVO> list = sql.selectList("store.store_etc");
		String total_sales = sql.selectOne("store.store_total_etc");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_name_by", method = RequestMethod.GET)
	public String store_name_by(HttpSession session, Model model) {
		session.setAttribute("active_category", "store");
		List<StoreVO> list = sql.selectList("store.store_name_by");
		String total_sales = sql.selectOne("store.store_select_totalsales");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_name_dr", method = RequestMethod.GET)
	public String store_name_dr(HttpSession session, Model model) {
		session.setAttribute("active_category", "Dr");
		List<StoreVO> list = sql.selectList("store.store_name_dr");
		String total_sales = sql.selectOne("store.store_total_dr");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_name_ri", method = RequestMethod.GET)
	public String store_name_ri(HttpSession session, Model model) {
		session.setAttribute("active_category", "Ri");
		List<StoreVO> list = sql.selectList("store.store_name_ri");
		String total_sales = sql.selectOne("store.store_total_ri");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_name_gi", method = RequestMethod.GET)
	public String store_name_gi(HttpSession session, Model model) {
		session.setAttribute("active_category", "Gi");
		List<StoreVO> list = sql.selectList("store.store_name_gi");
		String total_sales = sql.selectOne("store.store_total_gi");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_name_etc", method = RequestMethod.GET)
	public String store_name_etc(HttpSession session, Model model) {
		session.setAttribute("active_category", "Etc");
		List<StoreVO> list = sql.selectList("store.store_name_etc");
		String total_sales = sql.selectOne("store.store_total_etc");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_price_by", method = RequestMethod.GET)
	public String store_price_by(HttpSession session, Model model) {
		session.setAttribute("active_category", "store");
		List<StoreVO> list = sql.selectList("store.store_price_by");
		String total_sales = sql.selectOne("store.store_select_totalsales");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	
	@RequestMapping(value = "/store_price_dr", method = RequestMethod.GET)
	public String store_price_dr(HttpSession session, Model model) {
		session.setAttribute("active_category", "Dr");
		List<StoreVO> list = sql.selectList("store.store_price_dr");
		String total_sales = sql.selectOne("store.store_total_dr");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_price_ri", method = RequestMethod.GET)
	public String store_price_ri(HttpSession session, Model model) {
		session.setAttribute("active_category", "Ri");
		List<StoreVO> list = sql.selectList("store.store_price_ri");
		String total_sales = sql.selectOne("store.store_total_ri");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_price_gi", method = RequestMethod.GET)
	public String store_price_gi(HttpSession session, Model model) {
		session.setAttribute("active_category", "Gi");
		List<StoreVO> list = sql.selectList("store.store_price_gi");
		String total_sales = sql.selectOne("store.store_total_gi");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_price_etc", method = RequestMethod.GET)
	public String store_price_etc(HttpSession session, Model model) {
		session.setAttribute("active_category", "Etc");
		List<StoreVO> list = sql.selectList("store.store_price_etc");
		String total_sales = sql.selectOne("store.store_total_etc");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_sales_by", method = RequestMethod.GET)
	public String store_sales_by(HttpSession session, Model model) {
		session.setAttribute("active_category", "store");
		List<StoreVO> list = sql.selectList("store.store_sales_by");
		String total_sales = sql.selectOne("store.store_select_totalsales");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_sales_dr", method = RequestMethod.GET)
	public String store_sales_dr(HttpSession session, Model model) {
		session.setAttribute("active_category", "Dr");
		List<StoreVO> list = sql.selectList("store.store_sales_dr");
		String total_sales = sql.selectOne("store.store_total_dr");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_sales_ri", method = RequestMethod.GET)
	public String store_sales_ri(HttpSession session, Model model) {
		session.setAttribute("active_category", "Ri");
		List<StoreVO> list = sql.selectList("store.store_sales_ri");
		String total_sales = sql.selectOne("store.store_total_ri");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_sales_gi", method = RequestMethod.GET)
	public String store_sales_gi(HttpSession session, Model model) {
		session.setAttribute("active_category", "Gi");
		List<StoreVO> list = sql.selectList("store.store_sales_gi");
		String total_sales = sql.selectOne("store.store_total_gi");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}

	@RequestMapping(value = "/store_sales_etc", method = RequestMethod.GET)
	public String store_sales_etc(HttpSession session, Model model) {
		session.setAttribute("active_category", "Etc");
		List<StoreVO> list = sql.selectList("store.store_sales_etc");
		String total_sales = sql.selectOne("store.store_total_etc");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_amount", method = RequestMethod.GET)
	public String store_amount(HttpSession session, Model model) {
		session.setAttribute("active_category", "store");
		List<StoreVO> list = sql.selectList("store.store_amount");
		String total_sales = sql.selectOne("store.store_select_totalsales");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_amount_dr", method = RequestMethod.GET)
	public String store_amount_dr(HttpSession session, Model model) {
		session.setAttribute("active_category", "Dr");
		List<StoreVO> list = sql.selectList("store.store_amount_dr");
		String total_sales = sql.selectOne("store.store_total_dr");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_amount_ri", method = RequestMethod.GET)
	public String store_amount_ri(HttpSession session, Model model) {
		session.setAttribute("active_category", "Ri");
		List<StoreVO> list = sql.selectList("store.store_amount_ri");
		String total_sales = sql.selectOne("store.store_total_ri");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_amount_gi", method = RequestMethod.GET)
	public String store_amount_gi(HttpSession session, Model model) {
		session.setAttribute("active_category", "Gi");
		List<StoreVO> list = sql.selectList("store.store_amount_gi");
		String total_sales = sql.selectOne("store.store_total_gi");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/store_amount_etc", method = RequestMethod.GET)
	public String store_amount_etc(HttpSession session, Model model) {
		session.setAttribute("active_category", "Etc");
		List<StoreVO> list = sql.selectList("store.store_amount_etc");
		String total_sales = sql.selectOne("store.store_total_etc");
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
	public String accept_return(HttpSession session, Model model,String returnCodes,String userids) {
		session.setAttribute("active_category", "Dr");
		String[] returnCodeArr = returnCodes.split(","); 
		String[] userIdArr = userids.split(",");
		ArrayList<StoreReturnVO> list = new ArrayList<StoreReturnVO>();
		
		for (int i = 0; i < returnCodeArr.length; i++) {
			StoreReturnVO vo = new StoreReturnVO();
			vo.setId(userIdArr[i]);
			vo.setReturn_code(Integer.parseInt(returnCodeArr[i]));
			list.add(vo);
		}
		
		
		int result = sql.update("store.store_return_update",list);
		int result2 = sql.update("store.store_return_update2",list);
		
		
	
		
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
		
		
		String uploadPath = "D:\\Ling\\Ling\\image\\store\\"; // 디렉토리 경로

		// 디렉토리 생성
		File uploadDirectory = new File(uploadPath);
		if (!uploadDirectory.exists()) {
		    if (uploadDirectory.mkdirs()) {
		        System.out.println("디렉토리 생성 성공");
		    } else {
		        System.out.println("디렉토리 생성 실패");
		    }
		}
		
		 if (file != null && !file.isEmpty()) {
//				String uploadPath="D:\\Ling\\Ling\\image\\store\\";
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
					
					
					String uploadPath = "D:\\Ling\\Ling\\image\\store\\"; // 디렉토리 경로

					// 디렉토리 생성
					File uploadDirectory = new File(uploadPath);
					if (!uploadDirectory.exists()) {
					    if (uploadDirectory.mkdirs()) {
					        System.out.println("디렉토리 생성 성공");
					    } else {
					        System.out.println("디렉토리 생성 실패");
					    }
					}
					
					if (file != null && !file.isEmpty()) {
//						String uploadPath="D:\\Ling\\Ling\\image\\store\\";
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
				public String changelist(int tablename, Model model,HttpSession session ) {
					if(tablename==0) {
						session.setAttribute("active_category", "store");
						List<StoreVO> list = sql.selectList("store.store_select");
						String total_sales = sql.selectOne("store.store_select_totalsales");
						model.addAttribute("list",list);
						model.addAttribute("total_sales",total_sales);
						return "storelist/folder/all";	
					}
					else if(tablename==1) {
						session.setAttribute("active_category", "Dr");
						List<StoreVO> list = sql.selectList("store.store_dr");
						String total_sales = sql.selectOne("store.store_total_dr");
						model.addAttribute("list",list);
						model.addAttribute("total_sales",total_sales);
						return "storelist/folder/all";			
					}else if(tablename==2) {
						session.setAttribute("active_category", "Ri");
						List<StoreVO> list = sql.selectList("store.store_ri");
						String total_sales = sql.selectOne("store.store_total_ri");
						model.addAttribute("list",list);
						model.addAttribute("total_sales",total_sales);
						return "storelist/folder/all";	
					}else if(tablename==3) {
						session.setAttribute("active_category", "Gi");
						List<StoreVO> list = sql.selectList("store.store_gi");
						String total_sales = sql.selectOne("store.store_total_gi");
						model.addAttribute("list",list);
						model.addAttribute("total_sales",total_sales);
						return "storelist/folder/all";
					}
					else {
						session.setAttribute("active_category", "Etc");
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
		
				
				
				@RequestMapping("/deliverylist")
				public String deliverylist(int tablename, Model model,HttpSession session ) {
					if(tablename==0) {
						session.setAttribute("active_category", "store");
						List<StoreVO> list = sql.selectList("store.store_delivery_all");
					
						model.addAttribute("list",list);
						
						return "storelist/folder/delivery";	
					}
					else if(tablename==1) {
						session.setAttribute("active_category", "store");
						List<StoreVO> list = sql.selectList("store.store_delivery_ing");
						model.addAttribute("list",list);
						return "storelist/folder/delivery";		
						
					}else if(tablename==2) {
						session.setAttribute("active_category", "store");
						List<StoreVO> list = sql.selectList("store.store_delivery_complete");
						model.addAttribute("list",list);
						return "storelist/folder/delivery";	
						
					}else {
						session.setAttribute("active_category", "store");
						List<StoreVO> list = sql.selectList("store.store_delivery_cancel2");
						model.addAttribute("list",list);
						return "storelist/folder/delivery";	
						
					}
					
			
				}
				
				
				@RequestMapping("/returnlist")
				public String returnlist(int tablename, Model model,HttpSession session ) {
					if(tablename==0) {
						session.setAttribute("active_category", "store");
						List<StoreVO> list = sql.selectList("store.store_return_all");
					
						model.addAttribute("list",list);
						
						return "storelist/folder/return";	
					}
					else if(tablename==1) {
						session.setAttribute("active_category", "store");
						List<StoreVO> list = sql.selectList("store.store_return_ing");
						model.addAttribute("list",list);
						return "storelist/folder/return";		
						
					}else if(tablename==2) {
						session.setAttribute("active_category", "store");
						List<StoreVO> list = sql.selectList("store.store_return_complete");
						model.addAttribute("list",list);
						return "storelist/folder/return";	
						
					}else {
						session.setAttribute("active_category", "store");
						List<StoreVO> list = sql.selectList("store.store_return_cancel2");
						model.addAttribute("list",list);
						return "storelist/folder/return";	
						
					}
					
			
				}
				
				@RequestMapping("/store_delete")
				public String store_delete(String item_code) {
					
					int result2 = sql.delete("store.store_delete_zzim",item_code);
					int result3 = sql.delete("store.store_delete_buylist",item_code);
					int result4 = sql.delete("store.store_delete_return",item_code);
					int result5 = sql.delete("store.store_delete_basket",item_code);
					int result = sql.delete("store.store_delete",item_code);
					return "redirect:/store";
				}
	

	
	
	
	@RequestMapping(value = "/addstore", method = RequestMethod.GET)
	public String addproduct() {

		
		return "addstore";
	}
}
