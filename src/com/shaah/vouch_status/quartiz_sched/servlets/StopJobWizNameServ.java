package com.shaah.vouch_status.quartiz_sched.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;

import com.shaah.vouch_status.common.dtos.UserDto;
import com.shaah.vouch_status.quartiz_sched.baos.JobControlBao;

/**
 * Servlet implementation class StopJobWizName
 */
@WebServlet("/StopJobWizName")
public class StopJobWizNameServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(StopJobWizNameServ.class);   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StopJobWizNameServ() {
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
		String jobNameParam=null;
		try {
		jobNameParam=  request.getParameter("jobName");
		UserDto user = ((UserDto)request.getSession().getAttribute("user"));
		String jobName=user.getUserName()+"_"+jobNameParam;
		JobControlBao bao = new JobControlBao();
		Scheduler sched;
		
			sched = ((StdSchedulerFactory) request.getServletContext().getAttribute(QuartzInitializerListener.QUARTZ_FACTORY_KEY)).getScheduler();
		
		System.out.println("the job name is : "+jobName);
		bao.stopJob(jobName, sched);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			logger.error("Error in stopping Job : "+jobNameParam,e);
		}
	}

}
