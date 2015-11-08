package com.shaah.vouch_status.quartiz_sched.dtos;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.shaah.vouch_status.hibernatebeans.FtpJob;
import com.shaah.vouch_status.hibernatebeans.FtpUserTable;

public class DtoConverter {
	private final Logger logger = Logger.getLogger(DtoConverter.class);
	public FtpJob getDBJob(JobConfigDto schedDto) {
		// TODO Auto-generated method stub
		
		FtpJob dbJob = null;
		if(schedDto!=null){
			dbJob = new FtpJob();
			FtpUserTable user = new FtpUserTable();
			user.setUserId(BigDecimal.valueOf(schedDto.getUserId()));
			
			dbJob.setFtpUserTable(user);
			dbJob.setJobId(BigDecimal.ONE);
			dbJob.setCronExp(schedDto.getRunTime());			
			dbJob.setJobName(schedDto.getJobName());
			dbJob.setStatus(new BigDecimal(schedDto.getStatus()));
			dbJob.setPostFTPOpt(new BigDecimal(schedDto.getPostFTPOpt()));
			
			dbJob.setSrcServerUrl(schedDto.getSrcFtpServerURL());
			dbJob.setSrcServerUser(schedDto.getSrcUserName());
			dbJob.setSrcServerPass(schedDto.getSrcPass());
			dbJob.setSrcServerPath(schedDto.getSource());
			dbJob.setSrcBckupDir(schedDto.getSrcBckupDir());
			dbJob.setSrcSecureCon(schedDto.isSrcFtpsEnbl()?BigDecimal.ONE:BigDecimal.ZERO);
			
			dbJob.setDestServerUrl(schedDto.getDistFtpServerURL());
			dbJob.setDestServerUser(schedDto.getDistUserName());
			dbJob.setDestServerPass(schedDto.getDistPass());
			dbJob.setDestServerPath(schedDto.getDist());
			dbJob.setDestSecureCon(schedDto.isDistFtpsEnbl()?BigDecimal.ONE:BigDecimal.ZERO);
			
			
			
			
		}
		return dbJob;
	}

	public JobConfigDto getDtoJob(FtpJob job) {
		// TODO Auto-generated method stub
		JobConfigDto resJob = null;
		
		if(job!=null){
			
			resJob = new JobConfigDto();
			resJob.setJobId(job.getJobId().intValue());
			resJob.setDist(job.getDestServerPath());
			resJob.setDistFtpServerURL(job.getDestServerUrl());
			resJob.setDistPass(job.getDestServerPass());
			resJob.setDistUserName(job.getDestServerUser());
			resJob.setDistFtpsEnbl((job.getDestSecureCon().equals(BigDecimal.ONE)?true:false));
//			System.out.println("$$$$$$$$$$$$$$$$%%%%%%%%%%%%%#################dist "+resJob.isDistFtpsEnbl());
			resJob.setSource(job.getSrcServerPath());
			resJob.setSrcFtpServerURL(job.getSrcServerUrl());
			resJob.setSrcPass(job.getSrcServerPass());
			resJob.setSrcUserName(job.getSrcServerUser());
			resJob.setSrcFtpsEnbl((job.getSrcSecureCon().equals(BigDecimal.ONE)?true:false));
//			System.out.println("$$$$$$$$$$$$$$$$%%%%%%%%%%%%%#################src "+resJob.isSrcFtpsEnbl());
			resJob.setRunTime(job.getCronExp());
			resJob.setJobName(job.getJobName());
			resJob.setOldJobName(job.getJobName());
			resJob.setStatus(job.getStatus().intValue());
			resJob.setPostFTPOpt(job.getPostFTPOpt().intValue());
			resJob.setSrcBckupDir(job.getSrcBckupDir());
			
		}
		
		return resJob;
	}

}
