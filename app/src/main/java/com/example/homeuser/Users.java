package com.example.homeuser;

public class Users {
    private String name, sex, email, phone,password;
    private String register_date;
    private int uId;

    public Users() {
    }

    public Users(int uID, String name, String sex, String email, String phone, String password,  String register_date) {
        this.name = name;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.register_date = register_date;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public  String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }

}
