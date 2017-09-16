package com.example.pc.forwardcam_new;

import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import com.rd.PageIndicatorView;


public class SlideActivity extends AppCompatActivity {
    ViewPager vp; // 뷰페이지 변수 선언
    ImageButton btn_home_home;
    ImageButton btn_home_health;
    ImageButton btn_home_bluetooth;
    ImageButton btn_home_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slidemain);

        // 아이디로 뷰페이지와 각 버튼을 컨트롤 할 수 있게 함
        vp=(ViewPager)findViewById(R.id.vp);
        btn_home_home = (ImageButton)findViewById(R.id.btn_home_home);
        btn_home_health = (ImageButton)findViewById(R.id.btn_home_health);
        btn_home_bluetooth = (ImageButton)findViewById(R.id.btn_home_bluetooth);
        btn_home_user = (ImageButton)findViewById(R.id.btn_home_user);

        vp.setAdapter(new pagerAdapter(getSupportFragmentManager())); // 리스트뷰에도 쓰이는 어댑터는 각 뷰에 데이터를 뿌려주는 역할을 함.
        vp.setCurrentItem(0);                                        // 앱이 실행되었을 때 첫번째 페이지로 초기화 시키는 부분

        btn_home_home.setOnClickListener(movePageListener);
        btn_home_home.setTag(0);

        btn_home_health.setOnClickListener(movePageListener);
        btn_home_health.setTag(1);

        btn_home_bluetooth.setOnClickListener(movePageListener);
        btn_home_bluetooth.setTag(2);

        btn_home_user.setOnClickListener(movePageListener);
        btn_home_user.setTag(3);

        PageIndicatorView pageIndicatorView = (PageIndicatorView) findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setViewPager(vp);
    }


    View.OnClickListener movePageListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            int tag = (int) v.getTag();
            vp.setCurrentItem(tag);
        }
    };

    private class pagerAdapter extends FragmentStatePagerAdapter
    {
        public pagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return new HomeFragment();  // 포지션에 따라 생성해놓은 클래스를 부름
                case 1:
                    return new HealthFragment(); // ""
                case 2:
                    return new BluetoothFragment();  // ""
                case 3:
                    return new UserFragment();  // ""
                default:
                    return null;
            }
        }
        @Override
        public int getCount() // ViewPager 안에 들어가는 Page 의 갯수
        {
            return 4;
        }
    }
}
