package com.example.firstandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button buttonTextChange = (Button) findViewById(R.id.buttonTextExchange);
        Button buttonFlags = (Button) findViewById(R.id.buttonFlags);
        Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
        Button buttonImageDownload = (Button) findViewById(R.id.buttonImage);
        Button buttonProfile = (Button) findViewById(R.id.profilePhoto);

        buttonTextChange.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, TextChangeActivity.class);
                startActivity(intent);
            }
        });
        buttonFlags.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, FlagsActivity.class);
                startActivity(intent);
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, LoginLinearActivity.class);
                startActivity(intent);
            }
        });
        buttonImageDownload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ImageActivity.class);
                startActivity(intent);
            }
        });

        buttonProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

    }
}