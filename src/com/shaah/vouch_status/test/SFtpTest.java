package com.shaah.vouch_status.test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 



import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class SFtpTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String server = "localhost";
        int port = 21;
        String user = "smart";
        String pass = "smart";
 
        FTPClient ftpClient = new FTPClient();
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

	private static void uploadFileViaFTP(FTPClient ftpClient) throws IOException {
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
        String remoteFile1 = "/ge.txt";
        File downloadFile1 = new File("D:/Downloads/ftppppp.txt");
        OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
        boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
        outputStream1.close();

        if (success) {
            System.out.println("File #1 has been downloaded successfully.");
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
