package com.poseal.university.model;

public class Teacher extends Person {

    private Subject subject;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Teacher [subject=" + subject + ", toString()=" + super.toString() + "]";
    }
}
