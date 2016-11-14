package ru.yoursolution.dct;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import ru.yoursolution.dct.implementations.FourthDesktopActivityPresenterImpl;
import ru.yoursolution.dct.interfaces.IFourthDesktopPresenter;
import ru.yoursolution.dct.interfaces.IFourthDesktopView;

/**
 * Created by Admin on 14.11.2016.
 */

public class FourthDesktopActivity extends AppCompatActivity implements IFourthDesktopView{

    private IFourthDesktopPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(R.layout.fourth_desktop_layout);
        presenter = new FourthDesktopActivityPresenterImpl(this);

        TimeCounter timeCounter = new TimeCounter();
        Timer timer = new Timer();
        timer.schedule(timeCounter, 0, 1000);
    }

    public static void start(Context context){
        Intent intent = new Intent(context, FourthDesktopActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void setTimer() {

    }

    @Override
    public void setElement() {

    }

    @Override
    public void showPause() {

    }

    @Override
    public void showRun() {

    }

    /*
    * TimeCounter timeCounter = new TimeCounter();
        Timer timer = new Timer();
        //mTimer.schedule(mMyTimerTask, 1000);
        timer.schedule(timeCounter, 0, 500);*/
    private class TimeCounter extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //updateTimerCounter();
                }
            });

        }
    }
}
