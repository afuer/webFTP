package com.shaah.vouch_status.ftp.baos;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.UserInfo;

import com.shaah.vouch_status.common.utils.CommonUtils;
import com.shaah.vouch_status.ftp.common.MyJSChLogger;
import com.shaah.vouch_status.ftp.dtos.DownloadStreamObj;
import com.shaah.vouch_status.ftp.dtos.FTPExceptionDto;
import com.shaah.vouch_status.ftp.utils.MySFTPUserInfo;
import com.shaah.vouch_status.quartiz_sched.baos.JobControlBao;
import com.shaah.vouch_status.quartiz_sched.dtos.JobConfigDto;

public class FTPController {

	private final Logger logger = Logger.getLogger(FTPController.class);
	
	
//	public void executeFTPJob4OneFile(JobConfigDto configDto) throws IOException {
//		// TODO Auto-generated method stub
//		
//		FTPClient ftpClientDownloader= null;
//		FTPClient ftpClientUploader= null;
//		Channel downloadChannel=null;
//		Session downloadSession = null;
//		Channel uploadChannel=null;
//		Session uploadSession = null;
//		
//		boolean finishedsuccesfuly = false;
//		
//		try{
//		boolean isSrcSFTP=configDto.isSrcFtpsEnbl();
//		boolean isDstSFTP=configDto.isDistFtpsEnbl();
//		
//		InputStream downloadStream=null;
//		
//		int ftpPort = 21;
//		int sFtpPort = 22;
//		
//		String srcServer = configDto.getSrcFtpServerURL();       
//        String srcUser = configDto.getSrcUserName();
//        String srcPass = configDto.getSrcPass();
// 
//        String distServer = configDto.getDistFtpServerURL();       
//        String distUser = configDto.getDistUserName();
//        String distPass = configDto.getDistPass();
//		
//		if(!isSrcSFTP){
//			 logger.debug("Will start ftp transaction with server ip  : "+srcServer);
//		     logger.debug("Will start ftp transaction with server usr : "+srcUser);
//		     logger.debug("Will start ftp transaction with server pas : "+srcPass);
//			ftpClientDownloader= new FTPClient();
//			ftpClientDownloader.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true)); 
//			ftpClientDownloader.setControlKeepAliveTimeout(1900);
//			ftpClientDownloader.connect(srcServer, ftpPort);
//			ftpClientDownloader.login(srcUser, srcPass);
//			ftpClientDownloader.enterLocalPassiveMode();
//			ftpClientDownloader.setFileType(FTP.BINARY_FILE_TYPE);
//			downloadStream = downloadFileViaFTP(configDto.getSource(),ftpClientDownloader);
//			
//		}else{
//			
//			 logger.debug("Will start sftp transaction with server ip  : "+srcServer);
//		     logger.debug("Will start sftp transaction with server usr : "+srcUser);
//		     logger.debug("Will start sftp transaction with server pas : "+srcPass);
//		     JSch jsch=new JSch();
//
//		     
//
//		     downloadSession=jsch.getSession(srcUser, srcServer, sFtpPort);
//
//		      
//		      downloadSession.setPassword(srcPass);
//
//		      UserInfo ui = new MySFTPUserInfo(){
//		        public void showMessage(String message){
//		        }
//		        public boolean promptYesNo(String message){
//		          return true;
//		        }
//		
//		      };
//
//		      downloadSession.setUserInfo(ui);
//		      
//		      downloadSession.setConfig("PreferredAuthentications", 
//	                  "publickey,keyboard-interactive,password");
//
//		      downloadSession.connect(30000);   // making a connection with timeout.
//
//		      downloadChannel=downloadSession.openChannel("sftp");
//		      
//		     
//			   downloadChannel.connect(3*1000);
//			   downloadStream = downloadFileViaSFTP(downloadChannel,configDto.getSource());
//			   
//			   
//		}
//		
//		
//		
//		
//		if(downloadStream!=null){
//			logger.debug(">>>> will upload now ...");
//			if(!isDstSFTP){
//				
//				
//			ftpClientUploader= new FTPClient();
//			
//			
//			ftpClientUploader.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));
//			ftpClientUploader.setControlKeepAliveTimeout(1900);
//			ftpClientUploader.connect(distServer, ftpPort);
//			ftpClientUploader.login(distUser, distPass);
//			ftpClientUploader.enterLocalPassiveMode();
//			ftpClientUploader.setFileType(FTP.BINARY_FILE_TYPE);
//				
//				finishedsuccesfuly = uploadFileViaFTP(configDto.getDist(),configDto.getSource(),ftpClientUploader,downloadStream);
//				
//			}else{
//				JSch jsch=new JSch();
//				uploadSession=jsch.getSession(distUser, distServer, sFtpPort);
//
//			     
//				uploadSession.setPassword(distPass);
//
//			      UserInfo ui = new MySFTPUserInfo(){
//			        public void showMessage(String message){
//
//			        }
//			        public boolean promptYesNo(String message){
//
//			          return true;
//			        }
//
//			     
//
//			      };
//
//			      uploadSession.setUserInfo(ui);
//
//			      // It must not be recommended, but if you want to skip host-key check,
//			      // invoke following,
//			      // session.setConfig("StrictHostKeyChecking", "no");
//
//			      //session.connect();
//			      uploadSession.connect(30000);   // making a connection with timeout.
//
//			      uploadChannel=uploadSession.openChannel("sftp");
//			      
//			     
//			      uploadChannel.connect(3*1000);
//				 
//			      finishedsuccesfuly = uploadFileViaSFTP(uploadChannel,configDto.getDist(),downloadStream);
//			}
//		
//		}else{
//			logger.info(">>> the src server didn't return the download stream ..");
//		}
//		}catch(Exception e){
//			logger.error("Error in FTPCONTROLLER ... ", e);
//			FTPExceptionDto exDto = new FTPExceptionDto();
//			exDto.setJobId(configDto.getJobId());
//			exDto.setExceptionDesc(getStackTrace(e));
//			new FTPExceptionAdminBao().logFtpException(exDto);
//			finishedsuccesfuly=false;
//		}finally{
//			
//			if(ftpClientDownloader!=null&&ftpClientDownloader.isConnected()){
//				ftpClientDownloader.logout();
//				ftpClientDownloader.disconnect();
//			}
//			
//			if(ftpClientUploader!=null&&ftpClientUploader.isConnected()){
//				ftpClientUploader.logout();
//				ftpClientUploader.disconnect();
//			}
//			
//			if(downloadChannel!=null && downloadChannel.isConnected()){
//				downloadChannel.disconnect();
//			}
//			
//			if(downloadSession!=null && downloadSession.isConnected()){
//				downloadSession.disconnect();
//			}
//			
//			if(uploadChannel!=null && uploadChannel.isConnected()){
//				uploadChannel.disconnect();
//			}
//			
//			if(uploadSession!=null && uploadSession.isConnected()){
//				uploadSession.disconnect();
//			}
//			
//			
//			if(finishedsuccesfuly){
//				JobControlBao jcb = new JobControlBao();
//				jcb.updateStatus(configDto.getJobId(),CommonUtils.RUN_JOB_STATUS_OPTION);
//			}else{
//				JobControlBao jcb = new JobControlBao();
//				jcb.updateStatus(configDto.getJobId(),CommonUtils.RUNWizError_JOB_STATUS_OPTION);
//			}
//			
//		}
//		
//		
//	}
//
	private boolean uploadFileViaFTP(String distFile, FTPClient ftpClient,DownloadStreamObj inputStream) {
		// TODO Auto-generated method stub
		FTPJob ftpJob = new FTPJob(ftpClient);
		boolean res = ftpJob.uploadFileViaFTP(distFile, inputStream);
		
		return res;
	}

//	private InputStream downloadFileViaFTP(String remoteFile,FTPClient ftpClient ) throws IOException {
//		// TODO Auto-generated method stub
//		
//		
//		
//		FTPJob ftpJob = new FTPJob(ftpClient);
//		InputStream downloadInStream = ftpJob.getdownloadStream(remoteFile);
//		
//		return downloadInStream;
//	}
//	
//	
	
	private DownloadStreamObj[] getDownloadStreamsViaFTP(String remoteFile,FTPClient ftpClient ) throws IOException {
		// TODO Auto-generated method stub
		
		
		
		FTPJob ftpJob = new FTPJob(ftpClient);
		DownloadStreamObj[] downloadInStreams = ftpJob.getdownloadStreams(remoteFile);
		
		return downloadInStreams;
	}
	
	
	private boolean uploadFileViaSFTP(Channel channel , String remoteFile,DownloadStreamObj inputStream) throws JSchException, SftpException, IOException {
		// TODO Auto-generated method stub
		
//		String fileArr [] = remoteFile.split("/");
//		String fileName= fileArr[fileArr.length-1];
//		String filePathremo = remoteFile.substring(0,remoteFile.lastIndexOf("/"));
		
		
		
		SFTPJob ftpClient = new SFTPJob();
		return ftpClient.uploadFileSFTP(channel,remoteFile,inputStream);
	}

	private InputStream downloadFileViaSFTP(Channel channel , String remoteFile ) throws  JSchException, SftpException {
		// TODO Auto-generated method stub
		
		String fileArr [] = remoteFile.split("/");
		String fileName= fileArr[fileArr.length-1];
		String filePathremo = remoteFile.substring(0,remoteFile.lastIndexOf("/"));
		
		SFTPJob ftpClient = new SFTPJob();
		return ftpClient.downloadFileSFTP(channel, filePathremo, fileName);
		
		
		
		
	}	
	
	
	private DownloadStreamObj[] getDownloadStreamsViaSFTP(Channel channel , String remoteFile ) throws  JSchException, SftpException {
		// TODO Auto-generated method stub
		
		String fileArr [] = remoteFile.split("/");
		String fileName= fileArr[fileArr.length-1];
		String filePathremo = remoteFile.substring(0,remoteFile.lastIndexOf("/"));
		
		SFTPJob ftpClient = new SFTPJob();
		
		
		
		return ftpClient.getdownloadStreams(channel, filePathremo, fileName);
		
		
		
		
	}	
	
	
	
	
	public static String getStackTrace(final Throwable throwable) {
	     final StringWriter sw = new StringWriter();
	     final PrintWriter pw = new PrintWriter(sw, true);
	     throwable.printStackTrace(pw);
	     return sw.getBuffer().toString();
	}

	public void executeFTPJob(JobConfigDto configDto) throws IOException {
		// TODO Auto-generated method stub

		FTPClient ftpClientDownloader= null;
		FTPClient ftpClientUploader= null;
		ChannelSftp downloadChannel=null;
		Session downloadSession = null;
		Channel uploadChannel=null;		
		Session uploadSession = null;
//		Channel postActionChannel=null;
		JSch.setLogger(new MyJSChLogger());
		boolean finishedsuccesfuly = true;
		
		try{
		boolean isSrcSFTP=configDto.isSrcFtpsEnbl();
		boolean isDstSFTP=configDto.isDistFtpsEnbl();
		int postFTPOPT = configDto.getPostFTPOpt();
		
		DownloadStreamObj[] downloadStreams=null;
		
		int ftpPort = 21;
		int sFtpPort = 22;
		
		String srcServer = configDto.getSrcFtpServerURL();       
        String srcUser = configDto.getSrcUserName();
        String srcPass = configDto.getSrcPass();
 
        String distServer = configDto.getDistFtpServerURL();       
        String distUser = configDto.getDistUserName();
        String distPass = configDto.getDistPass();
		
		if(!isSrcSFTP){
			 logger.debug("Will start ftp transaction with server ip  : "+srcServer);
		     logger.debug("Will start ftp transaction with server usr : "+srcUser);
		     logger.debug("Will start ftp transaction with server pas : "+srcPass);
			ftpClientDownloader= new FTPClient();
			ftpClientDownloader.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true)); 
			ftpClientDownloader.setControlKeepAliveTimeout(1900);
			ftpClientDownloader.connect(srcServer, ftpPort);
			ftpClientDownloader.login(srcUser, srcPass);
			ftpClientDownloader.enterLocalPassiveMode();
			ftpClientDownloader.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClientDownloader.setKeepAlive(true);
			downloadStreams = getDownloadStreamsViaFTP(configDto.getSource(),ftpClientDownloader);
			
		}else{
			
			 logger.debug("Will start sftp transaction with server ip  : "+srcServer);
		     logger.debug("Will start sftp transaction with server usr : "+srcUser);
		     logger.debug("Will start sftp transaction with server pas : "+srcPass);
		     JSch jsch=new JSch();

		     

		     downloadSession=jsch.getSession(srcUser, srcServer, sFtpPort);

		      
		      downloadSession.setPassword(srcPass);

		      UserInfo ui = new MySFTPUserInfo(){
		        public void showMessage(String message){
		        }
		        public boolean promptYesNo(String message){
		          return true;
		        }
		
		      };

		      downloadSession.setUserInfo(ui);
		      
		      downloadSession.setConfig("PreferredAuthentications", 
	                  "publickey,keyboard-interactive,password");
		      

		      downloadSession.connect(30000);   // making a connection with timeout.

		      downloadChannel=(ChannelSftp)downloadSession.openChannel("sftp");
		      
		     
			   downloadChannel.connect(3*1000);
			   downloadStreams = getDownloadStreamsViaSFTP(downloadChannel,configDto.getSource());
			   
			   
			   
		}
		
		
		
		
		if(downloadStreams!=null && downloadStreams.length>0){
			logger.debug(">>>> will upload now ...");
			if(!isDstSFTP){
				
				logger.info("Upload by FTP ");
			ftpClientUploader= new FTPClient();
			
			
			ftpClientUploader.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));
			ftpClientUploader.setControlKeepAliveTimeout(1900);
			ftpClientUploader.connect(distServer, ftpPort);
			ftpClientUploader.login(distUser, distPass);
			ftpClientUploader.enterLocalPassiveMode();
			ftpClientUploader.setFileType(FTP.BINARY_FILE_TYPE);
				
			
			for(DownloadStreamObj downloadStream : downloadStreams){
				
				finishedsuccesfuly = uploadFileViaFTP(configDto.getDist() ,ftpClientUploader,downloadStream);
				
				
				if(finishedsuccesfuly){
					
					logger.info(downloadStream.getFileName()+" has been uploaded");
					
					
//					After FTP process action ========================================
					
					logger.info("postFTPOPT is :"+postFTPOPT);
					
					if(postFTPOPT==1){
						String file2bDeleted = downloadStream.getAbsoluteFileName();
						logger.info("will remove the file : "+file2bDeleted);
						
						if(isSrcSFTP){
							logger.info("SFTP removal");
							((ChannelSftp)downloadChannel).rm(file2bDeleted);
						}else{
							logger.info("FTP removal");
							ftpClientDownloader.deleteFile(downloadStream.getAbsoluteFileName());	
						}
						
						
						
					}else if(postFTPOPT==2){
						String file2bMove = downloadStream.getAbsoluteFileName();
						logger.info("will remove the file : "+file2bMove +" to backup folder : "+configDto.getSrcBckupDir());
						
						if(isSrcSFTP){
							logger.info("SFTP removal");
							
//							if(postActionChannel==null || postActionChannel.isClosed()){
//								postActionChannel = downloadSession.openChannel("shell");
//							}
//						      OutputStream ops = postActionChannel.getOutputStream();
//						      PrintStream ps = new PrintStream(ops, true);
//
//						      postActionChannel.connect(3*1000);
//						      InputStream input = postActionChannel.getInputStream();

//						      postActionChannel.getInputStream();
						      
						      //commands
//						      String removeCommandStr = "mv "+downloadStream.getAbsoluteFileName()+" "+configDto.getSrcBckupDir()+"/"+System.currentTimeMillis()+downloadStream.getFileName();
//						      ps.println(removeCommandStr);
//						      ps.println("clear");
//						      ps.flush();							      
//						      ps.close();
//
////						      printResult(input, downloadChannel);
//						      
//						      logger.info("SFTP removal completed with mv command : "+removeCommandStr);
//							  logger.info("SFTP removal completed with exit status : "+postActionChannel.getExitStatus());
//							  postActionChannel.disconnect();
							
							downloadChannel.rename(downloadStream.getAbsoluteFileName(), configDto.getSrcBckupDir()+"/"+System.currentTimeMillis()+downloadStream.getFileName());
							
						    
						}else{
							logger.info("FTP removal");
							ftpClientDownloader.rename(downloadStream.getAbsoluteFileName(),configDto.getSrcBckupDir()+"/"+System.currentTimeMillis()+downloadStream.getFileName());
							logger.info("FTP removal completed ");
						}
					}
					
					
					
//					End of FTP process============================================
					
				}else{
					logger.error("Failed to upload file : "+downloadStream.getFileName());
				}
				
				
				
			}
				
				
			}else{
				logger.info("Upload by SFTP ");
				finishedsuccesfuly=true;
				JSch jsch=new JSch();
				uploadSession=jsch.getSession(distUser, distServer, sFtpPort);

			     
				uploadSession.setPassword(distPass);

			      UserInfo ui = new MySFTPUserInfo(){
			        public void showMessage(String message){

			        }
			        public boolean promptYesNo(String message){

			          return true;
			        }

			     

			      };

			      uploadSession.setUserInfo(ui);

			      // It must not be recommended, but if you want to skip host-key check,
			      // invoke following,
			      // session.setConfig("StrictHostKeyChecking", "no");

			      //session.connect();
			      
			      uploadSession.setConfig("PreferredAuthentications", 
		                  "publickey,keyboard-interactive,password");
			      
			      uploadSession.connect(30000);   // making a connection with timeout.

			      uploadChannel=uploadSession.openChannel("sftp");
			      
			     
			      uploadChannel.connect(3*1000);
				 
			      
			  	for(DownloadStreamObj downloadStream : downloadStreams){
			  		
			  		finishedsuccesfuly = uploadFileViaSFTP(uploadChannel,configDto.getDist(),downloadStream);
			  		
			  		
			  		if(finishedsuccesfuly){
						
						logger.info(downloadStream.getFileName()+" has been uploaded");
						
//						After FTP process action ========================================
						
						logger.info("postFTPOPT is :"+postFTPOPT);
						
						if(postFTPOPT==1){
							String file2bDeleted = downloadStream.getAbsoluteFileName();
							logger.info("will remove the file : "+file2bDeleted);
							
							if(isSrcSFTP){
								logger.info("SFTP removal");
								((ChannelSftp)downloadChannel).rm(file2bDeleted);
							}else{
								logger.info("FTP removal");
								ftpClientDownloader.deleteFile(downloadStream.getAbsoluteFileName());	
							}
							
							
							
						}else if(postFTPOPT==2){
							String file2bMove = downloadStream.getAbsoluteFileName();
							logger.info("will remove the file : "+file2bMove +" to backup folder : "+configDto.getSrcBckupDir());
							
							if(isSrcSFTP){
								logger.info("SFTP removal");
								
//								if(postActionChannel==null || postActionChannel.isClosed()){
//									postActionChannel = downloadSession.openChannel("shell");
//								}
//							      OutputStream ops = postActionChannel.getOutputStream();
//							      PrintStream ps = new PrintStream(ops, true);
	//
//							      postActionChannel.connect(3*1000);
//							      InputStream input = postActionChannel.getInputStream();

//							      postActionChannel.getInputStream();
							      
							      //commands
//							      String removeCommandStr = "mv "+downloadStream.getAbsoluteFileName()+" "+configDto.getSrcBckupDir()+"/"+System.currentTimeMillis()+downloadStream.getFileName();
//							      ps.println(removeCommandStr);
//							      ps.println("clear");
//							      ps.flush();							      
//							      ps.close();
	//
////							      printResult(input, downloadChannel);
//							      
//							      logger.info("SFTP removal completed with mv command : "+removeCommandStr);
//								  logger.info("SFTP removal completed with exit status : "+postActionChannel.getExitStatus());
//								  postActionChannel.disconnect();
								
								downloadChannel.rename(downloadStream.getAbsoluteFileName(), configDto.getSrcBckupDir()+"/"+System.currentTimeMillis()+downloadStream.getFileName());
								
							    
							}else{
								logger.info("FTP removal");
								ftpClientDownloader.rename(downloadStream.getAbsoluteFileName(),configDto.getSrcBckupDir()+"/"+System.currentTimeMillis()+downloadStream.getFileName());
								logger.info("FTP removal completed ");
							}
						}
						
						
						
//						End of FTP process============================================
						
					}else{
						logger.error("Failed to upload file : "+downloadStream.getFileName());
					}
			  	}////////////////////////////////////////
			  	Thread.sleep(300);     
			}
		
		}else{
			logger.info(">>> the src server didn't return the download stream ..");
		}
		}catch(Exception e){
			logger.error("Error in FTPCONTROLLER ... ", e);
			FTPExceptionDto exDto = new FTPExceptionDto();
			exDto.setJobId(configDto.getJobId());
			exDto.setExceptionDesc(getStackTrace(e));
			new FTPExceptionAdminBao().logFtpException(exDto);
			finishedsuccesfuly=false;
		}finally{
			
			if(ftpClientDownloader!=null&&ftpClientDownloader.isConnected()){
				ftpClientDownloader.logout();
				ftpClientDownloader.disconnect();
			}
			
			if(ftpClientUploader!=null&&ftpClientUploader.isConnected()){
				ftpClientUploader.logout();
				ftpClientUploader.disconnect();
			}
			
			if(downloadChannel!=null && downloadChannel.isConnected()){
				downloadChannel.disconnect();
			}
			
			if(downloadSession!=null && downloadSession.isConnected()){
				downloadSession.disconnect();
			}
			
			if(uploadChannel!=null && uploadChannel.isConnected()){
				uploadChannel.disconnect();
			}
			
			if(uploadSession!=null && uploadSession.isConnected()){
				uploadSession.disconnect();
			}
			
			
			if(finishedsuccesfuly){
				JobControlBao jcb = new JobControlBao();
				jcb.updateStatus(configDto.getJobId(),CommonUtils.RUN_JOB_STATUS_OPTION);
			}else{
				JobControlBao jcb = new JobControlBao();
				jcb.updateStatus(configDto.getJobId(),CommonUtils.RUNWizError_JOB_STATUS_OPTION);
			}
			
		}
	}

	
	
	
	/**
	    * @param input
	    * @param channel
	    */
//	   private  void printResult(InputStream input,
//	                                   Channel channel) throws Exception
//	   {
//	      int SIZE = 1024;
//	      byte[] tmp = new byte[SIZE];
//	      while (true)
//	      {
//	         while (input.available() > 0)
//	         {
//	            int i = input.read(tmp, 0, SIZE);
//	            if(i < 0)
//	               break;
//	             System.out.print(new String(tmp, 0, i));
//	         }
//	         if(channel.isClosed())
//	         {
//	            System.out.println("exit-status: " + channel.getExitStatus());
//	           
//	         }
//	         try
//	         {
//	            Thread.sleep(300);
//	         }
//	         catch (Exception e)
//	         {
//	        	 logger.info("Error when printing the SCH client error",e);
//	         }
//	      }
//	   }
	
	
	
	
}
