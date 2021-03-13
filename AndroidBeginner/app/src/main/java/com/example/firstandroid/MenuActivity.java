package com.example.firstandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MenuActivity extends Activity {
    Button buttonTextChange, buttonFlags, buttonNextTask;
    ScrollView scrollView;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        buttonTextChange = (Button) findViewById(R.id.buttonTextExchange);
        linearLayout = new LinearLayout(this);
        scrollView = new ScrollView(this);

        buttonTextChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonTextExchange: {
                        Intent intent = new Intent(MenuActivity.this, TextChangeActivity.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
    }
}
