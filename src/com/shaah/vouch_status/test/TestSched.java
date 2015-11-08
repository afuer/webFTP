package com.shaah.vouch_status.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Servlet implementation class TestSched
 */
@WebServlet("/testSched")
public class TestSched extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestSched() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		JobDetail job = JobBuilder.newJob(QuartizJobTest.class)
//				.withIdentity("dummyJobName", "group1").build();
//
//// Trigger the job to run on the next round minute
//Trigger trigger = TriggerBuilder
//	.newTrigger()
//	.withIdentity("dummyTriggerName", "group1")
//	.withSchedule(
//		CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
//	.build();
//
//// schedule it
//Scheduler scheduler;
//try {
//	scheduler = new StdSchedulerFactory().getScheduler();
//	scheduler.start();
//	scheduler.scheduleJob(job, trigger);
//	request.getServletContext().setAttribute("sched",scheduler);
//} catch (SchedulerException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
