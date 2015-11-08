package com.shaah.vouch_status.quartiz_sched.daos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.JobName;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.shaah.vouch_status.common.dtos.UserDto;
import com.shaah.vouch_status.common.utils.CommonUtils;
import com.shaah.vouch_status.common.utils.HibernateUtil;
import com.shaah.vouch_status.hibernatebeans.FtpJob;
import com.shaah.vouch_status.hibernatebeans.FtpUserTable;
import com.shaah.vouch_status.hibernatebeans.FtpUserType;
import com.shaah.vouch_status.quartiz_sched.dtos.DtoConverter;
import com.shaah.vouch_status.quartiz_sched.dtos.JobConfigDto;

public class JobControllerDao {

	private final Logger logger = Logger.getLogger(JobControllerDao.class);
	public JobConfigDto getJobDetals(String jobName, int status) {
		// TODO Auto-generated method stub
		JobConfigDto job = null;
		Transaction tx= null;
		try{
			
			Session session = HibernateUtil.getSessionFactory().openSession();		
			FtpJob hibJob =(FtpJob) session.createCriteria(FtpJob.class).add(Restrictions.eq("jobName", jobName)).uniqueResult();
			
			job = new DtoConverter().getDtoJob(hibJob);
			
			if(status>=-1){
				tx = session.beginTransaction();
				hibJob.setStatus(new BigDecimal(status));
				session.persist(hibJob);
				job.setStatus(status);
			
				
			}
		
		}catch(Exception e ){
			logger.error("Somthing geos wrong on [getJobDetals]",e);
		}finally{
			if(tx !=null&& tx.isActive()){
				tx.commit();
			}
			HibernateUtil.shutdown();
		}
		return job;
	}

	public ArrayList<JobConfigDto> loadUserJobs(BigDecimal userId) {
		// TODO Auto-generated method stub
		 ArrayList<JobConfigDto> jobs = null;
		try{
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			FtpUserTable user = new FtpUserTable();
			user.setUserId(userId);
			List hibJobs = session.createCriteria(FtpJob.class).add(Restrictions.eq("ftpUserTable",user )).list();
			if(hibJobs.size()>0){
				jobs = new ArrayList<JobConfigDto>();
				DtoConverter conv = new DtoConverter();
				for(Object job : hibJobs){
					jobs.add(conv.getDtoJob((FtpJob)job));
				}
			}
		}catch(Exception e ){
			e.printStackTrace();
		}finally{
			HibernateUtil.shutdown();
		}
		return jobs;
	}

	public int updateJob(JobConfigDto job) {
		// TODO Auto-generated method stub
		Transaction tx= null;
		int resJobId=-1;
		try{
			
			Session session = HibernateUtil.getSessionFactory().openSession();		
			FtpJob hibJob =(FtpJob) session.createCriteria(FtpJob.class).add(Restrictions.eq("jobName", job.getOldJobName())).uniqueResult();
			resJobId = hibJob.getJobId().intValue();
//			job = new DtoConverter().getDtoJob(hibJob);
			
			logger.info(">>>>>>>>> will update the job with name : "+job.getJobName()+" and src url : "+job.getSrcFtpServerURL());
				tx = session.beginTransaction();
				
				if(job.getStatus()==CommonUtils.UPDATENRUN_JOB_STATUS_OPTION){
					job.setStatus(CommonUtils.RUN_JOB_STATUS_OPTION);
				}
				
				
				hibJob.setStatus(new BigDecimal(job.getStatus()));
				hibJob.setCronExp(job.getRunTime());
				hibJob.setJobName(job.getJobName());
				hibJob.setPostFTPOpt(new BigDecimal(job.getPostFTPOpt()));
				
				hibJob.setDestServerPass(job.getDistPass());
				hibJob.setDestServerPath(job.getDist());
				hibJob.setDestServerUrl(job.getDistFtpServerURL());
				hibJob.setDestServerUser(job.getDistUserName());
				hibJob.setDestSecureCon(new BigDecimal(job.isDistFtpsEnbl()?1:0));
				
				hibJob.setSrcServerPass(job.getSrcPass());
				hibJob.setSrcServerPath(job.getSource());
				hibJob.setSrcServerUrl(job.getSrcFtpServerURL());
				hibJob.setSrcServerUser(job.getSrcUserName());
				hibJob.setSrcSecureCon(new BigDecimal(job.isSrcFtpsEnbl()?1:0));
				
				logger.debug(" >>>>>>>>>>>>>>>>> Secsrc : "+job.isSrcFtpsEnbl()+" SecDest : "+job.isDistFtpsEnbl());
				
				session.persist(hibJob);
//				session.flush();
			
				
			
		
		}catch(Exception e ){
			logger.error("Somthing geos wrong on [getJobDetals]",e);
			resJobId=-1;
			if(tx !=null&& tx.isActive()){
				
				tx.rollback();
				
			}
		}finally{
			if(tx !=null&& tx.isActive()){
				
				tx.commit();
//				resJobId=true;
			}
			HibernateUtil.shutdown();
		}
		return resJobId;
	}

	public boolean updateJobStatus(int jobId , int status){
		boolean  updated=false;
		Transaction tx= null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();	
		tx = session.beginTransaction();
		
		FtpJob hibJob =(FtpJob) session.load(FtpJob.class,BigDecimal.valueOf(jobId));
		hibJob.setStatus(BigDecimal.valueOf(status));
		session.persist(hibJob);
		tx.commit();
		updated=true;
		HibernateUtil.shutdown();
		return updated;
	}

}
