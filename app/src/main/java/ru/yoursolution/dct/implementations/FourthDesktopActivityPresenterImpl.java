package ru.yoursolution.dct.implementations;

import java.util.Timer;
import java.util.TimerTask;

import ru.yoursolution.dct.interfaces.IFourthDesktopPresenter;
import ru.yoursolution.dct.interfaces.IFourthDesktopView;
import ru.yoursolution.dct.utils.ExternalStorageSaver;

/**
 * Created by Admin on 14.11.2016.
 */

public class FourthDesktopActivityPresenterImpl extends AbsAsyncSender implements IFourthDesktopPresenter {

    private Timer timer;
    private IFourthDesktopView view;
    private int time;
    private boolean timerIsActive;

    public FourthDesktopActivityPresenterImpl(IFourthDesktopView view){
        this.view = view;
        time = 0;
        startTimer();
    }

    private void setupTimer(int timeMillis){

    }

    @Override
    public void startTimer() {
        timer = view.getTimerObject(1000);
        timerIsActive = true;
    }

    @Override
    public void restartTimer() {
        time = 0;
        view.setTimer(0);
    }

    @Override
    public void pauseTimer() {
        if(timerIsActive){
            timer.cancel();
            timerIsActive = false;
        }else {
            startTimer();
        }
    }

    @Override
    public void resumeTimer() {
        startTimer();
    }

    @Override
    public void nextElement() {
        restartTimer();
    }

    @Override
    public void ignore() {
        restartTimer();
        sendAsync();
    }

    @Override
    public void tick() {
        time++;
        view.setTimer(time);
    }

    @Override
    public void setView(IFourthDesktopView view) {
        this.view = view;
        view.setTimer(time);
    }

    @Override
    public String getFileName() {
        return "Download/15281.jpg";
    }
}
