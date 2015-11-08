package com.shaah.vouch_status.common.baos;

import org.dom4j.util.UserDataAttribute;

import com.shaah.vouch_status.common.daos.CommonDao;
import com.shaah.vouch_status.common.dtos.UserDto;

public class CommonBao {

	public UserDto login(UserDto user){
		
		CommonDao dao = new CommonDao();
		
		return dao.login(user);
		
	}
	
}
