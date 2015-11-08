package com.shaah.vouch_status.ftp.dtos;

public class FTPExceptionDto {
	
	private int exceptionID;
	private String exceptionDesc;
	private int jobId;
	
	
	
	public String getExceptionDesc() {
		return exceptionDesc;
	}
	public void setExceptionDesc(String exceptionDesc) {
		this.exceptionDesc = exceptionDesc;
	}
	public int getExceptionID() {
		return exceptionID;
	}
	public void setExceptionID(int exceptionID) {
		this.exceptionID = exceptionID;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	
	 
	
	

}
