package com.dev.inner.owntrip.MakeCourse;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dev.inner.owntrip.MakeCourse.adapter.Adapter_makeCourse;
import com.dev.inner.owntrip.MakeCourse.data.Item_makeCourse;
import com.dev.inner.owntrip.MakeCourse.data.Tags;
import com.dev.inner.owntrip.R;
import com.dev.inner.owntrip.RetrofitCreator;
import com.dev.inner.owntrip.RetrofitResponse;
import com.dev.inner.owntrip.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakeCourseFragment extends Fragment implements View.OnClickListener {

    FrameLayout fl;
    ImageView iv_select1, iv_select2, iv_select3, iv_find, iv_make_find;
    ArrayList<String> request = new ArrayList<>();
    List<Item_makeCourse> list_makeCourses = new ArrayList<>();
    TextView tv_make_complete;
    EditText et_make_findhash;

    RecyclerView rv_make_item;
    Adapter_makeCourse adapter_wishList;
    Context mContext;

    // 현재 포커스를 가지고 있는 Circle의 Index
    int index = 0;
    // 활성화 되었는지를 검사하는 변수들
    boolean circle1 = false, circle2 = false, circle3 = false;
    // 사용된 Circle인지를 검사하는 변수들
    List<Boolean> isCircledUsed;

    public MakeCourseFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_make_course, container, false);

        for(int i=0;i<3;i++)
            isCircledUsed.add(false);

        // findViewById
        fl = v.findViewById(R.id.circleflag);
        fl.setOnClickListener(this);
        iv_select1 = v.findViewById(R.id.iv_make_select1);
        iv_select1.setOnClickListener(this);
        iv_select2 = v.findViewById(R.id.iv_make_select2);
        iv_select2.setOnClickListener(this);
        iv_select3 = v.findViewById(R.id.iv_make_select3);
        iv_select3.setOnClickListener(this);
        iv_make_find = v.findViewById(R.id.iv_make_find);
        iv_make_find.setOnClickListener(this);

        tv_make_complete = v.findViewById(R.id.tv_make_complete);
        tv_make_complete.setOnClickListener(this);

        et_make_findhash = v.findViewById(R.id.et_make_findhash);

        rv_make_item = v.findViewById(R.id.rv_make_item);

        rv_make_item.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rv_make_item.setHasFixedSize(true);

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
                break;
            case R.id.iv_make_find:
                search(et_make_findhash.getText().toString());
                break;
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

    public void add(int position) {
        if(!isCircledUsed.get(position-1)) {
            isCircledUsed.set(position-1, true);

            switch(position) {
                case 1:
                    Glide.with(mContext).load(R.drawable.ic_added).into(iv_select1);
                    break;
                case 2:
                    Glide.with(mContext).load(R.drawable.ic_added).into(iv_select2);
                    break;
                case 3:
                    Glide.with(mContext).load(R.drawable.ic_added).into(iv_select3);
                    break;
            }
            // 추가하는 이벤트 여기에 작성
        }
        else {
            Toast.makeText(mContext.getApplicationContext(), "이미 채워진 여정입니다! 다른 여정을 선택해주세요!", Toast.LENGTH_LONG).show();
        }
    }

    public void search(String word) {
        RetrofitService service = RetrofitCreator.createService(RetrofitService.class);

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1SWQiOjEsImVtYWlsIjoidGVzdHVzZXJAdGVzdC5jb20iLCJ1c2VybmFtZSI6InRlc3R1c2VyIiwiaWF0IjoxNTM1Nzg1NTE5fQ.luypTrMhcNHZH-d6cXQFrAXBjbNP2_DYRRQI4OQBnQ0";
        Call<RetrofitResponse> response = service.search(token, word);
        response.enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {
                Toast.makeText(getContext(), "Pass", Toast.LENGTH_SHORT).show();
                Log.d("search", "onResponse: " + response.code());
                for (int i=0; i<response.body().getItem_makeCourses().size(); i++) {
                    addCourseItem(
                            response.body().getItem_makeCourses().get(i).getPId(),
                            response.body().getItem_makeCourses().get(i).getPlaceName(),
                            response.body().getItem_makeCourses().get(i).getAddress(),
                            response.body().getItem_makeCourses().get(i).getImagePath(),
                            response.body().getItem_makeCourses().get(i).getCreatedAt(),
                            response.body().getItem_makeCourses().get(i).getUpdatedAt(),
                            response.body().getItem_makeCourses().get(i).getTags());
                }
            }

            @Override
            public void onFailure(Call<RetrofitResponse> call, Throwable t) {

            }
        });
    }

    public void cancellAll() {
        request.clear();
        index = 0;
        circle1 = circle2 = circle3 = false;

        for(int i=0;i<3;i++) {
            isCircledUsed.set(i, false);
        }
        Glide.with(mContext).load(R.drawable.ic_add).into(iv_select1);
        Glide.with(mContext).load(R.drawable.ic_add).into(iv_select2);
        Glide.with(mContext).load(R.drawable.ic_add).into(iv_select3);
    }

    public void addCourseItem(Integer pId, String placeName, String address, String imagePath, String createdAt, String updatedAt, List<Tags> tags) {
        list_makeCourses.add(new Item_makeCourse(pId, placeName, address, imagePath, createdAt, updatedAt, tags));
        adapter_wishList = new Adapter_makeCourse(getContext(), list_makeCourses);
        rv_make_item.setAdapter(adapter_wishList);
    }
}
