package ru.yoursolution.dct;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    private TextView tvTimerCounter;
    private Button btnPause, btnNextElement, btnIgnore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(R.layout.fourth_desktop_layout);
        tvTimerCounter = (TextView) findViewById(R.id.tvTimerCounter);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.pauseTimer();
            }
        });
        btnNextElement = (Button) findViewById(R.id.btnNextElement);
        btnNextElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.nextElement();
            }
        });
        btnIgnore = (Button) findViewById(R.id.btnIgnore);
        btnIgnore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.ignore();
            }
        });
        presenter = (IFourthDesktopPresenter) getLastCustomNonConfigurationInstance();
        if (presenter == null){
            presenter = new FourthDesktopActivityPresenterImpl(this);
            setTimer(0);
        }else {
            presenter.setView(this);
        }
    }

    public static void start(Context context){
        Intent intent = new Intent(context, FourthDesktopActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void setTimer(int time) {
        tvTimerCounter.setText(formatTime(time));
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

    @Override
    public Timer getTimerObject(int tick) {
        TimeCounter timeCounter = new TimeCounter();
        Timer timer = new Timer();
        timer.schedule(timeCounter, tick, tick);
        return timer;
    }

    private class TimeCounter extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    presenter.tick();
                }
            });

        }
    }

    private String formatTime(int time){
        int minutes = time / 60;
        int seconds = time % 60;
        return "" + minutes / 10 + minutes % 10 + ":" + seconds / 10 + seconds % 10;
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenter;
    }
}
