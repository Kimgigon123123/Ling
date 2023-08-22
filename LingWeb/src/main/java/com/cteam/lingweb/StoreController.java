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

import store.StoreVO;

@Controller
public class StoreController {
	@Autowired @Qualifier ("test") SqlSession sql;
	
	@RequestMapping(value = "/store", method = RequestMethod.GET)
	public String store(HttpSession session, Model model) {
		session.setAttribute("active_category", "store");
		List<StoreVO> list = sql.selectList("store.store_select");
		int total_sales = sql.selectOne("store.store_select_totalsales");
		model.addAttribute("list",list);
		model.addAttribute("total_sales",total_sales);
		return "store";
	}
	
	@RequestMapping(value = "/addstore", method = RequestMethod.GET)
	public String addproduct() {

		
		return "addstore";
	}
}
