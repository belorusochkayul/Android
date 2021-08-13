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
        Student item = StudentRepository.getStudent().get(index);

        TextView name = findViewById(R.id.name);
        name.setText(item.getName());

        TextView desc = findViewById(R.id.desc);
        desc.setText(item.getDesc());

        ImageView userImageView = findViewById(R.id.userImageView);

        ImageButton editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener((View v) -> {
                    Intent intent = new Intent(this.getApplicationContext(), StudentEditActivity.class);
                    intent.putExtra("edit", true);
                    intent.putExtra("index", index);
                    intent.putExtra("name", StudentRepository.getStudent().get(index).getName());
                    intent.putExtra("desc", StudentRepository.getStudent().get(index).getDesc());
                    startActivity(intent);
                }
        );

        Button delete = findViewById(R.id.delete);
        delete.setOnClickListener((View v) -> {

            Intent intent = new Intent(this.getApplicationContext(), StudentEditActivity.class);
            intent.putExtra("index", index);
            intent.putExtra("delete", StudentRepository.getStudent().getAll().remove(item));
            Intent intent1 = new Intent(this.getApplicationContext(), StudentListActivity.class);
            startActivity(intent1);
        });
    }

//   public void onDawnloadImg(){
//        String userInputUrl = urlEditText.getText().toString();
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
//                    }
//
//                });
//   }
}
