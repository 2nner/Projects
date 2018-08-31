package com.dev.inner.owntrip.Home;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.inner.owntrip.Home.adapter.HomeAdAdapter;
import com.dev.inner.owntrip.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    ViewPager vp_homdAd;
    ArrayList<Integer> img = new ArrayList<>();

    // Auto ViewPager Loop Setting
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 8000, PERIOD_MS = 8000;

    public HomeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Add Images & findViewById
        img.add(R.drawable.vp_img1);
        img.add(R.drawable.vp_img2);
        img.add(R.drawable.vp_img3);
        img.add(R.drawable.vp_img4);
        vp_homdAd = view.findViewById(R.id.vp_home_showad);

        // Set up ViewPager with setAdapter()
        PagerAdapter adapter = new HomeAdAdapter(getActivity(), img);
        vp_homdAd.setAdapter(adapter);

        // Set up Timer
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if(currentPage ==  img.size()-1) {
                    currentPage = 0;
                }
                vp_homdAd.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        return view;
    }

}
