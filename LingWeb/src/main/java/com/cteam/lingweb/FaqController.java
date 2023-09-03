package com.cteam.lingweb;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import faq.FaqDAO;
import faq.FaqVO;
import faq.PageVO;

@Controller
@RequestMapping("/faq")
public class FaqController {

	@Autowired
	private FaqDAO dao;

	// FAQ 목록조회
	@RequestMapping("/list")
	public String list(Model model, HttpSession session, PageVO page) {
		session.setAttribute("active_category", "faq");
		String activeCategory = (String) session.getAttribute("active_category");
		String loginId = "faq".equals(activeCategory) ? null : activeCategory;
//	 String loginId = (String) session.getAttribute("active_category");
//	List<FaqVO> list = dao.list();
//	model.addAttribute("list", list);
		model.addAttribute("page", dao.faq_list(page));

		model.addAttribute("loginId", loginId);
		return "default/faq/list";
	}

	// 신규 FAQ 글 작성화면
	@RequestMapping("/new")
	public String regist() {
		return "default/faq/new";
	}

	// 신규 FAQ 글 등록처리
	@RequestMapping("/faq_insert")
	public String faq_insert(FaqVO vo) {

		dao.faq_insert(vo);
		return "redirect:list";
	}

	// FAQ 글 정보 화면요청
	@RequestMapping("/faq_info")
	public String faq_content(int faq_no, Model model, PageVO page) {
		FaqVO vo =  dao.faq_info(faq_no);
		model.addAttribute("crlf", "\r\n"); // carrage return line feed
		model.addAttribute("lf", "\n"); // line feed
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);

		return "default/faq/faq_info";
	}
	
	@RequestMapping("/faq_modify")
	public String faq_modify(PageVO page, Model model, Integer faq_no) {
		FaqVO vo = dao.faq_info(faq_no);
		model.addAttribute("crlf", "\r\n"); // carrage return line feed
		model.addAttribute("lf", "\n"); // line feed
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		return "default/faq/faq_modify";
	}
	
	@RequestMapping("/faq_update")
	public String faq_update(FaqVO vo, PageVO page) {
		dao.faq_update(vo);
		return "redirect:faq_info?faq_no=" + vo.getFaq_no()
				+ "&curPage=" + page.getCurPage()
				+ "&search=" + page.getSearch();
	}
	
	@RequestMapping("/faq_delete")
	public String faq_delete(PageVO page, Integer faq_no) throws UnsupportedEncodingException {
		dao.faq_delete(faq_no);
		return "redirect:list"
				+ "?curPage=" + page.getCurPage()
				+ "&search=" + page.getSearch()
				+ "&keyword=" + URLEncoder.encode(page.getKeyword(), "utf-8")
				;
	}
	

}
