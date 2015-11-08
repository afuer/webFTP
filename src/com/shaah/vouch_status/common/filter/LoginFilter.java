package com.shaah.vouch_status.common.filter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shaah.vouch_status.common.dtos.UserDto;
import com.shaah.vouch_status.common.utils.CommonUtils;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("on filter");
		HttpServletRequest req =(HttpServletRequest) request;
		String uri = req.getRequestURI();
//		String att= req.getSession().getAttribute("loggedIn").toString();
		UserDto user = (UserDto)req.getSession().getAttribute(CommonUtils.USER_ATTR);
		if(user!=null&&user.getId().compareTo(BigDecimal.ZERO)>0){
				if(uri.endsWith("/index.jsp")||uri.endsWith("/"+CommonUtils.BASE_URL)){
					
					((HttpServletResponse)response).sendRedirect("/"+CommonUtils.BASE_URL+"ftp/ftpConfig.jsp");
				
				}else{
					chain.doFilter(request, response);
				}
			
		}else{
			
			
			
			System.out.println("the attribute null ..  "+uri);
			if(uri.endsWith("/index.jsp")){
				
				chain.doFilter(request, response);
			
			}else{
			System.out.println(">>> not end with index");
				((HttpServletResponse)response).sendRedirect("/"+CommonUtils.BASE_URL+"index.jsp");
				
			}
		}
//		System.out.println(""+req.getSession().getAttribute("loggedIn"));
//		while(atts.hasMoreElements()){
//			System.out.println(">>> "+atts.nextElement());
//		}
		// pass the request along the filter chain
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
