package com.shaah.vouch_status.ftp.common;

import java.math.BigDecimal;

import com.shaah.vouch_status.ftp.dtos.FTPExceptionDto;
import com.shaah.vouch_status.hibernatebeans.FtpExceptions;
import com.shaah.vouch_status.hibernatebeans.FtpJob;
import com.shaah.vouch_status.hibernatebeans.FtpJobUserMapping;

public class FTPModCmnUtil {

	public  FtpExceptions getHibFTPEX(FTPExceptionDto exDto) {
		// TODO Auto-generated method stub
		
		FtpExceptions hibEx = new FtpExceptions();
		hibEx.setExceptionDetails(exDto.getExceptionDesc());
		
		FtpJob job = new FtpJob();
		job.setJobId(BigDecimal.valueOf(exDto.getJobId()));
		hibEx.setFtpJob(job);
		hibEx.setExceptionId(BigDecimal.ONE);
		return hibEx;
	}

}
