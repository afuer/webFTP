package com.shaah.vouch_status.common.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class CommonUtils {

	public static final String USER_ATTR = "user";	
	public static final String UPLOAD_PATH="D:/khaled/temp/ftp";
	public static final String COMMON_PROPERTIES_FILE="D:/khaled/temp/ftp/voucher_app.properties";
	public static final String BASE_URL="shaah_FTP/";
	public static final int GET_JOB_OPTION=-2;
	public static final int NT_STARTED_JOB_OPTION=-1;
	public static final int STOP_JOB_STATUS_OPTION=0;
	public static final int RUN_JOB_STATUS_OPTION=1;	
	public static final int UPDATE_JOB_STATUS_OPTION=2;
	public static final int UPDATENRUN_JOB_STATUS_OPTION = 3;
	public static final int RUNWizError_JOB_STATUS_OPTION = 4;
	
	
	
	
	public static String getProperty(String propertyName){
		
		Properties prop = new Properties();
		InputStream input = null;
		String propertyVal=null;
	 
		try {
	 
			input = new FileInputStream(COMMON_PROPERTIES_FILE);
	 
			// load a properties file
			prop.load(input);
	 
			propertyVal = prop.getProperty(propertyName);
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return propertyVal;
	}
}
