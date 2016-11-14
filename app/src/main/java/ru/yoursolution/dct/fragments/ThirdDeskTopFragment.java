package ru.yoursolution.dct.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ru.yoursolution.dct.ForthDesktopActivity;
import ru.yoursolution.dct.R;

/**
 * Created by Admin on 14.11.2016.
 */

public class ThirdDeskTopFragment extends Fragment {

    Button btnStartTimer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.third_desktop, container, false);
        btnStartTimer = (Button) view.findViewById(R.id.btnStartTimer);
        btnStartTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForthDesktopActivity.start(getActivity());
            }
        });
        return view;
    }

}
