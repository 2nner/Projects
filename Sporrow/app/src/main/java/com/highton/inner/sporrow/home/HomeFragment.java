package com.highton.inner.sporrow.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.highton.inner.sporrow.R;

import java.util.List;

public class HomeFragment extends Fragment {
    View v;
    private RecyclerView mRecyclerView;
    private List<HomeFragmentItems> lstHomeItems;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = v.findViewById(R.id.rv);
        HomeRecyclerViewAdapter recyclerViewAdapter = new HomeRecyclerViewAdapter(getContext(), lstHomeItems);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(recyclerViewAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstHomeItems.add(new HomeFragmentItems(10000,"축구화 빌려가세용 깨끗이 신고 신발 세탁 맡겨 안전할겁니다 후흣", "서울 / 강남"));
        lstHomeItems.add(new HomeFragmentItems(15000,"위와 같아보이는 신발이지만 다른 신발입니다(엄근진지)","제주"));
        lstHomeItems.add(new HomeFragmentItems(20000, "어허 기분탓이라니까요", "부산"));
    }
}
