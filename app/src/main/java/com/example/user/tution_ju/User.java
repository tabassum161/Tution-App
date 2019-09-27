package com.example.user.tution_ju;

public class User {
    private String email;
    private String password;
    private String registrationDate;

    public User() {
    }

    public User(String email, String password, String registrationDate) {
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }
}
