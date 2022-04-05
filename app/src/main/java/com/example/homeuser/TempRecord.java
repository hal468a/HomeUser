package com.example.homeuser;

public class TempRecord {
    private String date, time, temp;

    public TempRecord(String date, String time, String temp){
        this.date = date;
        this.time = time;
        this.temp = temp;
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

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }



}
