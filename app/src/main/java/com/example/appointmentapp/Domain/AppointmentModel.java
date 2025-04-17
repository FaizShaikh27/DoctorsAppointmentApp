package com.example.appointmentapp.Domain;

public class AppointmentModel {
    String userName;
    String doctorName;
    String date;
    String time;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public AppointmentModel() {
    }

    public AppointmentModel(String userName, String doctorName, String date, String time) {
        this.userName = userName;
        this.doctorName = doctorName;
        this.date = date;
        this.time = time;
    }
}
