package com.example.user;

public class WorkPlace {

    String department;
    Integer experience;
    Job job;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "WorkPlace{" +
                "department='" + department + '\'' +
                ", experience=" + experience +
                ", job=" + job +
                '}';
    }
}
