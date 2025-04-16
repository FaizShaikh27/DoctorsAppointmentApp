package com.example.appointmentapp.Domain;

import java.io.Serializable;

public class DoctorsModel implements Serializable {
    private String Address;
    private String Biography;
    private int id;
    private String Name;
    private String Picture;
    private String Special;
    private int Experiense;
    private String Cost;
    private String Date;
    private String Time;
    private String location;
    private String Mobile;
    private String Patiens;
    private double Rating;
    private String Site;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBiography() {
        return Biography;
    }

    public void setBiography(String biography) {
        Biography = biography;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getSpecial() {
        return Special;
    }

    public void setSpecial(String special) {
        Special = special;
    }

    public int getExperiense() {
        return Experiense;
    }

    public void setExperiense(int experiense) {
        Experiense = experiense;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPatiens() {
        return Patiens;
    }

    public void setPatiens(String patiens) {
        Patiens = patiens;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double rating) {
        Rating = rating;
    }

    public String getSite() {
        return Site;
    }

    public void setSite(String site) {
        Site = site;
    }

    public DoctorsModel() {
    }
}
