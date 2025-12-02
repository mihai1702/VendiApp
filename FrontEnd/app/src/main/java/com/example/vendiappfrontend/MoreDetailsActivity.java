package com.example.vendiappfrontend;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class MoreDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_more_details);

        ImageView backButton=findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ImageView imageViewToMoreDetails=findViewById(R.id.imageListingToMoreDetails);
        TextView titleTextViewOnMoreDetails=findViewById(R.id.textTitleToMoreDetails);
        TextView descriptionTextViewOnMoreDetails=findViewById(R.id.textDescriptionToMoreDetails);
        TextView priceTextViewToMoreDetails=findViewById(R.id.textPriceToMoreDetails);
        TextView headerTextView=findViewById(R.id.headerTextView);
        TextView phoneNumberTextView=findViewById(R.id.phoneNumberTextView);

        String title=getIntent().getStringExtra("title");
        String description=getIntent().getStringExtra("description");
        double price=getIntent().getDoubleExtra("price", 0);
        String imageURL=getIntent().getStringExtra("imageUrl");
        String phoneNumber=getIntent().getStringExtra("phoneNumber");

        headerTextView.setText(title);
        titleTextViewOnMoreDetails.setText(title);
        descriptionTextViewOnMoreDetails.setText(description);
        priceTextViewToMoreDetails.setText("Price: "+price + " EUR");
        phoneNumberTextView.setText(phoneNumber);

        if (imageURL != null && !imageURL.isEmpty()) {
            Glide.with(this).load(imageURL).into(imageViewToMoreDetails);
        }


        Button callButton=findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });
    }
}