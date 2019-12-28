package com.btlbd.sheba.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.btlbd.sheba.R;
import com.btlbd.sheba.model.OfferItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<OfferItem> offerItems;

    public OfferAdapter(Context context, ArrayList<OfferItem> offerItem) {
        mContext = context;
        offerItems = offerItem;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.offersitem_cardview, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        OfferItem currentItem = offerItems.get(position);

        String imageUrl = currentItem.getImageUrl();
        String offerName = currentItem.getOfferName();

        holder.offerText.setText(offerName);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return offerItems.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView offerText;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.offerImageId);
            offerText = itemView.findViewById(R.id.offerNameId);
        }
    }
}
