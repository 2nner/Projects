package com.dev.inner.owntrip.wishList.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.inner.owntrip.R;
import com.dev.inner.owntrip.wishList.data.Item_wishList;

import java.util.List;

public class Adapter_wishList extends RecyclerView.Adapter<Adapter_wishList.ViewHolder> {
    private Context context;
    private List<Item_wishList> item_wishLists;

    public Adapter_wishList(Context context, List<Item_wishList> item_wishLists) {
        this.context = context;
        this.item_wishLists = item_wishLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_wishlist, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Presse " + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item_wishList item_wishList = item_wishLists.get(position);

        holder.tv_wishList_title.setText(item_wishList.getTv_wishList_title());
        holder.tv_wishList_content.setText(item_wishList.getTv_wishList_content());
    }

    @Override
    public int getItemCount() {
        return item_wishLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_wishList_title, tv_wishList_content;
        public ViewHolder(View view) {
            super(view);
            tv_wishList_title = view.findViewById(R.id.tv_wishList_title);
            tv_wishList_content = view.findViewById(R.id.tv_wishList_content);
        }
    }
}
