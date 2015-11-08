package com.shaah.vouch_status.ftp.baos;

import com.shaah.vouch_status.ftp.daos.FTPExceptionAdminDao;
import com.shaah.vouch_status.ftp.dtos.FTPExceptionDto;

public class FTPExceptionAdminBao {
	public int logFtpException(FTPExceptionDto exDto){
		return new FTPExceptionAdminDao().logFtpException(exDto);
	}
}
