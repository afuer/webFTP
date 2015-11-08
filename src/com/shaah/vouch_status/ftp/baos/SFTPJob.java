package com.shaah.vouch_status.ftp.baos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

import com.shaah.vouch_status.ftp.dtos.DownloadStreamObj;
import com.shaah.vouch_status.ftp.utils.MySFTPUserInfo;
import com.shaah.vouch_status.quartiz_sched.dtos.JobConfigDto;

public class SFTPJob {
	private final Logger logger = Logger.getLogger(SFTPJob.class);
	public boolean executeFTPJob(JobConfigDto jobConfig){
		
		logger.info("start testing ftp client .. config ......");
		 int port = 21;
		 
		String srcServer = jobConfig.getSrcFtpServerURL();       
       String srcUser = jobConfig.getSrcUserName();
       String srcPass = jobConfig.getSrcPass();

       String distServer = jobConfig.getDistFtpServerURL();       
       String distUser = jobConfig.getDistUserName();
       String distPass = jobConfig.getDistPass();
       
       logger.info("Will start transaction with server ip  : "+srcServer);
       logger.info("Will start transaction with server usr : "+srcUser);
       logger.info("Will start transaction with server pas : "+srcPass);
       
       
       
       
       logger.info("Will start transaction with server ip  : "+distServer);
       logger.info("Will start transaction with server usr : "+distUser);
       logger.info("Will start transaction with server pas : "+distPass);
       
       
       return false;
       
	}
	
	
	private static void testDownloadStp() throws JSchException, SftpException{
		Channel channel=null;
		Session session=null;
		OutputStream os=null;
		BufferedOutputStream bos=null;
		BufferedInputStream bis=null;
	    try{
	      JSch jsch=new JSch();

	      //jsch.setKnownHosts("/home/foo/.ssh/known_hosts");

	      
	      String user="sdpuser";
	      String host="10.238.226.75";

	      session=jsch.getSession(user, host, 22);

	      String passwd = "sdpuser";
	      session.setPassword(passwd);

	      UserInfo ui = new MySFTPUserInfo(){
	        public void showMessage(String message){
//	          JOptionPane.showMessageDialog(null, message);
	        }
	        public boolean promptYesNo(String message){
//	          Object[] options={ "yes", "no" };
//	          int foo=JOptionPane.showOptionDialog(null, 
//	                                               message,
//	                                               "Warning", 
//	                                               JOptionPane.DEFAULT_OPTION, 
//	                                               JOptionPane.WARNING_MESSAGE,
//	                                               null, options, options[0]);
	          return true;
	        }
	
	      };

	      session.setUserInfo(ui);

	      // It must not be recommended, but if you want to skip host-key check,
	      // invoke following,
	      // session.setConfig("StrictHostKeyChecking", "no");

	      //session.connect();
	      session.connect(30000);   // making a connection with timeout.

	      channel=session.openChannel("sftp");
	      
	     
		    channel.connect(3*1000);
		   
		    ChannelSftp sftp=(ChannelSftp) channel;
		    
		    
		    sftp.cd("/var/opt/fds/logs");
		    byte[] buffer = new byte[1024];
		    bis = new BufferedInputStream(sftp.get("TTMonitor.log"));
		    File newFile = new File("D:/Doneeeeeeeee.java");
		    os = new FileOutputStream(newFile);
		    bos = new BufferedOutputStream(os);
		    int readCount;
		    System.out.println("Getting: the file" );
		    while( (readCount = bis.read(buffer)) > 0) {
		    	System.out.println("Writing " );
		    	bos.write(buffer, 0, readCount);
		    }
		    
		    System.out.println("Done :) " );
//		    System.out.println(sftp.getHome());
//		    for (Object o : sftp.ls("")) {
//		        System.out.println(((ChannelSftp.LsEntry)o).getFilename());
//		    }
	    }
	    catch(Exception e){
	      System.out.println(e);
	    }finally{
//	      Session session=jsch.getSession("usersdp","10.238.226.75",22);

	      
	    	try {
	    		if(os!=null){
	    			os.close();
	    		}
				 if(bis!=null){
					 bis.close();
				 }
				  if(bos!=null){
					  bos.close();
				  }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		    
		    channel.disconnect();
		    session.disconnect();
	    }
		}

	
	public  InputStream downloadFileSFTP(Channel channel,String downloadPath,String fileName) throws JSchException, SftpException{
		
		logger.debug(">>> will start downloading the file located in : "+downloadPath+" its name : "+fileName);
		ChannelSftp sftp=(ChannelSftp) channel;
	    
	    
	    sftp.cd(downloadPath);
	    
	    return  sftp.get(fileName);
		
		
		
	}
	
	public  DownloadStreamObj[] getdownloadStreams(Channel channel,String downloadPath,String fileName) throws JSchException, SftpException{
		
		DownloadStreamObj[] downloadStreams = null;
		logger.debug(">>> will start downloading the file located in : "+downloadPath+" its name : "+fileName);
		ChannelSftp sftp=(ChannelSftp) channel;
	    
		 sftp.cd(downloadPath);
		
		Vector<ChannelSftp.LsEntry> list = sftp.ls(fileName);
		
		if(list!=null && list.size()>0){
			downloadStreams = new DownloadStreamObj[list.size()];
		}
		
		
		DownloadStreamObj downloadStreamObj = null;
		for(int i = 0 ; i< list.size() ; i++) {
			
			ChannelSftp.LsEntry entry = list.get(i);
			String theFileName = entry.getFilename().trim();
			downloadStreamObj =  new DownloadStreamObj();
			downloadStreamObj.setDownloadStream(sftp.get(entry.getFilename()));
			downloadStreamObj.setFileName(theFileName);
			downloadStreamObj.setAbsoluteFileName(downloadPath+"/"+theFileName);
			downloadStreams[i] = downloadStreamObj;
		}
	   
	    
	    
	    
	    return downloadStreams;
		
		
		
	}
	
	
public  boolean uploadFileSFTP(Channel channel,String uploadPath,DownloadStreamObj uploadStream) throws JSchException, SftpException, IOException{
		
	boolean res = true;
	 ChannelSftp sftp=(ChannelSftp) channel;
	    
	    
	    sftp.cd(uploadPath);
	   logger.debug("SFTP upload on : "+uploadPath+" and the file name is : "+uploadStream.getFileName());
	   
	   int streamFlag = uploadStream.getStreamFlag();
	   if(streamFlag==1){
		    InputStream downloadStream = (InputStream)uploadStream.getDownloadStream();
		    
		    sftp.put(downloadStream,System.currentTimeMillis()+uploadStream.getFileName());
		    downloadStream.close();
	   }else if(streamFlag==0){
		   ByteArrayOutputStream outputStream = (ByteArrayOutputStream)uploadStream.getDownloadStream();
	        InputStream downloadStream = new ByteArrayInputStream(outputStream.toByteArray());
	        
	        sftp.put(downloadStream,System.currentTimeMillis()+uploadStream.getFileName());
		    downloadStream.close();
		    outputStream.close();
	   }
	    return  res;
		
		
		
	}
		
	private static void testUploadStp() throws JSchException, SftpException{
		Channel channel=null;
		Session session=null;
		
	    try{
	      JSch jsch=new JSch();

	      //jsch.setKnownHosts("/home/foo/.ssh/known_hosts");

	      
	      String user="sdpuser";
	      String host="10.238.226.75";

	      session=jsch.getSession(user, host, 22);

	      String passwd = "sdpuser";
	      session.setPassword(passwd);

	      UserInfo ui = new MySFTPUserInfo(){
	        public void showMessage(String message){
//	          JOptionPane.showMessageDialog(null, message);
	        }
	        public boolean promptYesNo(String message){
//	          Object[] options={ "yes", "no" };
//	          int foo=JOptionPane.showOptionDialog(null, 
//	                                               message,
//	                                               "Warning", 
//	                                               JOptionPane.DEFAULT_OPTION, 
//	                                               JOptionPane.WARNING_MESSAGE,
//	                                               null, options, options[0]);
	          return true;
	        }

	        // If password is not given before the invocation of Session#connect(),
	        // implement also following methods,
	        //   * UserInfo#getPassword(),
	        //   * UserInfo#promptPassword(String message) and
	        //   * UIKeyboardInteractive#promptKeyboardInteractive()

	      };

	      session.setUserInfo(ui);

	      // It must not be recommended, but if you want to skip host-key check,
	      // invoke following,
	      // session.setConfig("StrictHostKeyChecking", "no");

	      //session.connect();
	      session.connect(30000);   // making a connection with timeout.

	      channel=session.openChannel("sftp");
	      
	     
		    channel.connect(3*1000);
		   
		    ChannelSftp sftp=(ChannelSftp) channel;
		    
		    
		    sftp.cd("/var/opt/fds/logs");
		    File f = new File("D:/Doneeeeeeeee.java");
		   FileInputStream uploadStream = new FileInputStream(f);
		    sftp.put(uploadStream, f.getName());

		    
		    System.out.println("Done :) " );
//		    System.out.println(sftp.getHome());
//		    for (Object o : sftp.ls("")) {
//		        System.out.println(((ChannelSftp.LsEntry)o).getFilename());
//		    }
	    }
	    catch(Exception e){
	      System.out.println(e);
	    }finally{
//	      Session session=jsch.getSession("usersdp","10.238.226.75",22);
	    
		    channel.disconnect();
		    session.disconnect();
	    }
		}
	
}
//class MyUserInfo implements UserInfo, UIKeyboardInteractive{
//
//	@Override
//	public String getPassphrase() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean promptPassphrase(String arg0) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean promptPassword(String arg0) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean promptYesNo(String arg0) {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public void showMessage(String arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public String[] promptKeyboardInteractive(String arg0, String arg1,
//			String arg2, String[] arg3, boolean[] arg4) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//		    
//		  }