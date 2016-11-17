package ru.yoursolution.dct.implementations;

import android.view.View;

import ru.yoursolution.dct.interfaces.ISecondDesktopPresenter;
import ru.yoursolution.dct.interfaces.ISecondDesktopView;

/**
 * Created by Admin on 16.11.2016.
 */

public class SecondDesktopPresenterImpl extends AbsAsyncSender implements ISecondDesktopPresenter {
    private ISecondDesktopView view;

    public SecondDesktopPresenterImpl(ISecondDesktopView view){
        this.view = view;
    }

    @Override
    public String getFileName() {
        return "cache/test.jpg";
    }

    @Override
    public View getSnackView() {
        return view.getView();
    }

    @Override
    public void save() {
        //sendAsync();
    }
}
