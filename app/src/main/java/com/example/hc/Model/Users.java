package com.example.hc.Model;

public class Users {
    private String Uid;
    private String Fullname;
    private String PhoneNumber;
    private String Email;
    private String Password;
    private String Img;
    private Integer userType;


    public Users() {
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public Users(String uid, String fullname, String phoneNumber, String email, String password, String img, int userType) {
        Uid = uid;
        Fullname = fullname;
        PhoneNumber = phoneNumber;
        Email = email;
        Password = password;
        Img = img;
        this.userType = userType;
    }


    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
