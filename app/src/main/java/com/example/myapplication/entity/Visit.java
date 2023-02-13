package com.example.myapplication.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Visit {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("person")
    private Person person;

    @SerializedName("doctor")
    private Doctor doctor;

    @Expose
    @SerializedName("cause")
    private String cause;






    public Visit() {
    }

    public Visit(int id, Person person, Doctor doctor, String cause) {
        this.id = id;
        this.person = person;
        this.doctor = doctor;
        this.cause = cause;
    }

    public Visit( Person person, Doctor doctor, String cause) {
        this.person = person;
        this.doctor = doctor;
        this.cause = cause;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", person=" + person +
                ", doctor=" + doctor +
                ", cause='" + cause + '\'' +
                '}';
    }
}
