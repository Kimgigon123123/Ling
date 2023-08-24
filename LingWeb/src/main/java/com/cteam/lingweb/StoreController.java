package com.cteam.lingweb;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	
	@RequestMapping(value="store_insert",method = RequestMethod.GET)
	public String store_insert(StoreVO vo) {
		int result = sql.insert("store.store_insert",vo);
		return "redirect:/store";
	}
	
	@RequestMapping(value = "/addstore", method = RequestMethod.GET)
	public String addproduct() {

		
		return "addstore";
	}
}
