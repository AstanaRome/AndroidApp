package com.example.myapplication.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Doctor {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("fullname")
    private String fullName;

    @Expose
    @SerializedName("qualification")
    private String qualification;






    public Doctor() {
    }

    public Doctor(int id, String fullName, String qualification) {
        this.id = id;
        this.fullName = fullName;
        this.qualification = qualification;
    }

    public Doctor(String fullname, String qualification) {
        this.fullName = fullname;
        this.qualification = qualification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullName;
    }

    public void setFullname(String fullName) {
        this.fullName = fullName;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", fullname='" + fullName+ '\'' +
                ", qualification='" + qualification + '\'' +
                '}';
    }
}
