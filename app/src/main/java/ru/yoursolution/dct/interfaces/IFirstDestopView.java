package ru.yoursolution.dct.interfaces;

/**
 * Created by Admin on 14.11.2016.
 */

public interface IFirstDestopView {
    public int getShiftsNumber();
    public int getShiftDuration();
    public int getBreakTime();
    public int getDayStore();
    public void setWorkTimeADay(int time);
    public void setActionTime(int time);
}
