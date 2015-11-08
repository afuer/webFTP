package com.shaah.vouch_status.quartiz_sched.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.google.gson.Gson;

import com.shaah.vouch_status.common.baos.CommonBao;
import com.shaah.vouch_status.common.dtos.UserDto;
import com.shaah.vouch_status.common.utils.CommonUtils;
import com.shaah.vouch_status.quartiz_sched.baos.JobControlBao;
import com.shaah.vouch_status.quartiz_sched.dtos.JobConfigDto;

/**
 * Servlet implementation class LoadUserJobsServ
 */
@WebServlet("/LoadUserJobsServ")
public class LoadUserJobsServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadUserJobsServ() {
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
		System.out.println("in the tree servlet ...");
		HttpSession session = request.getSession();
		UserDto user =(UserDto) session.getAttribute(CommonUtils.USER_ATTR);
//		user = new CommonBao().login(user);
		System.out.println(">>> the user id is : "+user.getId());
		if(user!=null && user.getId().compareTo(BigDecimal.ZERO)>0){
			JobControlBao bao = new JobControlBao();
			ArrayList<JobConfigDto> jobs = bao.loadUserJobs(user.getId());
			String gsonStr = bao.getJobsGson(jobs);
//			Gson gson = new Gson();
			System.out.println(">>> the gson is : "+gsonStr);
			PrintWriter writer = response.getWriter();
			writer.print(gsonStr);
			writer.close();
			
		}
	}

}
