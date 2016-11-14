package ru.yoursolution.dct.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import ru.yoursolution.dct.R;
import ru.yoursolution.dct.interfaces.IFirstDestopView;

/**
 * Created by Admin on 14.11.2016.
 */

public class FirstDeskTopFragment extends Fragment implements IFirstDestopView {

    EditText etShiftsNumber, etShiftDuration, etB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_desktop_layout, container, false);
        //TextView textView = (EditText) view.findViewById(R.id.tvFirst);
        //textView.setText("Первый рабочий стол");
        return view;
    }

    @Override
    public int getShiftsNumber() {
        return 0;
    }

    @Override
    public int getShiftDuration() {
        return 0;
    }

    @Override
    public int getBreakTime() {
        return 0;
    }

    @Override
    public int getDayStore() {
        return 0;
    }

    @Override
    public void setWorkTimeADay(int time) {

    }

    @Override
    public void setActionTime(int time) {

    }
}
