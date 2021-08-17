package com.example.mystudentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StudentDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        int index = getIntent().getIntExtra("index", 0);
        Student item = StudentRepository.getInstance().get(index);

        TextView name = findViewById(R.id.name);
        name.setText(item.getName());

        TextView desc = findViewById(R.id.desc);
        desc.setText(item.getDesc());

        ImageView userImageView = findViewById(R.id.userImageView);

        ImageButton editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener((View v) -> {
                    Intent intent = new Intent(this.getApplicationContext(), StudentEditActivity.class);
                    StudentRepository.getInstance().get(index);



                    startActivity(intent);
                }
        );

        Button delete = findViewById(R.id.delete);
        delete.setOnClickListener((View v) -> {
            Intent intent = new Intent(this.getApplicationContext(), StudentEditActivity.class);
            StudentRepository.getInstance().getAll().remove(item);
            StudentDetailsActivity.this.finish();

        });
    }

//   public void onDawnloadImg(){
//       Student item = StudentRepository.getInstance().get(index);
//        Picasso
//                .get()
//                .load(userInputUrl)
//                .error(R.drawable.ic_launcher_foreground)
//                .transform(new PicassoCircleTransformation())
//                .into(userImageview,new Callback(){
//                    @Override
//                    public void onSuccess() {
//                    }
//                    @Override
//                    public void onError(Exception e) {
//                });
//   }
}
