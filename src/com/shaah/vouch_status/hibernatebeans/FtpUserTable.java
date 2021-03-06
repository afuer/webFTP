package com.shaah.vouch_status.hibernatebeans;
// Generated Mar 10, 2015 12:13:12 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * FtpUserTable generated by hbm2java
 */
public class FtpUserTable  implements java.io.Serializable {


     private BigDecimal userId;
     private FtpUserType ftpUserType;
     private String username;
     private String password;
     private Set ftpJobUserMappings = new HashSet(0);
     private Set ftpJobs = new HashSet(0);

    public FtpUserTable() {
    }

	
    public FtpUserTable(BigDecimal userId, FtpUserType ftpUserType, String username, String password) {
        this.userId = userId;
        this.ftpUserType = ftpUserType;
        this.username = username;
        this.password = password;
    }
    public FtpUserTable(BigDecimal userId, FtpUserType ftpUserType, String username, String password, Set ftpJobUserMappings, Set ftpJobs) {
       this.userId = userId;
       this.ftpUserType = ftpUserType;
       this.username = username;
       this.password = password;
       this.ftpJobUserMappings = ftpJobUserMappings;
       this.ftpJobs = ftpJobs;
    }
   
    public BigDecimal getUserId() {
        return this.userId;
    }
    
    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }
    public FtpUserType getFtpUserType() {
        return this.ftpUserType;
    }
    
    public void setFtpUserType(FtpUserType ftpUserType) {
        this.ftpUserType = ftpUserType;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public Set getFtpJobUserMappings() {
        return this.ftpJobUserMappings;
    }
    
    public void setFtpJobUserMappings(Set ftpJobUserMappings) {
        this.ftpJobUserMappings = ftpJobUserMappings;
    }
    public Set getFtpJobs() {
        return this.ftpJobs;
    }
    
    public void setFtpJobs(Set ftpJobs) {
        this.ftpJobs = ftpJobs;
    }




}


