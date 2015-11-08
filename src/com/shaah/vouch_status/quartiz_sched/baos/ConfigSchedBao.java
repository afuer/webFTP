package com.shaah.vouch_status.quartiz_sched.baos;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.shaah.vouch_status.common.utils.CommonUtils;
import com.shaah.vouch_status.quartiz_sched.QuartizFTPJob;
import com.shaah.vouch_status.quartiz_sched.daos.ConfigSchedDao;
import com.shaah.vouch_status.quartiz_sched.dtos.JobConfigDto;

public class ConfigSchedBao {

	private final Logger logger = Logger.getLogger(ConfigSchedBao.class); 
	public boolean ConfigSched(JobConfigDto schedDto,Scheduler sched){
		
//		System.out.println("OOOOOOOOOOOOOON the config");
		
		boolean schedStarted = false;
		
		if(schedDto != null && schedDto.getStatus()==CommonUtils.RUN_JOB_STATUS_OPTION){ // new and the user select save N run button so after saving going for starting
			
			int jobId = new ConfigSchedDao().saveSchedConfig(schedDto);
			
			if(jobId>0){
				schedDto.setJobId(jobId);
				schedStarted = startSched(schedDto,sched);
			}
			
		}else if(schedDto != null && schedDto.getStatus()==CommonUtils.NT_STARTED_JOB_OPTION){ // new and the user select save N run button so after saving going for starting
			logger.info(">>>>> saving Job ....");
			 
			int jobId = new ConfigSchedDao().saveSchedConfig(schedDto);
			if(jobId>0){
				schedStarted=true;
			}
			
			
		}else if(schedDto != null && schedDto.getStatus()==CommonUtils.UPDATE_JOB_STATUS_OPTION){
			JobControlBao jcb = new JobControlBao();
			logger.info("Will update >> job name is : "+schedDto.getJobName());
			jcb.stopJob(schedDto.getOldJobName(), sched);
			
			
			int jobId = jcb.updateJob(schedDto);
			
			if(jobId>0){
//				schedDto.setJobId(jobId);
				schedStarted = true;
			}
			
//			if(schedStarted){
//				startSched(schedDto, sched);
//			}
		}else if(schedDto != null && schedDto.getStatus()==CommonUtils.UPDATENRUN_JOB_STATUS_OPTION){
			logger.info("Update and run ... ");
			JobControlBao jcb = new JobControlBao();
			
			jcb.stopJob(schedDto.getOldJobName(), sched);
			schedDto.setStatus(CommonUtils.RUN_JOB_STATUS_OPTION);
			int jobId = jcb.updateJob(schedDto);
			
			if(jobId>0){
				schedStarted=true;
			}
			if(schedStarted){
				schedDto.setJobId(jobId);
				
				schedStarted = startSched(schedDto, sched);
			}
		}
		
		return schedStarted;
	}

	public boolean startSched(JobConfigDto schedDto,Scheduler sched) {
		// TODO Auto-generated method stub
		boolean res=false;
//		0 0 0/5 1/1 * ? *   0 0 12 ? * * *
				
//		System.out.println("OOOOOOOOOOOOOON strat job"+schedDto.getSrcFtpServerURL());
				JobDetail job = JobBuilder.newJob(QuartizFTPJob.class)
						.usingJobData("srcurl",schedDto.getSrcFtpServerURL())
						.usingJobData("src",schedDto.getSource())
						.usingJobData("srcusr",schedDto.getSrcUserName())
						.usingJobData("srcpass",schedDto.getSrcPass())
						.usingJobData("srcftpsEnabled",schedDto.isSrcFtpsEnbl())
						.usingJobData("disturl",schedDto.getDistFtpServerURL())
						.usingJobData("dist",schedDto.getDist())
						.usingJobData("distusr",schedDto.getDistUserName())
						.usingJobData("distpass",schedDto.getDistPass())
						.usingJobData("distftpsEnabled",schedDto.isDistFtpsEnbl())
						.usingJobData("jId",schedDto.getJobId())
						.usingJobData("postFtpOpt",schedDto.getPostFTPOpt())
						.usingJobData("srcBckupDir",schedDto.getSrcBckupDir())
						
				.withIdentity(schedDto.getJobName()).build();
				
//				System.out.println(">>>>>>>>>>>>>>>>> in quartiz dist is : "+schedDto.isDistFtpsEnbl()......);

// Trigger the job to run on the next round minute
Trigger trigger = TriggerBuilder
	.newTrigger()
	.withIdentity(schedDto.getJobName()+"_trig", "grp_trg")
	
	
	.withSchedule(
		CronScheduleBuilder.cronSchedule(schedDto.getRunTime()))
	.build();
res=true;
// schedule it
try {
	
	sched.scheduleJob(job, trigger);
//	System.out.println(">>>>>>>>>>>>>>>"+trigger.getNextFireTime());
	
} catch (SchedulerException e) {
	// TODO Auto-generated catch block
	logger.error("error in [startSched]",e);
	res=false;
}

				
				
				
		
		return res;
		
	}

	public void runValidSched(Scheduler sched) {
		// TODO Auto-generated method stub
		ArrayList<JobConfigDto> jobs= new ConfigSchedDao().getValidJobs();
		if(jobs!=null){
			for(JobConfigDto job : jobs){
				logger.info("will start job : "+job.getJobName()+" srcip : "+job.getSrcFtpServerURL()+" distip : "+job.getDistFtpServerURL());
				startSched(job, sched);
			}
		}
	}
}