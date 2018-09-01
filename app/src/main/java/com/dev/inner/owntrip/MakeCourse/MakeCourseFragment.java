package com.dev.inner.owntrip.MakeCourse;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dev.inner.owntrip.R;

import java.util.ArrayList;

public class MakeCourseFragment extends Fragment implements View.OnClickListener {

    FrameLayout fl;
    ImageView iv_select1, iv_select2, iv_select3, iv_find;
    ArrayList<String> request = new ArrayList<>();

    Context mContext;

    // 현재 포커스를 가지고 있는 Circle의 Index
    int index = 0;
    // 활성화 되었는지를 검사하는 변수들
    boolean circle1 = false, circle2 = false, circle3 = false;

    public MakeCourseFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_make_course, container, false);

        // findViewById
        fl = v.findViewById(R.id.circleflag);
        fl.setOnClickListener(this);
        iv_select1 = v.findViewById(R.id.iv_make_select1);
        iv_select1.setOnClickListener(this);
        iv_select2 = v.findViewById(R.id.iv_make_select2);
        iv_select2.setOnClickListener(this);
        iv_select3 = v.findViewById(R.id.iv_make_select3);
        iv_select3.setOnClickListener(this);
        iv_find = v.findViewById(R.id.iv_make_find);
        iv_find.setOnClickListener(this);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.circleflag:
                index = 0;
                break;
            case R.id.iv_make_select1:
                action1();
                break;
            case R.id.iv_make_select2:
                action2();
                break;
            case R.id.iv_make_select3:
                action3();
                break;
            case R.id.tv_make_complete:
                send();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void action1() {
        index = 1;
        circle1 = true;
        Glide.with(mContext).load(R.drawable.circle_red).into(iv_select1);
        iv_select2.setVisibility(View.VISIBLE);
    }

    public void action2() {
        if(!circle1) {
            Toast.makeText(mContext, "이전의 여정에 여행을 등록하세요!", Toast.LENGTH_SHORT).show();
        }
        else {
            index = 2;
            circle2 = true;
            Glide.with(mContext).load(R.drawable.circle_red).into(iv_select2);
            iv_select3.setVisibility(View.VISIBLE);
        }
    }

    public void action3() {
        if(!circle2) {
            Toast.makeText(mContext, "이전의 여정에 여행을 등록하세요!", Toast.LENGTH_SHORT).show();
        }
        else {
            index = 3;
            circle3 = true;
            Glide.with(mContext).load(R.drawable.circle_red).into(iv_select3);
        }
    }

    public void send() {
        // 여기다가 request 전송해서 서버 통신
        cancellAll();
    }

    public void cancellAll() {
        request.clear();
        index = 0;
        circle1 = circle2 = circle3 = false;
        Glide.with(mContext).load(R.drawable.ic_add).into(iv_select1);
        Glide.with(mContext).load(R.drawable.ic_add).into(iv_select2);
        Glide.with(mContext).load(R.drawable.ic_add).into(iv_select3);
    }
}
