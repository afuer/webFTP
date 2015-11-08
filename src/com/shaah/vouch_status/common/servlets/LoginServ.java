package com.shaah.vouch_status.common.servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shaah.vouch_status.common.baos.CommonBao;
import com.shaah.vouch_status.common.dtos.UserDto;
import com.shaah.vouch_status.common.utils.CommonUtils;

/**
 * Servlet implementation class LoginServ
 */
public class LoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mail = request.getParameter("userName").trim();
		String pass = request.getParameter("password").trim();
		
		UserDto user = new UserDto();
		user.setUserName(mail);
		user.setPassword(pass);;
		user = new CommonBao().login(user);
		
		if(user !=null && user.getId()!=null){
//			System.out.println(">>>>>innnnnnnnnnnnnnn");
			
			request.getSession().setAttribute(CommonUtils.USER_ATTR,user);
			ServletContext context = getServletContext();
			RequestDispatcher requestDispatcher = context.getRequestDispatcher("/ftp/ftpConfig.jsp");
			
			requestDispatcher.forward(request, response);
		}else{
			request.setAttribute("loginStatus","Please check username and password and try again !!" );
			
			ServletContext context = getServletContext();
			RequestDispatcher requestDispatcher = context.getRequestDispatcher("/index.jsp");
			
			requestDispatcher.forward(request, response);
		}
	}

}
