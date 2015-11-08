package com.shaah.vouch_status.quartiz_sched;





import java.io.IOException;



import org.apache.log4j.Logger;
import org.quartz.InterruptableJob;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

import com.shaah.vouch_status.ftp.baos.FTPController;
import com.shaah.vouch_status.ftp.baos.FTPJob;
import com.shaah.vouch_status.quartiz_sched.dtos.JobConfigDto;


public class QuartizFTPJob implements InterruptableJob
{
	
	private volatile Thread  thisThread;
	private final Logger logger = Logger.getLogger(QuartizFTPJob.class);
	public void execute(JobExecutionContext context)
	throws JobExecutionException {
 
		
		thisThread = Thread.currentThread();
		
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();

	    String srcUrl = dataMap.getString("srcurl");
	    String src = dataMap.getString("src");
	    String srcUsr = dataMap.getString("srcusr");
	    String srcPass = dataMap.getString("srcpass");
	    String srcBckupDir = dataMap.getString("srcBckupDir");
	    boolean srcsftpEnbl = dataMap.getBoolean("srcftpsEnabled");
	    
	    String disturl = dataMap.getString("disturl");
	    String dist = dataMap.getString("dist");
	    String distusr = dataMap.getString("distusr");
	    String distPass = dataMap.getString("distpass");
	    boolean dstSftpEnbl = dataMap.getBoolean("distftpsEnabled");
	                                              
	    JobConfigDto configDto = new JobConfigDto();
	    configDto.setJobId(dataMap.getInt("jId"));
	    configDto.setPostFTPOpt(dataMap.getInt("postFtpOpt"));
	    
	    configDto.setSource(src);
	    configDto.setSrcFtpServerURL(srcUrl);
	    configDto.setSrcPass(srcPass);
	    configDto.setSrcUserName(srcUsr);
	    configDto.setSrcBckupDir(srcBckupDir);
	    configDto.setSrcFtpsEnbl(srcsftpEnbl);
	    
	    configDto.setDist(dist);
	    configDto.setDistFtpServerURL(disturl);
	    configDto.setDistPass(distPass);
	    configDto.setDistUserName(distusr);
	    configDto.setDistFtpsEnbl(dstSftpEnbl);
	   
	    
	    FTPController ftpController = new FTPController();
	    
	    try {
//	    	logger.debug(">>> the dest sftp status is : "+configDto.isDistFtpsEnbl());
			ftpController.executeFTPJob(configDto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error in the quartiz job",e);
		} catch (Exception e) {
			// TODO Auto-generated catch blocks
			logger.error("Error in the quartiz job",e);
		}
	    
		
		
		
		
		
 
	}
 
	

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>>>>>>>> inturrepted ....");
		
		if (thisThread != null) {
		      // this call causes the ClosedByInterruptException to happen
		      thisThread.interrupt(); 
		    }		
		
	}
	
	
	
}