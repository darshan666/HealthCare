package com.example.hc.Model;

public class AppointmentListModel {

    String PatientName,Gender,Dob,AptDate,AptReason;

    public AppointmentListModel() {
    }

    public AppointmentListModel(String patientName, String gender, String dob, String aptDate, String aptReason) {
        PatientName = patientName;
        Gender = gender;
        Dob = dob;
        AptDate = aptDate;
        AptReason = aptReason;
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
