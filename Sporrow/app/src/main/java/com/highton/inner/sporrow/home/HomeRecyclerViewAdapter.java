package com.highton.inner.sporrow.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.highton.inner.sporrow.R;

import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<HomeFragmentItems> mData;

    public HomeRecyclerViewAdapter(Context context, List<HomeFragmentItems> data) {
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_home, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.location.setText(mData.get(position).getTradeArea());
        holder.content.setText(mData.get(position).getTitle());
        holder.price.setText("1일 "+mData.get(position).getBorrowPrice()+"주말 포함");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView location, content, price;

        public MyViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cv);
            location = itemView.findViewById(R.id.tv_location);
            content = itemView.findViewById(R.id.tv_content);
            price = itemView.findViewById(R.id.tv_price);
        }
    }
}

