package ru.yoursolution.dct.model;

/**
 * Created by Admin on 15.11.2016.
 */

public class Element {

    private String name;
    private int duration;
    private int workType;

    public Element(String name, int duration, int workType) {
        this.name = name;
        this.duration = duration;
        this.workType = workType;
    }

    public Element(String name) {
        this.name = name;
        this.duration = 0;
        this.workType = WorkType.USEFUL_WORK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getWorkType() {
        return workType;
    }

    public void setWorkType(int workType) {
        this.workType = workType;
    }
}
