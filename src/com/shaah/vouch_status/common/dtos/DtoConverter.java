package com.shaah.vouch_status.common.dtos;

import com.shaah.vouch_status.hibernatebeans.FtpUserTable;

public class DtoConverter {

	public UserDto getDtoUser(FtpUserTable ftpUser){
		UserDto user = null;
		if(ftpUser!=null){
			user = new UserDto();
			user.setId(ftpUser.getUserId());
			user.setUserName(ftpUser.getUsername());
			user.setType(ftpUser.getFtpUserType().getUserTypeId());
		}
		return user;
	}
	
}
