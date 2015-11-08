package com.shaah.vouch_status.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;

import com.shaah.vouch_status.quartiz_sched.baos.ConfigSchedBao;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		System.out.println("will be destroyed !! ");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		System.out.println("will Load the whole jobs now ..... ");
		ConfigSchedBao configBao = new ConfigSchedBao();
		Scheduler sched=null;
		try {
			 sched = ((StdSchedulerFactory) event.getServletContext().getAttribute(QuartzInitializerListener.QUARTZ_FACTORY_KEY)).getScheduler();
//			 sched = new StdSchedulerFactory().getScheduler();
			sched.start();
//			event.getServletContext().setAttribute("sched",sched);
			
//			configBao.ConfigSched(configObj,sched);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		configBao.runValidSched(sched);

	}

}
