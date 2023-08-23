package com.cteam.lingweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import chart.ChartDAO;

@RestController
@RequestMapping("/")
public class ChartController {
	@Autowired private ChartDAO service;
	
	@ResponseBody @RequestMapping("/age")
	public Object department() {
		//DB에서 부서별 사원수를 조회해와 응답한다.
		return service.age();
	}
	
}
