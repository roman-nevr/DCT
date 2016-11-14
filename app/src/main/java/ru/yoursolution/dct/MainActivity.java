package ru.yoursolution.dct;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.TabLayout;

import ru.yoursolution.dct.fragments.FirstDeskTopFragment;
import ru.yoursolution.dct.fragments.SecondDeskTopFragment;
import ru.yoursolution.dct.fragments.ThirdDeskTopFragment;
import ru.yoursolution.dct.utils.IntentEmailSender;
import ru.yoursolution.dct.utils.MailSenderClass;
import ru.yoursolution.dct.utils.utils;

public class MainActivity extends AppCompatActivity {

    MainAdapter adapter;
    ViewPager pager;
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MainAdapter(getSupportFragmentManager());

        pager = (ViewPager) findViewById(R.id.vpMain);
        pager.setAdapter(adapter);
        //pager.setCurrentItem(1); // выводим второй экран
        pager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View v, float pos) {
                final float invt = Math.abs(Math.abs(pos) - 1);
                v.setAlpha(invt);
            }
        });
        tabs = (TabLayout) findViewById(R.id.tlTabs);
        tabs.setupWithViewPager(pager);

    }

    public static class MainAdapter extends FragmentPagerAdapter {
        public MainAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:

                    return new FirstDeskTopFragment();
                case 1:
                    return new SecondDeskTopFragment();
                case 2:
                    return new ThirdDeskTopFragment();

                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Первый";
                case 1:
                    return "Второй";
                case 2:
                    return "Третий";
            }
            return null;
        }
    }

}
