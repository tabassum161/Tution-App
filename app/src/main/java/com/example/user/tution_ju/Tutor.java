package com.example.user.tution_ju;

public class Tutor {
    private String name;
    private String institute;
    private String dept;
    private String subjects;
    private String phoneNo;

    public Tutor() {
    }

    public Tutor(String name, String institute, String dept, String subjects, String phoneNo) {
        this.name = name;
        this.institute = institute;
        this.dept = dept;
        this.subjects = subjects;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public String getInstitute() {
        return institute;
    }

    public String getDept() {
        return dept;
    }

    public String getSubjects() {
        return subjects;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
}
