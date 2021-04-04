package com.example.firstandroid;


import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        String imageUrl = "https://i.imgur.com/tGbaZCY.jpg";
        ImageView userPhoto = (ImageView) findViewById(R.id.profilePhoto);
        Picasso.get()
                .load(imageUrl)
                .into(userPhoto);

    }
}