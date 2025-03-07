package com.example.hc.Model;

public class Doctor {

    String docId,fullname,specialist,Description,phone,location,city,state,pincode,fees,schedule,imageUrl;

    public Doctor() {
    }

    public Doctor(String docId,String fullname, String description, String specialist,String fees, String imageUrl) {
        this.fullname = fullname;
        this.docId = docId;
        Description = description;
        this.specialist = specialist;
        this.phone = phone;
        this.fees = fees;
        this.imageUrl = imageUrl;
    }

    public Doctor(String docId,String fullname, String specialist, String description, String phone, String location, String city, String state, String pincode, String fees, String schedule, String imageUrl) {
        this.docId = docId;
        this.fullname = fullname;
        this.specialist = specialist;
        Description = description;
        this.phone = phone;
        this.location = location;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.fees = fees;
        this.schedule = schedule;
        this.imageUrl = imageUrl;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }




}
