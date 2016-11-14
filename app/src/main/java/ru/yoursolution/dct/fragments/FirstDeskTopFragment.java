package ru.yoursolution.dct.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import ru.yoursolution.dct.R;
import ru.yoursolution.dct.implementations.FirstDesktopPresenterImpl;
import ru.yoursolution.dct.interfaces.IFirstDesktopPresenter;
import ru.yoursolution.dct.interfaces.IFirstDestopView;

/**
 * Created by Admin on 14.11.2016.
 */

public class FirstDeskTopFragment extends Fragment implements IFirstDestopView {

    private EditText etShiftsNumber, etShiftDuration, etBreakTime, etDayStore;
    private TextView tvWorkTimeADay, tvActionTime;
    private IFirstDesktopPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_desktop_layout, container, false);
        init(view);
        presenter = new FirstDesktopPresenterImpl(this);
        return view;
    }

    private void init(View view) {
        etShiftsNumber = (EditText) view.findViewById(R.id.etShiftsNumber);
        etShiftsNumber.addTextChangedListener(new InputTextWatcher());
        etShiftDuration = (EditText) view.findViewById(R.id.etShiftDuration);
        etShiftDuration.addTextChangedListener(new InputTextWatcher());
        etBreakTime = (EditText) view.findViewById(R.id.etBreakTime);
        etBreakTime.addTextChangedListener(new InputTextWatcher());
        etDayStore = (EditText) view.findViewById(R.id.etDayStore);
        etDayStore.addTextChangedListener(new InputTextWatcher());
        tvActionTime = (TextView) view.findViewById(R.id.etActionTime);
        tvWorkTimeADay = (TextView) view.findViewById(R.id.etWorkTimeADay);
    }

    @Override
    public int getShiftsNumber() {
        return Integer.parseInt(etShiftsNumber.getText().toString());
    }

    @Override
    public int getShiftDuration() {
        return Integer.parseInt(etShiftDuration.getText().toString());
    }

    @Override
    public int getBreakTime() {
        return Integer.parseInt(etBreakTime.getText().toString());
    }

    @Override
    public int getDayStore() {
        return Integer.parseInt(etDayStore.getText().toString());
    }

    @Override
    public String getWarningMessage() {
        return getResources().getString(R.string.noDataWarning);
    }

    @Override
    public void setWorkTimeADay(int time) {
        if(time == -1){
            tvWorkTimeADay.setText(R.string.noDataWarning);
        }else tvWorkTimeADay.setText("" + time);
    }

    @Override
    public void setActionTime(int time) {
        if(time == -1){
            tvActionTime.setText(R.string.noDataWarning);
        }else tvActionTime.setText("" + time);
    }

    private class InputTextWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            presenter.calculateActionTime();
            presenter.calculateWorkTimeADay();
        }
    }
}
