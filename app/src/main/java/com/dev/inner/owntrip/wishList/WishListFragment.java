package com.dev.inner.owntrip.wishList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dev.inner.owntrip.R;
import com.dev.inner.owntrip.wishList.adapter.Adapter_wishList;
import com.dev.inner.owntrip.wishList.data.Item_wishList;

import java.util.ArrayList;
import java.util.List;

public class WishListFragment extends Fragment {
    EditText et_wishList_search;

    RecyclerView rv_wishList_list;

    List<Item_wishList> list_wishLists = new ArrayList<>();

    Adapter_wishList adapter_wishList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wish_list, container, false);

        et_wishList_search = view.findViewById(R.id.et_wishList_search);

        rv_wishList_list = view.findViewById(R.id.rv_wishList_list);


        rv_wishList_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rv_wishList_list.setHasFixedSize(true);
        adapter_wishList = new Adapter_wishList(getContext(), list_wishLists);
        rv_wishList_list.setAdapter(adapter_wishList);

        return view;
    }

    public void initList() {
        for (int i=0; i<10; i++) {
            list_wishLists.add(new Item_wishList("한세사이버보안고등학교", "#공부 #학생 #고등학교", "2018.05.02"));
        }
    }


}
