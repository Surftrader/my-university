package com.poseal.university.resource.beans;

import javax.ws.rs.QueryParam;

public class ScheduleFilterBean {

    private @QueryParam("groupId") Integer groupId;
    private @QueryParam("teacherId") Integer teacherId;
    private @QueryParam("dayStart") String dayStart;
    private @QueryParam("dayEnd") String dayEnd;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getDayStart() {
        return dayStart;
    }

    public void setDayStart(String dayStart) {
        this.dayStart = dayStart;
    }

    public String getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(String dayEnd) {
        this.dayEnd = dayEnd;
    }
}
