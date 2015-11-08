package com.shaah.vouch_status.quartiz_sched.daos;



import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;






import org.hibernate.criterion.Restrictions;

import com.shaah.vouch_status.common.utils.CommonUtils;
import com.shaah.vouch_status.common.utils.HibernateUtil;
import com.shaah.vouch_status.hibernatebeans.FtpJob;
import com.shaah.vouch_status.quartiz_sched.dtos.DtoConverter;
import com.shaah.vouch_status.quartiz_sched.dtos.JobConfigDto;

public class ConfigSchedDao {

	private final Logger logger = Logger.getLogger(ConfigSchedDao.class); 
	public int saveSchedConfig(JobConfigDto schedDto) {
		// TODO Auto-generated method stub
		Transaction tr= null;
		int insertedJobId=-1;
		Session session=null;
		FtpJob job=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
		
		tr = session.beginTransaction();
		DtoConverter conv =  new DtoConverter();
		job = conv.getDBJob(schedDto);
	
		
		session.save(job);
		
			
		}catch(Exception e){
			tr.rollback();
			throw e;
		}finally  {
			if(tr!=null && tr.isActive()){
				tr.commit();
				if(job!=null){
//					session.refresh(job);
					insertedJobId=((FtpJob)session.createCriteria(FtpJob.class).add(Restrictions.eq("jobName",job.getJobName())).uniqueResult()).getJobId().intValue();
					
					logger.debug(">>>>>>>> the job saved with this id : "+insertedJobId);
					
				}
			}
			HibernateUtil.shutdown();
		}
		return insertedJobId;
	}

	public ArrayList<JobConfigDto> getValidJobs() {
		// TODO Auto-generated method stub
		ArrayList<JobConfigDto> jobs=null;
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			ArrayList<FtpJob> hibJobs = (ArrayList<FtpJob>) session.createCriteria(FtpJob.class).add(Restrictions.or(Restrictions.eq("status",BigDecimal.valueOf(CommonUtils.RUN_JOB_STATUS_OPTION)),Restrictions.eq("status",BigDecimal.valueOf(CommonUtils.RUNWizError_JOB_STATUS_OPTION)))).list();
			if(hibJobs!=null && hibJobs.size()>0){
				jobs= new ArrayList<JobConfigDto>();
				DtoConverter conv =  new DtoConverter();
				for(FtpJob job : hibJobs){
					
					
					jobs.add(conv.getDtoJob(job));
				}
			}
		
			
		}catch(Exception e){
			
			throw e;
		}finally  {
			
			HibernateUtil.shutdown();
		}
		return jobs;
	}

}
