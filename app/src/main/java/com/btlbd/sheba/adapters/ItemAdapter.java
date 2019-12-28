package com.btlbd.sheba.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.btlbd.sheba.R;
import com.btlbd.sheba.model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<Item> itemList;

    public ItemAdapter(Context context, ArrayList<Item> item) {
        mContext = context;
        itemList = item;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.itemview, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Item currentItem = itemList.get(position);

        String imageUrl = currentItem.getItemImageUrl();
        String offerName = currentItem.getItemOfferName();

        holder.itemName.setText(offerName);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.itemImageView);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemImageView;
        public TextView itemName;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            itemImageView = itemView.findViewById(R.id.itemImageId);
            itemName = itemView.findViewById(R.id.itemNameId);
        }
    }
}

