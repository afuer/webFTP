package com.shaah.vouch_status.ftp.daos;


import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shaah.vouch_status.common.utils.HibernateUtil;
import com.shaah.vouch_status.ftp.common.FTPModCmnUtil;
import com.shaah.vouch_status.ftp.dtos.FTPExceptionDto;
import com.shaah.vouch_status.hibernatebeans.FtpExceptions;


public class FTPExceptionAdminDao {
	private final static Logger logger = Logger.getLogger(FTPExceptionAdminDao.class);
	
	public int logFtpException(FTPExceptionDto exDto){
		int exId = 0;
		
		try{
			logger.debug(">>> will insert ftp exception ...");
		Transaction tx= null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();	
		tx = session.beginTransaction();
		
		FtpExceptions hibEx = new FTPModCmnUtil().getHibFTPEX(exDto);
		hibEx.setLogDate(new Date());
		session.save(hibEx);
		exId=hibEx.getExceptionId().intValue();
		tx.commit();
		
		}catch(Exception ex){
			logger.error("error when loging Exception",ex);
		}finally{
			HibernateUtil.shutdown();
		}
		
		
		
		
		
		
		return exId;
	}

}
