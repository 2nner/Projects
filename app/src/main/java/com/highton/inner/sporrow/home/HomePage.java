package com.highton.inner.sporrow.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.highton.inner.sporrow.R;

public class HomePage extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        tabLayout = findViewById(R.id.tl);
        viewPager = findViewById(R.id.vp);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new HomeFragment(),"홈");
        adapter.AddFragment(new ListFragment(), "목록");
        adapter.AddFragment(new JjimFragment(), "찜 목록");
        adapter.AddFragment(new ConfirmFragment(), "제안 확인");
        adapter.AddFragment(new SettingFragment(), "설정");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        // Remove Shadow From the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
    }
}
