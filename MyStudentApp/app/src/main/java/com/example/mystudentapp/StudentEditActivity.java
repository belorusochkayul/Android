package com.example.mystudentapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StudentEditActivity extends AppCompatActivity {
    boolean value;
    int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_edit);

        this.value = getIntent().getBooleanExtra("edit", false);
        if (value) {
            EditText name = this.findViewById(R.id.name);
            EditText desc = this.findViewById(R.id.desc);
            name.setText(getIntent().getStringExtra("name"));
            desc.setText(getIntent().getStringExtra("desc"));
        }
        this.index = getIntent().getIntExtra("index", 0);
    }

    public void save(View view) {
        EditText name = this.findViewById(R.id.name);
        EditText desc = this.findViewById(R.id.desc);

        if (value) {
            Student item = StudentRepository.getStudent().get(index);
            item.setName(name.getText().toString());
            item.setDesc(desc.getText().toString());
            StudentRepository.getStudent().replace(index, item);
        } else {
            Student item = new Student(name.getText().toString(), desc.getText().toString());
            StudentRepository.getStudent().add(item);
        }

        Intent intent = new Intent(this.getApplicationContext(), StudentListActivity.class);
        startActivity(intent);
    }
}
