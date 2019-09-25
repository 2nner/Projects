package com.dev.inner.owntrip.Home.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dev.inner.owntrip.R;

import java.util.ArrayList;

public class HomeAdAdapter extends PagerAdapter {

    private Activity activity;
    private ArrayList<Integer> img;

    public HomeAdAdapter(Activity activity, ArrayList<Integer> img) {
        this.activity = activity;
        this.img = img;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = activity.getLayoutInflater();

        View viewItem = inflater.inflate(R.layout.item_adimage, container, false);
        ImageView iv_Img = viewItem.findViewById(R.id.item_img);
        Glide.with(viewItem).load(img.get(position)).into(iv_Img);

        container.addView(viewItem);
        return viewItem;
    }

    @Override
    public int getCount() {
        return img.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((View) object);
    }
}
