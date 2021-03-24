package com.example.firstandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button buttonTextChange = (Button) findViewById(R.id.buttonTextExchange);
        Button buttonFlags = (Button) findViewById(R.id.buttonFlags);
        Button buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonTextChange.setOnClickListener(this);
        buttonFlags.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);
            }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonTextExchange: {
                Intent intent = new Intent(MenuActivity.this, TextChangeActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.buttonFlags: {
                Intent intent = new Intent(MenuActivity.this, FlagsActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.buttonLogin: {
                Intent intent = new Intent(MenuActivity.this, LoginLinearActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}