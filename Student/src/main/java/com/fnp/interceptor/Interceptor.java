package com.fnp.interceptor;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Interceptor implements HandlerInterceptor{
	
	private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Interceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("request");
		log.info(request+"  "+request.getMethod());
		
//		Long time = System.currentTimeMillis();
//		Thread.sleep(10000);
//		time = time + (10*60*1000);
//		if(System.currentTimeMillis()<time) {
//			return false;
//		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date = new java.util.Date();
		System.out.println(dateFormat.format(date));
		
		//Long time = System.currentTimeMillis();
		java.util.Date sysDate = new java.util.Date();

		String s = "30/11/2022";
		date = dateFormat.parse(s);
		System.out.println(date);
		System.out.println(sysDate);
		if(sysDate.getDate()==date.getDate()) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("resp");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("complete");
	}
}
