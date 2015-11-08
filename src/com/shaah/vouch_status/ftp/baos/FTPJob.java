package com.shaah.vouch_status.ftp.baos;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.log4j.Logger;
import com.shaah.vouch_status.ftp.dtos.DownloadStreamObj;
import com.shaah.vouch_status.hibernatebeans.FtpJob;
import com.shaah.vouch_status.quartiz_sched.dtos.JobConfigDto;

public class FTPJob {
	
	private FTPClient theFtpClient;
	private final Logger logger = Logger.getLogger(FtpJob.class);
	
	
	
	public FTPJob(FTPClient theFtpClient){
		this.theFtpClient=theFtpClient;
	}
	
	public boolean executeFTPJob(JobConfigDto jobConfig){
		logger.info("start testing ftp client .. config ......");
		 int port = 22;
		 
		String srcServer = jobConfig.getSrcFtpServerURL();       
        String srcUser = jobConfig.getSrcUserName();
        String srcPass = jobConfig.getSrcPass();
 
        String distServer = jobConfig.getDistFtpServerURL();       
        String distUser = jobConfig.getDistUserName();
        String distPass = jobConfig.getDistPass();
        
        logger.info("Will start transaction with server ip  : "+srcServer);
        logger.info("Will start transaction with server usr : "+srcUser);
        logger.info("Will start transaction with server pas : "+srcPass);
        
        FTPClient srcFtpClient = new FTPClient();
        FTPClient distFtpClient = new FTPClient();
        try {
//        	logger.info("1");
        	srcFtpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true)); 
        	srcFtpClient.connect(srcServer, port);
        	srcFtpClient.login(srcUser, srcPass);
        	srcFtpClient.enterLocalPassiveMode();
        	srcFtpClient.setFileType(FTP.BINARY_FILE_TYPE);

//        	distFtpClient.setControlKeepAliveTimeout(3600); // set timeout to 60 minutes
        	distFtpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));
        	distFtpClient.connect(distServer, port);
        	distFtpClient.login(distUser, distPass);
        	distFtpClient.enterLocalPassiveMode();
        	distFtpClient.setFileType(FTP.BINARY_FILE_TYPE);
        	
        	
            logger.info("start downloading .... ");
            
            
           String downloadedFile = downloadFileViaFTP(srcFtpClient, jobConfig.getSource());
            if(downloadedFile!=null){
	            logger.info("downloaded successfully , -> start uploading .... ");
	            logger.info("Will start transaction with server ip  : "+distServer);
	            logger.info("Will start transaction with server usr : "+distUser);
	            logger.info("Will start transaction with server pas : "+distPass);
	            srcFtpClient.logout();
           	 	srcFtpClient.disconnect();
	            uploadFileViaFTP(distFtpClient, jobConfig.getDist() ,downloadedFile );
            }
//            
 
        } catch (Exception ex) {
//            logger.info("Error: " + ex.getMessage());
            logger.error("Error when start the FTP job with src server : "+ srcServer+" and dist server : "+distServer,ex);
        } finally {
            try {
            	
                if ( srcFtpClient.isConnected()) {
                	logger.info(">> will disconnect src");
                	 srcFtpClient.logout();
                	 srcFtpClient.disconnect();
                }
                
                if (distFtpClient.isConnected()) {
                	logger.info(">> will disconnect dist");
                	distFtpClient.logout();
                	distFtpClient.disconnect();
                }
                
                
            } catch (IOException ex) {
            	logger.error("Error During close  FTP connection for job with src server : "+ srcServer+" and dist server : "+distServer,ex);
            }
        }
        
        return false;
	}
	
	
	private void uploadFileViaFTP(FTPClient ftpClient, String uploadfilePath , String downloadedFile)  {
		// TODO Auto-generated method stub
		// APPROACH #1: uploads first file using an InputStream
		try{
        File localFile = new File(downloadedFile);

        
        InputStream inputStream = new FileInputStream(localFile);

        logger.info("Start uploading first file");
        boolean done = ftpClient.storeFile(uploadfilePath, inputStream);
        inputStream.close();
        if (done) {
            logger.info("The first file is uploaded successfully.");
        }else{
        	logger.info("something goes wrong while uploading !!");
        }
	}catch(Exception e)
    {
    	logger.error("Somthing wrong happens when trying to upload to  : "+uploadfilePath,e);
    }
	}
     
	
	
	public boolean uploadFileViaFTP(String uploadfilePath , DownloadStreamObj downloadStream)  {
		// TODO Auto-generated method stub
		// APPROACH #1: uploads first file using an InputStream
		try{
       
       

        logger.info("Start uploading file from the download stream");
               
        uploadfilePath = uploadfilePath.trim();
        if(!uploadfilePath.endsWith("/")&&!uploadfilePath.endsWith("//")){
        	uploadfilePath=uploadfilePath+"/";
        }
        
        boolean done =false;
        int streamFlag = downloadStream.getStreamFlag();
        if(streamFlag==0){
	        ByteArrayOutputStream outputStream = (ByteArrayOutputStream)downloadStream.getDownloadStream();
	        InputStream inDownloadStream = new ByteArrayInputStream(outputStream.toByteArray());
	        done = theFtpClient.storeFile(uploadfilePath+System.currentTimeMillis()+downloadStream.getFileName(),inDownloadStream );
	        inDownloadStream.close();
	        outputStream.close();
        }else if(streamFlag==1){
        	InputStream inDownloadStream =(InputStream)downloadStream.getDownloadStream();
        	done = theFtpClient.storeFile(uploadfilePath+System.currentTimeMillis()+downloadStream.getFileName(),inDownloadStream );
        	
	        inDownloadStream.close();
        }
        
        if (done) {
            logger.info("The file is uploaded successfully.");
            return true;
        }else{
        	logger.info("something goes wrong while uploading !!");
        	return false;
        }
	}catch(Exception e)
    {
    	logger.error("Somthing wrong happens when trying to upload to  : "+uploadfilePath,e);
    }
		return false;
	}
	
	
	
        
        private String downloadFileViaFTP(FTPClient ftpClient, String remoteFile)  {
    		// TODO Auto-generated method stub
    		// APPROACH #1: using retrieveFile(String, OutputStream)
        	String downloadedFilePath=null;
        	try{
        	logger.info("Download the following file : "+remoteFile);
        	downloadedFilePath = "D:/"+UUID.randomUUID()+"_"+System.currentTimeMillis();
//            String remoteFile1 = remoteFile;
        	logger.info("Download in the following path : "+downloadedFilePath);
            File downloadFile1 = new File(downloadedFilePath);
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
            boolean success = ftpClient.retrieveFile(remoteFile, outputStream1);
            outputStream1.close();

            if (success) {
                logger.info("File has been downloaded successfully.");
            }else{
            	logger.info("Download failed !!");
            	downloadedFilePath=null;
            }
        	}catch(Exception e)
            {
            	logger.error("Somthing wrong happens when trying to download : "+remoteFile,e);
            }
            return downloadedFilePath;
        }


		public InputStream getdownloadStream(String remoteFile) throws IOException {
			// TODO Auto-generated method stub
			InputStream downloadStream = null;
			if(theFtpClient!=null){
				downloadStream = theFtpClient.retrieveFileStream(remoteFile);
			}
			
			
			
			
			return downloadStream;
		}
		
		public DownloadStreamObj[] getdownloadStreams(String remoteFile) throws IOException {
			// TODO Auto-generated method stub
			 DownloadStreamObj[] downloadStreams=null;
			if(theFtpClient!=null){
			
				
				
				int lastNdxOfBS = remoteFile.lastIndexOf("/");
				
				if(lastNdxOfBS==-1){
					lastNdxOfBS = remoteFile.lastIndexOf("\\");
				}
				
				
				if(lastNdxOfBS==-1){
					logger.error("The file path Doesn't conains / or \\ characters !! ");
					return null;
				}
				
				
				
				
				String dir = remoteFile.substring(0,lastNdxOfBS)+"/";
				final String filePtrn = remoteFile.substring(lastNdxOfBS+1);
//				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>$$$$$$$$$$$$$$$ dir : "+dir+" file : "+filePtrn);
				
				
				
				FTPFileFilter filter = new FTPFileFilter() {
					 
	                @Override
	                public boolean accept(FTPFile ftpFile) {
	                	
//	                	logger.debug(">>>>>>>>>>> ON Filter " );
//	                    return (ftpFile.isFile() && ftpFile.getName().contains(filePtrn));
	                	return matches(filePtrn,ftpFile.getName());
	                }
	                
	                
	                boolean matches(String pattern, String text) {
	      		      // add sentinel so don't need to worry about *'s at end of pattern
	      		      text    += '\0';
	      		      pattern += '\0';

	      		      int N = pattern.length();

	      		      boolean[] states = new boolean[N+1];
	      		      boolean[] old = new boolean[N+1];
	      		      old[0] = true;

	      		      for (int i = 0; i < text.length(); i++) {
	      		         char c = text.charAt(i);
	      		         states = new boolean[N+1];       // initialized to false
	      		         for (int j = 0; j < N; j++) {
	      		            char p = pattern.charAt(j);

	      		            // hack to handle *'s that match 0 characters
	      		            if (old[j] && (p == '*')) old[j+1] = true;

	      		            if (old[j] && (p ==  c )) states[j+1] = true;
	      		            if (old[j] && (p == '.')) states[j+1] = true;
	      		            if (old[j] && (p == '*')) states[j]   = true;
	      		            if (old[j] && (p == '*')) states[j+1] = true;
	      		         }
	      		         old = states;
	      		      }
	      		      return states[N];
	      		   }
	                
	            };
				
	            FTPFile[] ftpFiles =  theFtpClient.listFiles(dir,filter);
	            
	            
	            
	            
	            int ftpFilesSize = ftpFiles.length;
	            downloadStreams = new  DownloadStreamObj[ftpFilesSize];
	            
	            logger.debug("The returned files number from filtering is : "+ftpFilesSize);
	           
	            DownloadStreamObj downloadStreamObj =null;
	            ByteArrayOutputStream tempOutStreamObj = null;
	            String fileName=null;
	            for( int i = 0; i<ftpFilesSize ; i++){
	           
	            	FTPFile file = ftpFiles[i];
	            	
	            	downloadStreamObj = new DownloadStreamObj();
	            	tempOutStreamObj = new ByteArrayOutputStream();
	            	fileName= file.getName();
	            	logger.debug(">> now will return the download stream for :  "+dir+fileName);
//	            	downloadStreamObj.setDownloadStream(theFtpClient.retrieveFileStream(dir+fileName));
	            	
	            	theFtpClient.retrieveFile(dir+fileName,tempOutStreamObj);
	            	downloadStreamObj.setDownloadStream(tempOutStreamObj);
	            	downloadStreamObj.setStreamFlag(0);
	            	
	            	logger.debug(">> returned the download stream for :  "+dir+fileName);
	            	downloadStreamObj.setFileName(fileName);
	            	
	            	downloadStreamObj.setAbsoluteFileName(dir+fileName);
	            	
	            	downloadStreams[i] = downloadStreamObj;
	            
	            }
			}
			
			
			
			
			return downloadStreams;
		}
        
        
		
		

}
