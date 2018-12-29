package cn.uicp.mouse.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import model.User;


public class TokenInterceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println(111);
		// 获取请求的URL  
        String url = request.getRequestURI(); 
        if (url.indexOf("/users/login") >= 0 || url.indexOf("/test/") >= 0  
                ||  url.indexOf("/swagger") >= 0 || url.indexOf("/index/error")>=0) {  
            return true;  
        } 
		
		
		String token=request.getHeader("token");
		//String token = request.getParameter("token");
		// token is not needed when debug
		if(token == null) {
			response.sendRedirect(request.getContextPath() + "/index/error");
			return true;  // !! remember to comment this when deploy on server !!
		}
			
		
		User user = JWT.unsign(token, User.class);
		if (user != null) {
			return true;
		}
		response.sendRedirect(request.getContextPath() + "/index/error");
		return false;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//返回时重新生成
		String token=request.getHeader("token");
		response.addHeader("token", token);
		response.setHeader("token4", token);
		request.setAttribute("token", token);
		System.out.println("123");
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println(456);
		
	}
}