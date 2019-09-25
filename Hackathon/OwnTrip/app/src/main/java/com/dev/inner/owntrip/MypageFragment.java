package com.dev.inner.owntrip;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MypageFragment extends Fragment {

    ImageView iv_userImg;
    TextView tv_username, tv_email;
    ListView lv_menu;

    static final String[] LIST_MENU = {"나의 여행코스", "고객센터", "이용약관", "Push"};

    public MypageFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);

        // findViewById()
        iv_userImg = view.findViewById(R.id.iv_mypage_userimg);
        tv_username = view.findViewById(R.id.tv_mypage_name);
        tv_email = view.findViewById(R.id.tv_mypage_email);
        lv_menu = view.findViewById(R.id.lv_mypage_menu);

        Glide.with(MypageFragment.this).load(R.drawable.ic_user).into(iv_userImg);

        // Set up ListView with ArrayAdpater
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, LIST_MENU);
        lv_menu.setAdapter(mAdapter);

        return view;
    }


}
