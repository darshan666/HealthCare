package com.example.hc.Model;

public class AppointmentListModel {

    String Id,PatientName,Gender,Dob,AptDate,AptTime,AptReason,Address,City,State,Pincode,PhoneNumber;


    public AppointmentListModel(String patientName, String gender, String DOB, String aptDate, String aptTime, String aptReason, String address, String city, String state, String pincode, String phoneNumber, String aptId) {
        PatientName = patientName;
        Gender = gender;
        Dob = DOB;
        AptDate = aptDate;
        AptTime = aptTime;
        AptReason = aptReason;
        Address = address ;
        City = city;
        State = state;
        Pincode = pincode;
        PhoneNumber = phoneNumber;
        Id = aptId;
    }

    public String getAptTime() {
        return AptTime;
    }

    public void setAptTime(String aptTime) {
        AptTime = aptTime;
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

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }



    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getAptDate() {
        return AptDate;
    }

    public void setAptDate(String aptDate) {
        AptDate = aptDate;
    }

    public String getAptReason() {
        return AptReason;
    }

    public void setAptReason(String aptReason) {
        AptReason = aptReason;
    }
}
