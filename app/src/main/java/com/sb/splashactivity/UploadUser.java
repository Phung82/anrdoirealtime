package com.sb.splashactivity;

public class UploadUser {
    public String FullName;
    public String UserName;
    public String Password;
    public String PhoneNum;
    public String Email;
    public String JobName;
    public String DoB;
    public String Sex;

    public UploadUser(){
        //Default
    }

    public UploadUser(String userName, String email) {
        UserName = userName;
        Email = email;
    }

    public UploadUser(String fullName, String phoneNum, String jobName, String doB, String sex) {
        FullName = fullName;
        PhoneNum = phoneNum;
        JobName = jobName;
        DoB = doB;
        Sex = sex;
    }
}
