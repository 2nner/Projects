package com.dev.inner.owntrip;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.dev.inner.owntrip.wishList.WishListFragment;

public class MainActivity extends AppCompatActivity {

    ViewPager vp_main_viewPager;

    BottomNavigationView bn_main_bottomNavi;

    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp_main_viewPager = findViewById(R.id.vp_main_viewPager);

        bn_main_bottomNavi = findViewById(R.id.bn_main_bottomNavi);

        vp_main_viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new HomeFragment();
                    case 1:
                        return new WishListFragment();
                    case 2:
                        return new MakeCourseFragment();
                    case 3:
                        return null;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });

        vp_main_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                }
                bn_main_bottomNavi.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        bn_main_bottomNavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_home:
                        vp_main_viewPager.setCurrentItem(0);
                        break;
                    case R.id.item_second:
                        vp_main_viewPager.setCurrentItem(1);
                        break;
                    case R.id.item_third:
                        vp_main_viewPager.setCurrentItem(2);
                        break;
                    case R.id.item_fourth:
                        vp_main_viewPager.setCurrentItem(3);
                }
                return false;
            }
        });
    }
}
