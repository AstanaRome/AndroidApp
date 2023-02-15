package com.example.myapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Visit implements Parcelable {

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

    @Expose
    @SerializedName("visit_date")
    private String visitDate;

    public Visit() {
    }

    public Visit(Person person, Doctor doctor, String cause, String visitDate) {
        this.person = person;
        this.doctor = doctor;
        this.cause = cause;
        this.visitDate = visitDate;
    }

    public Visit(int id, Person person, Doctor doctor, String cause, String visitDate) {
        this.id = id;
        this.person = person;
        this.doctor = doctor;
        this.cause = cause;
        this.visitDate = visitDate;
    }

    protected Visit(Parcel in) {
        id = in.readInt();
        person = in.readParcelable(Person.class.getClassLoader());
        doctor = in.readParcelable(Doctor.class.getClassLoader());
        cause = in.readString();
        visitDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeParcelable(person, flags);
        dest.writeParcelable(doctor, flags);
        dest.writeString(cause);
        dest.writeString(visitDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Visit> CREATOR = new Creator<Visit>() {
        @Override
        public Visit createFromParcel(Parcel in) {
            return new Visit(in);
        }

        @Override
        public Visit[] newArray(int size) {
            return new Visit[size];
        }
    };

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

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", person=" + person +
                ", doctor=" + doctor +
                ", cause='" + cause + '\'' +
                ", visitDate='" + visitDate + '\'' +
                '}';
    }
}
