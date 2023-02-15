package com.example.myapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Doctor implements Parcelable {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("first_name")
    private String firstName;

    @Expose
    @SerializedName("last_name")
    private String lastName;

    @Expose
    @SerializedName("qualification")
    private String qualification;

    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("country")
    private String country;





    public Doctor() {
    }

    public Doctor(String firstName, String lastName, String qualification, String email, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.qualification = qualification;
        this.email = email;
        this.country = country;
    }

    public Doctor(int id, String firstName, String lastName, String qualification, String email, String country) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.qualification = qualification;
        this.email = email;
        this.country = country;
    }

    protected Doctor(Parcel in) {
        id = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        qualification = in.readString();
        email = in.readString();
        country = in.readString();
    }

    public static final Creator<Doctor> CREATOR = new Creator<Doctor>() {
        @Override
        public Doctor createFromParcel(Parcel in) {
            return new Doctor(in);
        }

        @Override
        public Doctor[] newArray(int size) {
            return new Doctor[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getFullName(){
        return this.getFirstName() + " " + this.getLastName();
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", qualification='" + qualification + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(qualification);
        dest.writeString(email);
        dest.writeString(country);
    }
}
