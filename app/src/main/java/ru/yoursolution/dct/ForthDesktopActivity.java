package ru.yoursolution.dct;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.TimerTask;

import ru.yoursolution.dct.fragments.ThirdDeskTopFragment;

/**
 * Created by Admin on 14.11.2016.
 */

public class ForthDesktopActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forth_desktop_layout);
    }

    public static void start(Context context){
        Intent intent = new Intent(context, ForthDesktopActivity.class);
        context.startActivity(intent);
    }

    /*
    * TimeCounter timeCounter = new TimeCounter();
        Timer timer = new Timer();
        //mTimer.schedule(mMyTimerTask, 1000);
        timer.schedule(timeCounter, 0, 500);*/
    class TimeCounter extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                }
            });

        }
    }
}
