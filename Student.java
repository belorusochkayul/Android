package com.example.students;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Student extends AppCompatActivity {


    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        username = findViewById(R.id.user_name);

        String s = getIntent().getStringExtra("username");
        String userdescr = getIntent().getStringExtra("userDesc");
        username.setText(s + "" + userdescr);

    }
}