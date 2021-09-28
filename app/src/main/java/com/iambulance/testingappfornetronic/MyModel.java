package com.iambulance.testingappfornetronic;

public class MyModel {

    String name;
    String email;
    String gender;
    String date;
    String time;
    String image;

    public MyModel() {
    }

    public MyModel(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public MyModel(String name, String email, String gender, String date, String time, String image) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.date = date;
        this.time = time;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
