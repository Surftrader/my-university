package com.poseal.university.model;

public class Teacher extends Person {

    private Integer subjectId;
    private Integer departmentId;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Teacher [toString()=" + super.toString() + ", subject=" + subjectId + ", departmentId=" + departmentId
                + "]";
    }
}
