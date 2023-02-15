package com.example.myapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person implements Parcelable {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("last_name")
    private String lastName;

    @Expose
    @SerializedName("first_name")
    private String firstName;

    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("phone")
    private String phone;
    @Expose
    @SerializedName("age")
    private int age;

    public Person() {
    }

    public Person(String lastName, String firstName, String email, String phone, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.age = age;
    }

    public Person(int id, String lastName, String firstName, String email, String phone, int age) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.age = age;
    }

    protected Person(Parcel in) {
        id = in.readInt();
        lastName = in.readString();
        firstName = in.readString();
        email = in.readString();
        phone = in.readString();
        age = in.readInt();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFullName(){
        return this.getFirstName() + " " + this.getLastName();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(lastName);
        dest.writeString(firstName);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeInt(age);
    }
}
