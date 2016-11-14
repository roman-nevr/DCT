package ru.yoursolution.dct.model;

import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by Admin on 15.11.2016.
 */

public class WorkType {
    public static int USEFUL_WORK = 0;
    public static int WAITING = 1;
    public static int TRANSIT = 2;
    public static int WASTE_WORK = 3;

    public static Integer getColor(int workType){
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.RED);
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        return colors.get(workType);
    }
}