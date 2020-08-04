package com.example.user;

public class Job {

    private String name;
    private int workingHours;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    @Override
    public String toString() {
        return "Job{" +
                "name='" + name + '\'' +
                ", workingHours=" + workingHours +
                '}';
    }
}
