package com.dev.inner.owntrip.MakeCourse.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dev.inner.owntrip.MakeCourse.MakeCourseFragment;
import com.dev.inner.owntrip.MakeCourse.data.Item_makeCourse;
import com.dev.inner.owntrip.R;
import com.dev.inner.owntrip.RetrofitService;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_makeCourse extends RecyclerView.Adapter<Adapter_makeCourse.ViewHolder> {
    private Context context;
    private List<Item_makeCourse> item_makeCourses;

    public Adapter_makeCourse(Context context, List<Item_makeCourse> item_makeCourses) {
        this.context = context;
        this.item_makeCourses = item_makeCourses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_makecourse, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item_makeCourse item_makeCourse = item_makeCourses.get(position);

        Glide.with(context).asBitmap().load(RetrofitService.BASE_URL + item_makeCourse.getImagePath()).into(holder.iv_makeCourse_thumbnail);

        holder.tv_makeCourse_title.setText(item_makeCourse.getPlaceName());
        holder.tv_makeCourse_address.setText(item_makeCourse.getAddress());
        holder.tv_wishList_tag1.setText(item_makeCourse.getTags().get(0).getTagName());
    }

    @Override
    public int getItemCount() {
        return item_makeCourses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView iv_makeCourse_thumbnail;
        TextView tv_makeCourse_title, tv_makeCourse_address, tv_wishList_tag1, tv_wishList_tag2, tv_wishList_tag3, tv_wishList_tag4;
        public ViewHolder(View view) {
            super(view);
            iv_makeCourse_thumbnail = view.findViewById(R.id.iv_makeCourse_thumbnail);

            tv_makeCourse_title = view.findViewById(R.id.tv_makeCourse_title);
            tv_makeCourse_address = view.findViewById(R.id.tv_makeCourse_address);

            tv_wishList_tag1 = view.findViewById(R.id.tv_wishList_tag1);
            tv_wishList_tag2 = view.findViewById(R.id.tv_wishList_tag2);
            tv_wishList_tag3 = view.findViewById(R.id.tv_wishList_tag3);
            tv_wishList_tag4 = view.findViewById(R.id.tv_wishList_tag4);
        }
    }
}
