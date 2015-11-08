package com.shaah.vouch_status.ftp.dtos;


public class DownloadStreamObj {

	private Object downloadStream ; // in case of ftp this stream will be outputstream and inputstream in case of sftp
	private int streamFlag=1; // =(1) -> if the downloadStream is input stream =(0)-> in case of output stream   
	private String fileName;
	private String absoluteFileName;
	
	
	
	
	
	public String getAbsoluteFileName() {
		return absoluteFileName;
	}
	public void setAbsoluteFileName(String absoluteFileName) {
		this.absoluteFileName = absoluteFileName;
	}
	public int getStreamFlag() {
		return streamFlag;
	}
	public void setStreamFlag(int streamFlag) {
		this.streamFlag = streamFlag;
	}
	public Object getDownloadStream() {
		return downloadStream;
	}
	public void setDownloadStream(Object downloadStream) {
		this.downloadStream = downloadStream;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
	
	
}
