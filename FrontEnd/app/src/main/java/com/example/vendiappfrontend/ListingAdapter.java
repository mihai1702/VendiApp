package com.example.vendiappfrontend;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vendiappfrontend.R;
import com.example.vendiappfrontend.Listing;

import java.util.List;

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ViewHolder> {
    private Context context;
    private List<Listing> listings;

    public ListingAdapter(Context context, List<Listing> listings) {
        this.context = context;
        this.listings = listings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_listing, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Listing listing = listings.get(position);
        holder.title.setText(listing.getTitle());
        holder.description.setText(listing.getDescription());
        holder.price.setText(listing.getPrice()+"EUR");

        String imageUrl = listing.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(context)
                    .load(imageUrl)
                    .into(holder.image);
        } else {
            Toast.makeText(context.getApplicationContext(), "problema e aici", Toast.LENGTH_SHORT).show();

        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MoreDetailsActivity.class);
                intent.putExtra("title", listing.getTitle());
                intent.putExtra("description", listing.getDescription());
                intent.putExtra("price", listing.getPrice());
                intent.putExtra("imageUrl", listing.getImageUrl());
                intent.putExtra("phoneNumber", listing.getPhoneNumber());
                intent.putExtra("sellerName", listing.getSellerName());
                intent.putExtra("location", listing.getLocation());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, price;
        ImageView image;

        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);

            title = itemView.findViewById(R.id.textTitle);
            description = itemView.findViewById(R.id.textDescription);
            price = itemView.findViewById(R.id.textPrice);
            image = itemView.findViewById(R.id.imageListing);
        }
    }
}
