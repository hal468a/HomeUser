package com.example.homeuser;

public class Temperature {
    String date, time, temp, user_phone;
    int id;

    public Temperature() {
    }

    public Temperature(int id, String date, String time, String temp, String user_phone) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.temp = temp;
        this.user_phone = user_phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() { return time;}

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

}
