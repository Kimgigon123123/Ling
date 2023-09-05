package chart;

import java.util.HashMap;
import java.util.List;

public interface ChartService {
	
 	List<HashMap<String, Object>> age(); //연령별 유저 수 조회
 	List<HashMap<String, Object>> period(); //연애기간별 유저 수 조회
 	List<HashMap<String, Object>> item_top10();
	
}
