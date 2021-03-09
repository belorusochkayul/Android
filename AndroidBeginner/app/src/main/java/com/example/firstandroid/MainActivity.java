package com.example.firstandroid;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView1 = (TextView) findViewById(R.id.textView);
        final Button btn = (Button) findViewById(R.id.button);
        final TextView textView2 = (TextView) findViewById(R.id.textView2);

        btn.setOnClickListener(this);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final TextView textView1 = (TextView) findViewById(R.id.textView);
        final TextView textView2 = (TextView) findViewById(R.id.textView2);
        final Button btn = (Button) findViewById(R.id.button);

        String text1 = (String) textView1.getText();
        String text2 = (String) textView2.getText();

        int color1 = Color.TRANSPARENT;
        int color2 = Color.TRANSPARENT;

        Drawable background1 = textView1.getBackground();
        Drawable background2 = textView2.getBackground();

        color1 = getBackgroundColorFromTextView(background1, color1);
        color2 = getBackgroundColorFromTextView(background2, color2);

        if (v.getId() == R.id.button || v.getId() == R.id.textView || v.getId() == R.id.textView2) {
            changeTextColor(textView1, textView2, text1, text2);
            changeBackgroundColor(textView1, textView2, color1, color2);
        }
    }

    public void changeTextColor(TextView textView1, TextView textView2, String text1, String text2) {
        textView1.setText(text2);
        textView2.setText(text1);
    }

    public void changeBackgroundColor(TextView textView1, TextView textView2, int color1, int color2) {
        textView1.setBackgroundColor(color2);
        textView2.setBackgroundColor(color1);
    }

    public int getBackgroundColorFromTextView(Drawable background, int color) {
        if (background instanceof ColorDrawable) {
            color = ((ColorDrawable) background).getColor();
        }
        return color;
    }
}