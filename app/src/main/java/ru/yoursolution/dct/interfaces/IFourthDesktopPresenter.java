package ru.yoursolution.dct.interfaces;

/**
 * Created by Admin on 14.11.2016.
 */

public interface IFourthDesktopPresenter {
    void startTimer();
    void restartTimer();
    void pauseTimer();
    void resumeTimer();
    void nextElement();
    void ignore();
    void tick();
    void setView(IFourthDesktopView view);
}
