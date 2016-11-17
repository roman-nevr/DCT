package ru.yoursolution.dct.fragments;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.yoursolution.dct.R;
import ru.yoursolution.dct.implementations.SecondDesktopPresenterImpl;
import ru.yoursolution.dct.interfaces.ISecondDesktopPresenter;
import ru.yoursolution.dct.interfaces.ISecondDesktopView;
import ru.yoursolution.dct.utils.ExternalStorageSaver;
import ru.yoursolution.dct.views.BalancedWorkTable;

/**
 * Created by Admin on 14.11.2016.
 */

public class SecondDeskTopFragment extends Fragment implements ISecondDesktopView{
    BalancedWorkTable table;
    ISecondDesktopPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sample_balanced_work_table, container, false);

        table = (BalancedWorkTable) view.findViewById(R.id.balancedWorkTable);
        presenter = new SecondDesktopPresenterImpl(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        table.setDrawingCacheEnabled(true);
        table.buildDrawingCache();

        Bitmap bitmap;
        Canvas canvas;

        /*Bitmap bitmap = Bitmap.createBitmap(table.getWidth(), table.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        table.draw(canvas);*/

        if (table.getDrawingCache() != null){
            /*
            Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            v.draw(c);*/
            bitmap = Bitmap.createBitmap(table.getWidth(), table.getHeight(), Bitmap.Config.ARGB_8888);
            canvas = new Canvas(bitmap);
            table.draw(canvas);

            String filename = ExternalStorageSaver.save("test.jpg", bitmap, Bitmap.CompressFormat.JPEG, 100);
            presenter.save();

            bitmap.recycle();
        }
    }

}
