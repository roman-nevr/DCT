package ru.yoursolution.dct.implementations;

import ru.yoursolution.dct.interfaces.IFirstDesktopPresenter;
import ru.yoursolution.dct.interfaces.IFirstDestopView;

/**
 * Created by Admin on 14.11.2016.
 */

public class FirstDesktopPresenterImpl implements IFirstDesktopPresenter {

    private IFirstDestopView view;

    public FirstDesktopPresenterImpl(IFirstDestopView view){
        this.view = view;
    }

    @Override
    public void calculateWorkTimeADay() {
        try {
            view.setWorkTimeADay(view.getShiftsNumber() * (view.getShiftDuration() - view.getBreakTime()));
        }catch (Exception e){
            view.setWorkTimeADay(-1);
        }

    }

    @Override
    public void calculateActionTime() {
        try {
            view.setActionTime((view.getShiftsNumber() * (view.getShiftDuration() - view.getBreakTime())) / view.getDayStore());
        }catch (Exception e){
            view.setActionTime(-1);
        }

    }
}
