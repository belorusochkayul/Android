package com.example.mystudentapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.melnykov.fab.FloatingActionButton;

import java.util.List;

public class StudentListActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter = new StudentAdapter(this);
    private RecyclerView studentRecycler;
    EditText searchView;
    CharSequence search = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
       searchView = findViewById(R.id.searchView);


        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        List<Student> students = StudentRepository.getInstance().getAll();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
        recycler.addItemDecoration(dividerItemDecoration);

//        searchView.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
//                adapter.getFilter().filter(charSequence);
//                search = charSequence;
//            }
//
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });


        FloatingActionButton add_button = findViewById(R.id.button_add);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(v);
            }
        });
    }

    private void setStudentRecycler(List<Student> studentsList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        studentRecycler.setLayoutManager(layoutManager);
        adapter = new StudentAdapter(this);
        studentRecycler.setAdapter(adapter);
    }

    public void add(View view) {
        Intent intent = new Intent(this.getApplicationContext(), StudentEditActivity.class);
        startActivity(intent);
    }
}

