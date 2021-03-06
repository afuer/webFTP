package com.shaah.vouch_status.hibernatebeans;
// Generated Mar 10, 2015 12:13:12 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * FtpUserType generated by hbm2java
 */
public class FtpUserType  implements java.io.Serializable {


     private BigDecimal userTypeId;
     private String userTypeName;
     private Set ftpUserTables = new HashSet(0);

    public FtpUserType() {
    }

	
    public FtpUserType(BigDecimal userTypeId, String userTypeName) {
        this.userTypeId = userTypeId;
        this.userTypeName = userTypeName;
    }
    public FtpUserType(BigDecimal userTypeId, String userTypeName, Set ftpUserTables) {
       this.userTypeId = userTypeId;
       this.userTypeName = userTypeName;
       this.ftpUserTables = ftpUserTables;
    }
   
    public BigDecimal getUserTypeId() {
        return this.userTypeId;
    }
    
    public void setUserTypeId(BigDecimal userTypeId) {
        this.userTypeId = userTypeId;
    }
    public String getUserTypeName() {
        return this.userTypeName;
    }
    
    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }
    public Set getFtpUserTables() {
        return this.ftpUserTables;
    }
    
    public void setFtpUserTables(Set ftpUserTables) {
        this.ftpUserTables = ftpUserTables;
    }




}


