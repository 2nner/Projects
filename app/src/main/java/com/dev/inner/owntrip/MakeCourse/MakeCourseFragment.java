package com.dev.inner.owntrip.MakeCourse;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.inner.owntrip.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MakeCourseFragment extends Fragment implements View.OnClickListener {

    TextView tv_step1, tv_step2, tv_step3, tv_step4;
    CircleImageView iv_address;
    ArrayList<String> request = new ArrayList<>();

    public MakeCourseFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_make_course, container, false);

        // findByViewId
        tv_step1 = v.findViewById(R.id.tv_make_no1);
        tv_step1.setOnClickListener(this);
        tv_step2 = v.findViewById(R.id.tv_make_no2);
        tv_step2.setOnClickListener(this);
        tv_step3 = v.findViewById(R.id.tv_make_no3);
        tv_step3.setOnClickListener(this);
        tv_step4 = v.findViewById(R.id.tv_make_no4);
        tv_step4.setOnClickListener(this);
        iv_address = v.findViewById(R.id.civ_make_address);
        iv_address.setOnClickListener(this);

        return v;
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.civ_make_address:
                action1();
                break;
            case R.id.tv_make_no1:
                action2();
                break;
            case R.id.tv_make_no2:
                action3();
                break;
            case R.id.tv_make_no3:
                action4();
                break;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void action1() {
        tv_step1.setVisibility(View.VISIBLE);
    }

    public void action2() {
        tv_step2.setVisibility(View.VISIBLE);
    }

    public void action3() {

    }

    public void action4() {

    }
}
