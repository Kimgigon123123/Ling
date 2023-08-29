package member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Intercepter implements HandlerInterceptor {
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
    	// 컨트롤러가 요청을 처리하기 전에 실행되는 코드
        HttpSession session = request.getSession();
        String loginId = (String) session.getAttribute("loginId");

        if (loginId == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return false; // 컨트롤러 실행을 중단
        }

        return true; // 컨트롤러 실행 계속
        
        
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        
    	// 컨트롤러가 요청을 처리한 후에 실행되는 코드
        
        
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        
    	// 뷰 렌더링까지 완료된 후 실행되는 코드
        
        
    }    
    
}