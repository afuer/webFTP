package com.shaah.vouch_status.quartiz_sched.dtos;

import com.shaah.vouch_status.common.utils.CommonUtils;

public class JobConfigDto {

	
	private String satrtDate ;
	private String runTime;
	
	
	private int postFTPOpt; // this determine the status of action needed to be taken after uploading (0) means no action required (1) means delete after copying (2) mv to another location 
	
	
	
//	private String triggerName;
	private String jobName;
	private String oldJobName;
	private int jobId;
	private int userId;	
//	private String groupName;
	
	
	private String srcFtpServerURL ;
	private String srcUserName ;
	private String srcPass ;
	private String source ;
	private String srcBckupDir;
	private boolean srcFtpsEnbl;
	
	
	private String distFtpServerURL ;
	private String distUserName ;
	private String distPass ;
	private String dist ;	
	private boolean distFtpsEnbl;
	
	private int status; 
	
	
	
	
	
	
	public String getSrcBckupDir() {
		return srcBckupDir;
	}
	public void setSrcBckupDir(String srcBckupDir) {
		this.srcBckupDir = srcBckupDir;
	}
	public int getPostFTPOpt() {
		return postFTPOpt;
	}
	public void setPostFTPOpt(int postFTPOpt) {
		this.postFTPOpt = postFTPOpt;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public boolean isDistFtpsEnbl() {
		return distFtpsEnbl;
	}
	public void setDistFtpsEnbl(boolean distFtpsEnbl) {
		this.distFtpsEnbl = distFtpsEnbl;
	}
	public boolean isSrcFtpsEnbl() {
		return srcFtpsEnbl;
	}
	public void setSrcFtpsEnbl(boolean srcFtpsEnbl) {
		this.srcFtpsEnbl = srcFtpsEnbl;
	}
	public int getStatus() {
		
		
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRunTime() {
		return runTime;
	}
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	public String getSrcFtpServerURL() {
		return srcFtpServerURL;
	}
	public void setSrcFtpServerURL(String srcFtpServerURL) {
		this.srcFtpServerURL = srcFtpServerURL;
	}
	public String getSrcUserName() {
		return srcUserName;
	}
	public void setSrcUserName(String srcUserName) {
		this.srcUserName = srcUserName;
	}
	public String getSrcPass() {
		return srcPass;
	}
	public void setSrcPass(String srcPass) {
		this.srcPass = srcPass;
	}
	public String getDistFtpServerURL() {
		return distFtpServerURL;
	}
	public void setDistFtpServerURL(String distFtpServerURL) {
		this.distFtpServerURL = distFtpServerURL;
	}
	public String getDistUserName() {
		return distUserName;
	}
	public void setDistUserName(String distUserName) {
		this.distUserName = distUserName;
	}
	public String getDistPass() {
		return distPass;
	}
	public void setDistPass(String distPass) {
		this.distPass = distPass;
	}
	
//	public String getGroupName() {
//		return groupName;
//	}
//	public void setGroupName(String groupName) {
//		this.groupName = groupName;
//	}
//	public String getTriggerName() {
//		return triggerName;
//	}
//	public void setTriggerName(String triggerName) {
//		this.triggerName = triggerName;
//	}
	public String getJobName() {		
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getSatrtDate() {
		return satrtDate;
	}
	public void setSatrtDate(String satrtDate) {
		this.satrtDate = satrtDate;
	}
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDist() {
		return dist;
	}
	public void setDist(String dist) {
		this.dist = dist;
	}
	
	
	public String getViewingJobName() {
		// TODO Auto-generated method stub\
		String viewingJobName="";
		String [] jobNameArr=null;
		
		jobNameArr=jobName.split("_");
		for(int i=1;i<jobNameArr.length;i++){
			if(i==1){
				viewingJobName+=jobNameArr[i];
			}else{
				viewingJobName+="_"+jobNameArr[i];
			}
		}
		return viewingJobName;
	}
	public String getOldJobName() {
		return oldJobName;
	}
	public void setOldJobName(String oldJobName) {
		this.oldJobName = oldJobName;
	}
	
	
	
	
	
	
	
}
