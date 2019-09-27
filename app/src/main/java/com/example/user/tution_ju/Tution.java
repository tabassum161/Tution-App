package com.example.user.tution_ju;

public class Tution {
    private String sClass;
    private String subjects;
    private String gender;
    private String location;
    private String salary;
    private String phoneNo;

    public Tution() {
    }

    public Tution(String sClass, String subjects, String gender, String location, String salary, String phoneNo) {
        this.sClass = sClass;
        this.subjects = subjects;
        this.gender = gender;
        this.location = location;
        this.salary = salary;
        this.phoneNo = phoneNo;
    }

    public String getsClass() {
        return sClass;
    }

    public String getSubjects() {
        return subjects;
    }

    public String getGender() {
        return gender;
    }

    public String getLocation() {
        return location;
    }

    public String getSalary() {
        return salary;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
}
