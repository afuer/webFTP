package com.shaah.vouch_status.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 





import java.io.PrintWriter;

import javax.swing.JOptionPane;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPSClient;



import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

public class FtpTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(System.currentTimeMillis());
//		System.out.println("start testing ftp client .. config ......");
//		String server = "10.238.226.75";
//        int port = 21;
//        String user = "sdpuser";
//        String pass = "sdpuser";
// 
//        FTPSClient ftpClient = new FTPSClient();
//        try {
//        	System.out.println("1");
//        	ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));
//            ftpClient.connect(server, port);
//            
//            ftpClient.login(user, pass);
//            ftpClient.enterLocalPassiveMode();
//             
////            ftpClient.enterLocalActiveMode();
//            System.out.println("2");
//            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//
//            System.out.println("start downloading .... ");
////            downloadFileViaFTP(ftpClient);
//            
//           
////            uploadFileViaFTP(ftpClient);
//            
////            
// 
//        } catch (IOException ex) {
////            System.out.println("Error: " + ex.getMessage());
//            ex.printStackTrace();
//        } finally {
//            try {
//            	
//                if (ftpClient.isConnected()) {
//                	System.out.println(">> will disconnect");
//                    ftpClient.logout();
//                    ftpClient.disconnect();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
		try{
		testDownloadStp();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private static void uploadFileViaFTP(FTPClient ftpClient) throws IOException {
		// TODO Auto-generated method stub
		// APPROACH #1: uploads first file using an InputStream
        File firstLocalFile = new File("D:/ge.txt");

        String firstRemoteFile = "/export/home/fuse/ftttttp.txt";;
        InputStream inputStream = new FileInputStream(firstLocalFile);

        System.out.println("Start uploading first file");
        boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
        inputStream.close();
        if (done) {
            System.out.println("The first file is uploaded successfully.");
        }

//        // APPROACH #2: uploads second file using an OutputStream
//        File secondLocalFile = new File("E:/Test/Report.doc");
//        String secondRemoteFile = "test/Report.doc";
//        inputStream = new FileInputStream(secondLocalFile);
//
//        System.out.println("Start uploading second file");
//        OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
//        byte[] bytesIn = new byte[4096];
//        int read = 0;
//
//        while ((read = inputStream.read(bytesIn)) != -1) {
//            outputStream.write(bytesIn, 0, read);
//        }
//        inputStream.close();
//        outputStream.close();
//
//        boolean completed = ftpClient.completePendingCommand();
//        if (completed) {
//            System.out.println("The second file is uploaded successfully.");
//        }
	}

	private static void downloadFileViaFTP(FTPClient ftpClient) throws IOException {
		// TODO Auto-generated method stub
		// APPROACH #1: using retrieveFile(String, OutputStream)
        String remoteFile1 = "/export/home/fuse/ge.txt";
        File downloadFile1 = new File("D:/ftppppp.txt");
        OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
        boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
        outputStream1.close();

        if (success) {
            System.out.println("File #1 has been downloaded successfully.");
        }else{
        	System.out.println("Download failed !!");
        }
        
//        // APPROACH #2: using InputStream retrieveFileStream(String)
//        String remoteFile2 = "/test/song.mp3";
//        File downloadFile2 = new File("D:/Downloads/song.mp3");
//        OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(downloadFile2));
//        InputStream inputStream = ftpClient.retrieveFileStream(remoteFile2);
//        byte[] bytesArray = new byte[4096];
//        int bytesRead = -1;
//        while ((bytesRead = inputStream.read(bytesArray)) != -1) {
//            outputStream2.write(bytesArray, 0, bytesRead);
//        }
//
//        success = ftpClient.completePendingCommand();
//        if (success) {
//            System.out.println("File #2 has been downloaded successfully.");
//        }
//        outputStream2.close();
//        inputStream.close();
	}
	
	
	
	
	
	
	
//	=========================================SFTP============================================
	 

	
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

	      UserInfo ui = new MyUserInfo(){
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

	      UserInfo ui = new MyUserInfo(){
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

		
		
		
	
	
	
	
	
//	=========================================ftps============================================
	
	
	private static void uploadFileViaFTPs(FTPSClient ftpClient) throws IOException {
		// TODO Auto-generated method stub
		// APPROACH #1: uploads first file using an InputStream
        File firstLocalFile = new File("D:/ge.txt");

        String firstRemoteFile = "/export/home/fuse/ftttttp.txt";;
        InputStream inputStream = new FileInputStream(firstLocalFile);

        System.out.println("Start uploading first file");
        boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
        inputStream.close();
        if (done) {
            System.out.println("The first file is uploaded successfully.");
        }

//        // APPROACH #2: uploads second file using an OutputStream
//        File secondLocalFile = new File("E:/Test/Report.doc");
//        String secondRemoteFile = "test/Report.doc";
//        inputStream = new FileInputStream(secondLocalFile);
//
//        System.out.println("Start uploading second file");
//        OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
//        byte[] bytesIn = new byte[4096];
//        int read = 0;
//
//        while ((read = inputStream.read(bytesIn)) != -1) {
//            outputStream.write(bytesIn, 0, read);
//        }
//        inputStream.close();
//        outputStream.close();
//
//        boolean completed = ftpClient.completePendingCommand();
//        if (completed) {
//            System.out.println("The second file is uploaded successfully.");
//        }
	}

	private static void downloadFileViaFTPs(FTPSClient ftpClient) throws IOException {
		// TODO Auto-generated method stub
		// APPROACH #1: using retrieveFile(String, OutputStream)
        String remoteFile1 = "/export/home/fuse/ge.txt";
        File downloadFile1 = new File("D:/ftppppp.txt");
        OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
        boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
        outputStream1.close();

        if (success) {
            System.out.println("File #1 has been downloaded successfully.");
        }else{
        	System.out.println("Download failed !!");
        }
        
//        // APPROACH #2: using InputStream retrieveFileStream(String)
//        String remoteFile2 = "/test/song.mp3";
//        File downloadFile2 = new File("D:/Downloads/song.mp3");
//        OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(downloadFile2));
//        InputStream inputStream = ftpClient.retrieveFileStream(remoteFile2);
//        byte[] bytesArray = new byte[4096];
//        int bytesRead = -1;
//        while ((bytesRead = inputStream.read(bytesArray)) != -1) {
//            outputStream2.write(bytesArray, 0, bytesRead);
//        }
//
//        success = ftpClient.completePendingCommand();
//        if (success) {
//            System.out.println("File #2 has been downloaded successfully.");
//        }
//        outputStream2.close();
//        inputStream.close();
	}

}
class MyUserInfo implements UserInfo, UIKeyboardInteractive{

	@Override
	public String getPassphrase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean promptPassphrase(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean promptPassword(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean promptYesNo(String arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void showMessage(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] promptKeyboardInteractive(String arg0, String arg1,
			String arg2, String[] arg3, boolean[] arg4) {
		// TODO Auto-generated method stub
		return null;
	}
		    
		  }