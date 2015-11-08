package com.shaah.vouch_status.quartiz_sched.baos;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;

import com.shaah.vouch_status.common.utils.CommonUtils;
import com.shaah.vouch_status.quartiz_sched.daos.JobControllerDao;
import com.shaah.vouch_status.quartiz_sched.dtos.JobConfigDto;

public class JobControlBao {

	private final Logger logger = Logger.getLogger(JobControlBao.class);
	
	public boolean stopJob(String jobName,Scheduler sched){
		boolean stopped=false;
		try {
			
			
			
			logger.info("stop ..... the schedular is "+sched);
			
			JobKey key = null;
			
			for (String groupName : sched.getJobGroupNames()) {
				for (JobKey jobKey : sched.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
					String tempJobName = jobKey.getName();
					String jobGroup = jobKey.getGroup();
//					List<Trigger> triggers = (List<Trigger>) sched.getTriggersOfJob(jobKey);
//					Date nextFireTime = triggers.get(0).getNextFireTime();
					logger.info("[jobName] : " + jobName + " [groupName] : "+ jobGroup);
					if(jobName.equals(tempJobName)&&jobGroup.equals("grp_trg")){
						key=jobKey;
						stopped=true;
						break;
					}
				}
				
	
			}
			
			
			
			
			
			
			
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName+"_trig","grp_trg");
			sched.unscheduleJob(triggerKey);
			
			sched.deleteJob(key);
			
			JobConfigDto schedDto= new JobControllerDao().getJobDetals(jobName,CommonUtils.STOP_JOB_STATUS_OPTION);
			if(stopped && schedDto!=null){
				return true;
			}else{
				stopped= false;
			}
			
	}catch(Exception e){
		stopped=false;
		logger.error("Error on [stopJob] ",e);
	}
		
		return stopped;
}

	
	
	public JobConfigDto getJob(String jobName) {
		// TODO Auto-generated method stub
		
		
		
		JobConfigDto schedDto= new JobControllerDao().getJobDetals(jobName,CommonUtils.GET_JOB_OPTION);
		
		
		
		return schedDto;
	}

	public boolean startJob(String jobName, Scheduler sched) {
		// TODO Auto-generated method stub
		boolean started=false;
		ConfigSchedBao cnfgBao = new ConfigSchedBao();
		
		JobConfigDto schedDto= new JobControllerDao().getJobDetals(jobName,CommonUtils.RUN_JOB_STATUS_OPTION);
		
		started = cnfgBao.startSched(schedDto, sched);
		
		return started;
	}

	public ArrayList<JobConfigDto> loadUserJobs(BigDecimal userId) {
		// TODO Auto-generated method stub
		return new JobControllerDao().loadUserJobs(userId);
	}

	public String getJobsGson(ArrayList<JobConfigDto> jobs) {
		// TODO Auto-generated method stub
		
		
		StringBuilder gsonStr = new StringBuilder();
		gsonStr.append("[{\"title\": \"My Jobs\", \"isFolder\": true, \"key\": \"treeRoot\",\"children\": [");
		if(jobs!=null){
		boolean firstLoopFlag= true;
		
		String jobName="";
		
		for(JobConfigDto job : jobs){
			String status = null;
					switch (job.getStatus()) {
					case CommonUtils.NT_STARTED_JOB_OPTION:
						status = "not started";
						break;
						
					case CommonUtils.STOP_JOB_STATUS_OPTION:
						status = "stopped";
						break;
						
					case CommonUtils.RUN_JOB_STATUS_OPTION:
						status = "running";
						break;
						
					case CommonUtils.UPDATE_JOB_STATUS_OPTION:
						status = "Updated";
						break;
						
					case CommonUtils.RUNWizError_JOB_STATUS_OPTION:
						status = "Run with error";
						break;

					default:
						status = "undefined";
						break;
					}
					jobName= job.getViewingJobName();
					
					if(firstLoopFlag){
						firstLoopFlag=false;
							
						
						gsonStr.append("{\"title\": \""+jobName+" [ "+status+" ]\" }");
					}else{
						gsonStr.append(",{\"title\": \""+jobName+" [ "+status+" ]\" }");
					}
					jobName="";
					
		}
		}
		gsonStr.append("]}]");
		
//		Gson gson = new Gson();
		
//		String gsonStr = gson.toJson(jobs);
		
		return gsonStr.toString();
	}
	public int updateJob(JobConfigDto schedDto) {
		// TODO Auto-generated method stub
		return new JobControllerDao().updateJob(schedDto);
	}



	public boolean updateStatus(int jobId, int status) {
		// TODO Auto-generated method stub
		
		 return new JobControllerDao().updateJobStatus(jobId, status);
		
	}
	
}
