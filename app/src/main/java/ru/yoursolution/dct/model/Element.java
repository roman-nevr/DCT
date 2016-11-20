package ru.yoursolution.dct.model;

import android.graphics.Color;

/**
 * Created by Admin on 15.11.2016.
 */

public class Element {

    private String name;
    private int duration;
    private int workType;
    private int color;

    public Element(String name, int duration, int workType, int color) {
        this.name = name;
        this.duration = duration;
        this.workType = workType;
        this.color = color;
    }

    public Element(String name) {
        this.name = name;
        this.duration = 0;
        this.workType = WorkType.USEFUL_WORK;
        this.color = Color.RED;
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
