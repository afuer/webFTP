package com.shaah.vouch_status.test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

/**
 * Servlet implementation class TestStopSched
 */
@WebServlet("/testStopSched")
public class TestStopSched extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestStopSched() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		System.out.println(">>>>>>>>>>>>>>>> will stop now !!");
//	try {
//		
//		Scheduler sched =(Scheduler) request.getServletContext().getAttribute("sched");
//		
//		
//		
//		JobKey key = null;
//		
//		for (String groupName : sched.getJobGroupNames()) {
//			for (JobKey jobKey : sched.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
//				String jobName = jobKey.getName();
//				String jobGroup = jobKey.getGroup();
////				List<Trigger> triggers = (List<Trigger>) sched.getTriggersOfJob(jobKey);
////				Date nextFireTime = triggers.get(0).getNextFireTime();
//				System.out.println("[jobName] : " + jobName + " [groupName] : "+ jobGroup);
//				if(jobName.equals("dummyJobName")&&jobGroup.equals("group1")){
//					key=jobKey;
//					break;
//				}
//			}
//			
//
//		}
//		
//		
//		
//		
//		
//		
//		
////		TriggerKey triggerKey = TriggerKey.triggerKey("crontrigger","DB");
////		sched.unscheduleJob(triggerKey);
//		
//		sched.deleteJob(key);
//		
//	} catch (SchedulerException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
