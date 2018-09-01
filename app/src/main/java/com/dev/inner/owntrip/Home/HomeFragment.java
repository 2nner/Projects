package com.dev.inner.owntrip.Home;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dev.inner.owntrip.Home.adapter.HomeAdAdapter;
import com.dev.inner.owntrip.R;
import com.dev.inner.owntrip.ThemeActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    ViewPager vp_homdAd;
    TextView tv_home_size;
    ArrayList<Integer> img = new ArrayList<>();

    ImageView iv_home_theme1, iv_home_theme2, iv_home_theme3, iv_home_theme4;

    // Auto ViewPager Loop Setting
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 4000, PERIOD_MS = 4000;

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
        img.add(R.drawable.vp_img5);
        vp_homdAd = view.findViewById(R.id.vp_home_showad);

        tv_home_size = view.findViewById(R.id.tv_home_size);

        iv_home_theme1 = view.findViewById(R.id.iv_home_theme1);
        iv_home_theme2 = view.findViewById(R.id.iv_home_theme2);
        iv_home_theme3 = view.findViewById(R.id.iv_home_theme3);
        iv_home_theme4 = view.findViewById(R.id.iv_home_theme4);

        //travel theme
        Glide.with(getActivity()).asBitmap().load(R.drawable.beach).into(iv_home_theme1);
        Glide.with(getActivity()).asBitmap().load(R.drawable.camping).into(iv_home_theme2);
        Glide.with(getActivity()).asBitmap().load(R.drawable.matzip).into(iv_home_theme3);
        Glide.with(getActivity()).asBitmap().load(R.drawable.hotel).into(iv_home_theme4);

        iv_home_theme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ThemeActivity.class);
                intent.putExtra("tag", "#여름");
                startActivity(intent);
            }
        });

        iv_home_theme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ThemeActivity.class);
                intent.putExtra("tag", "#캠핑");
                startActivity(intent);
            }
        });

        iv_home_theme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ThemeActivity.class);
                intent.putExtra("tag", "#맛집");
                startActivity(intent);
            }
        });

        iv_home_theme4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ThemeActivity.class);
                intent.putExtra("tag", "#호캉스");
                startActivity(intent);
            }
        });

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

        vp_homdAd.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                Resources res = getResources();
                String adimageCount = String.format(res.getString(R.string.adimageCount), vp_homdAd.getCurrentItem()+1, img.size());
                tv_home_size.setText(adimageCount);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

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
