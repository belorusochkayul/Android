package com.example.students.model;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.students.MainActivity;
import com.example.students.R;

public class StudentEditActivity extends AppCompatActivity {
    EditText editTextName;
    EditText editTextAge;
    EditText editTextImageUrl;
    Button addStudentBtn;
    String studentName;
    String studentAge;
    int studentImageUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_edit);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextImageUrl = findViewById(R.id.editTextImageUrl);
        addStudentBtn = findViewById(R.id.save_button);

//        studentName = editTextName.getText().toString();
//        studentAge = editTextAge.getText().toString();
//        studentImageUrl = Integer.parseInt(editTextImageUrl.getText().toString());
//
//        StudentRepository addNewStudent = new StudentRepository();
//        UserData addStudent = new UserData(studentName,studentAge,studentImageUrl);
//        addNewStudent.addStudent(addStudent);

        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                StudentRepository addNewStudent = new StudentRepository();
//                UserData addStudent = new UserData(studentName,studentAge,studentImageUrl);
//                addNewStudent.addStudent(addStudent);
                Intent intent = new Intent(StudentEditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
