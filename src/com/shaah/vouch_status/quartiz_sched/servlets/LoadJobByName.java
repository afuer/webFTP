package com.shaah.vouch_status.quartiz_sched.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import com.shaah.vouch_status.common.dtos.UserDto;
import com.shaah.vouch_status.quartiz_sched.baos.JobControlBao;
import com.shaah.vouch_status.quartiz_sched.dtos.JobConfigDto;

/**
 * Servlet implementation class LoadJobByName
 */
@WebServlet("/loadJobByName")
public class LoadJobByName extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(LoadJobByName.class);   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadJobByName() {
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
		HttpSession session = request.getSession();
		UserDto user = (UserDto)session.getAttribute("user");
		String jobName=  user.getUserName()+"_"+request.getParameter("jobName");
		JobControlBao bao = new JobControlBao();
		JobConfigDto job = bao.getJob(jobName);
//		logger.info(">>>> the old job name is : "+job.getOldJobName());
		PrintWriter writer = response.getWriter();
		writer.print(new Gson().toJson(job));
		writer.close();
	}

}
