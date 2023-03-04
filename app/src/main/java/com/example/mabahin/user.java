package com.example.mabahin;

public class user {


    private String email;
    private String pasword;
    private  String fullname;


    public user(String email, String pasword, String fullname, String contactNo) {
        this.email = email;
        this.pasword = pasword;
        this.fullname = fullname;
        this.contactNo = contactNo;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    private String  contactNo;





    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }



    public user(){}




}
