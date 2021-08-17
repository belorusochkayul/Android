package com.example.mystudentapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StudentEditActivity extends AppCompatActivity {
    private int index;
    EditText name, desc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_edit);
        name = this.findViewById(R.id.name);
        desc = this.findViewById(R.id.desc);
        //url
        this.index = getIntent().getIntExtra("index", -1);
        if (index != -1) {
            Student item = StudentRepository.getInstance().get(index);
            name.setText(item.getName());
            desc.setText(item.getDesc());
            //  url
        }

        Button buttonSave = this.findViewById(R.id.save);
        buttonSave.setOnClickListener(v -> save(v));
    }


    public void save(View view) {
        if (index != -1) {
            Student item = StudentRepository.getInstance().get(index);
            item.setName(name.getText().toString());
            item.setDesc(desc.getText().toString());
            StudentRepository.getInstance().replace(index, item);
        } else {
            Student item = new Student(name.getText().toString(), desc.getText().toString());
            StudentRepository.getInstance().add(item);
        }
        finish();
    }
}
