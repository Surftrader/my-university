package com.poseal.university.model;

public class Student extends Person {

    private Integer groupId;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "Student [toString()=" + super.toString() + ", groupId=" + groupId + "]";
    }
}
