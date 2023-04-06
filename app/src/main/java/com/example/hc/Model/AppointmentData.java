package com.example.hc.Model;

public class AppointmentData {
    private String Fullname;
    private String DOB;
    private String Gender;
    private String Phone;
    private String Address;
    private String City;
    private String State;
    private String Pincode;
    private String AppointmentReason;
    private String AppointmentDateTimes;

    public AppointmentData(String fullname, String DOb, String gender, String phone, String address, String city, String state, String pincode, String appointmentReason, String appointmentDateTimes) {
        Fullname = fullname;
        DOB = DOb;
        Gender = gender;
        Phone = phone;
        Address = address;
        City = city;
        State = state;
        Pincode = pincode;
        AppointmentReason = appointmentReason;
        AppointmentDateTimes = appointmentDateTimes;
    }

    public AppointmentData() {
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public String getAppointmentReason() {
        return AppointmentReason;
    }

    public void setAppointmentReason(String appointmentReason) {
        AppointmentReason = appointmentReason;
    }

    public String getAppointmentDateTimes() {
        return AppointmentDateTimes;
    }

    public void setAppointmentDateTimes(String appointmentDateTimes) {
        AppointmentDateTimes = appointmentDateTimes;
    }
}
