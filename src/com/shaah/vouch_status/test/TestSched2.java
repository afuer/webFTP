package com.shaah.vouch_status.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

/**
 * Servlet implementation class TestSched2
 */
@WebServlet("/testSched2")
public class TestSched2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestSched2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
//		
//		
//		JobDetail job = JobBuilder.newJob(QuartizJobTest2.class)
//				.withIdentity("dummyJobName2", "group2").build();
//		 
//        //Quartz 1.6.3
//// SimpleTrigger trigger = new SimpleTrigger();
//// trigger.setStartTime(new Date(System.currentTimeMillis() + 1000));
//// trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
//// trigger.setRepeatInterval(30000);
//
//// Trigger the job to run on the next round minute
//Trigger trigger = TriggerBuilder
//	.newTrigger()
//	.withIdentity("dummyTriggerName2", "group2")
//	.withSchedule(
//		SimpleScheduleBuilder.simpleSchedule()
//			.withIntervalInSeconds(10).repeatForever())
//	.build();
//
//// schedule it
//Scheduler scheduler;
//try {
//	scheduler =(Scheduler) request.getServletContext().getAttribute("sched");
//	scheduler.start();
//	scheduler.scheduleJob(job, trigger);
//	request.getServletContext().setAttribute("sched",scheduler);
//} catch (SchedulerException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
//		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
