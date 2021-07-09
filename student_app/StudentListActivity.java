package com.example.student_app;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student_app.adapter.RecyclerViewAdapter;
import com.example.student_app.model.UserData;

import java.util.ArrayList;
import java.util.List;

public class StudentListActivity extends AppCompatActivity {
    RecyclerView userRecycle;
    RecyclerViewAdapter recyclerViewAdapter;
    EditText searchView;
    CharSequence search = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        searchView = findViewById(R.id.search_bar);

        List<UserData> userDAtaList = new ArrayList<>();
        userDAtaList.add(new UserData("Jylu", 24, R.drawable.photo_female_1));
        userDAtaList.add(new UserData("Nadia", 23, R.drawable.photo_female_2));
        userDAtaList.add(new UserData("Iren", 18, R.drawable.photo_female_3));
        userDAtaList.add(new UserData("Dasha", 19, R.drawable.photo_female_4));
        userDAtaList.add(new UserData("Sasha", 22, R.drawable.photo_female_5));

        setUserRecycle(userDAtaList);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                recyclerViewAdapter.getFilter().filter(charSequence);
                search = charSequence;
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void setUserRecycle(List<UserData> userDataList) {
        userRecycle = findViewById(R.id.userRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        userRecycle.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(this, userDataList);
        userRecycle.setAdapter(recyclerViewAdapter);
    }
}
