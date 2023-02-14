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

    protected Doctor(Parcel in) {
        id = in.readInt();
        fullName = in.readString();
        qualification = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(fullName);
        dest.writeString(qualification);
    }
}
