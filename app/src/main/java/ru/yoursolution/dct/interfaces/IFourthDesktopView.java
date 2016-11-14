package ru.yoursolution.dct.interfaces;

import java.util.Timer;

/**
 * Created by Admin on 14.11.2016.
 */

public interface IFourthDesktopView {
    void setTimer(int time);
    void setElement();
    void showPause();
    void showRun();
    Timer getTimerObject(int time);
}
