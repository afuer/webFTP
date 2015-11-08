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
 * Servlet implementation class StartJobWizNameServ
 */
@WebServlet("/StartJobWizNameServ")
public class StartJobWizNameServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(StartJobWizNameServ.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartJobWizNameServ() {
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
		String jobName=null;
		try {
		HttpSession session = request.getSession();
		UserDto user = (UserDto)session.getAttribute("user");
		jobName=  user.getUserName()+"_"+request.getParameter("jobName");
		JobControlBao bao = new JobControlBao();
		Scheduler sched;
		
			sched = ((StdSchedulerFactory) request.getServletContext().getAttribute(QuartzInitializerListener.QUARTZ_FACTORY_KEY)).getScheduler();
		
		
		System.out.println("Will start the service .... "+jobName);
		bao.startJob(jobName, sched);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			logger.error("Error During starting Job : "+jobName, e);
		}
	}

}
