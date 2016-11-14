package ru.yoursolution.dct.implementations;

import ru.yoursolution.dct.interfaces.ISecondDesktopPresenter;

/**
 * Created by Admin on 16.11.2016.
 */

public class SecondDesktopPresenterImpl extends AbsAsyncSender implements ISecondDesktopPresenter {
    @Override
    public String getFileName() {
        return "cache/test.jpg";
    }

    @Override
    public void save() {
        sendAsync();
    }
}
