package ru.yoursolution.dct.implementations;

import ru.yoursolution.dct.interfaces.IFourthDesktopPresenter;
import ru.yoursolution.dct.interfaces.IFourthDesktopView;

/**
 * Created by Admin on 14.11.2016.
 */

public class FourthDesktopActivityPresenterImpl implements IFourthDesktopPresenter {

    private IFourthDesktopView view;

    public FourthDesktopActivityPresenterImpl(IFourthDesktopView view){
        this.view = view;
    }

    @Override
    public void startTimer(int tick) {

    }

    @Override
    public void restartTimer() {

    }

    @Override
    public void pauseTimer() {

    }

    @Override
    public void resumeTimer() {

    }

    @Override
    public void nextElement() {

    }

    @Override
    public void ignore() {

    }
}
