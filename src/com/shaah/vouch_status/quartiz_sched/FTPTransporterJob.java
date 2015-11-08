package com.shaah.vouch_status.quartiz_sched;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPSClient;
import org.quartz.InterruptableJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

public class FTPTransporterJob implements InterruptableJob {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		
		String server = "localhost";
        int port = 21;
        String user = "smart";
        String pass = "smart";
 
        FTPSClient ftpClient = new FTPSClient();
        try {
 
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
 
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            
            downloadFileViaFTP(ftpClient);
            
            uploadFileViaFTP(ftpClient);
            
//            
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
		

	}
	}
        
        
        private static void uploadFileViaFTP(FTPSClient ftpClient) throws IOException {
    		// TODO Auto-generated method stub
    		// APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File("D:/ge.txt");

            String firstRemoteFile = "ge.txt";
            InputStream inputStream = new FileInputStream(firstLocalFile);

            System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
            }
    	}

    	private static void downloadFileViaFTP(FTPSClient ftpClient) throws IOException {
    		// TODO Auto-generated method stub
    		// APPROACH #1: using retrieveFile(String, OutputStream)
            String remoteFile1 = "/ge.txt";
            File downloadFile1 = new File("D:/Downloads/"+new Date().toString().replaceAll(":","")+"ftppppp.txt");
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
            boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
            outputStream1.close();

            if (success) {
                System.out.println("File #1 has been downloaded successfully.");
            }
    	}

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		// TODO Auto-generated method stub
		
	}

}
