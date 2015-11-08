package com.shaah.vouch_status.common.daos;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.shaah.vouch_status.common.dtos.DtoConverter;
import com.shaah.vouch_status.common.dtos.UserDto;
import com.shaah.vouch_status.common.utils.HibernateUtil;
import com.shaah.vouch_status.hibernatebeans.FtpUserTable;

public class CommonDao {

	public UserDto login(UserDto user){
		UserDto resUser = null;
		try{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		FtpUserTable ftpUser = (FtpUserTable)session.createCriteria(FtpUserTable.class).add(Restrictions.and(Restrictions.eq("username",user.getUserName()),Restrictions.eq("password",user.getPassword()))).uniqueResult();
		if(ftpUser!=null){
			DtoConverter conv = new DtoConverter();
			resUser = conv.getDtoUser(ftpUser);
		}
		
		}catch(Exception e){
			throw e;
		}finally{
			HibernateUtil.shutdown();
		}
		
		
		
		return resUser;
	}
}
