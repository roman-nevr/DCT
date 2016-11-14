package ru.yoursolution.dct.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.yoursolution.dct.R;

/**
 * Created by Admin on 14.11.2016.
 */

public class SecondDeskTopFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_desktop_layout, container, false);
        //TextView textView = (TextView) view.findViewById(R.id.tvFirst);
        //textView.setText("Второй рабочий стол");
        return view;
    }
}
