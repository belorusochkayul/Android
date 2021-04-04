package com.example.firstandroid;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TextChangeActivity extends Activity implements View.OnClickListener {
    private TextView textView1;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textchange);

        textView1 = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);

        findViewById(R.id.button).setOnClickListener(this);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String text1 = (String) textView1.getText();
        String text2 = (String) textView2.getText();

        int color1 = Color.TRANSPARENT;
        int color2 = Color.TRANSPARENT;

        Drawable background1 = textView1.getBackground();
        Drawable background2 = textView2.getBackground();

        color1 = getBackgroundColorFromTextView(background1, color1);
        color2 = getBackgroundColorFromTextView(background2, color2);

        changeTextColor(text1, text2);
        changeBackgroundColor(color1, color2);

           }

    public void changeTextColor(String text1, String text2) {
        textView1.setText(text2);
        textView2.setText(text1);
    }

    public void changeBackgroundColor( int color1, int color2) {
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