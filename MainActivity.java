package com.example.students;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.students.adapter.RecyclerviewAdapter;
import com.example.students.model.StudentEditActivity;
import com.example.students.model.UserData;
import com.melnykov.fab.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView userRecycler;
    RecyclerviewAdapter recyclerviewAdapter;
    EditText searchView;
    ImageView addStudents;
    CharSequence search = "";
    FloatingActionButton addUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.search_bar);
        addUsers = findViewById(R.id.btn_add_users);

        List<UserData> userDataList = new ArrayList<>();
        userDataList.add(new UserData("Layra", "31 year", R.drawable.male_1));
        userDataList.add(new UserData("Irina", "25 year", R.drawable.male_2));
        userDataList.add(new UserData("Jyly", "23 year", R.drawable.male_3));
        userDataList.add(new UserData("Nastia", "31 year", R.drawable.male_4));
        userDataList.add(new UserData("Dasha", "35 year", R.drawable.male_6));
        userDataList.add(new UserData("Ani", "31 year", R.drawable.male_7));
        userDataList.add(new UserData("Inna", "31 year", R.drawable.male_8));
        userDataList.add(new UserData("Margo", "33 year", R.drawable.male_9));
        userDataList.add(new UserData("Diana", "22 year", R.drawable.male_10));
        userDataList.add(new UserData("Nadia", "31 year", R.drawable.male_11));

        setUserRecycler(userDataList);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
        userRecycler.addItemDecoration(dividerItemDecoration);

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }


            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                recyclerviewAdapter.getFilter().filter(charSequence);
                search = charSequence;
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void afterTextChanged(Editable s) {
                onHideKeyboard();
            }
        });

    addUsers.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, StudentEditActivity.class);
            startActivity(intent);
        }
    });

    }

    private void setUserRecycler(List<UserData> userDataList) {
        userRecycler = findViewById(R.id.userRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        userRecycler.setLayoutManager(layoutManager);
        recyclerviewAdapter = new RecyclerviewAdapter(this, userDataList);
        userRecycler.setAdapter(recyclerviewAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onHideKeyboard() {
        InputMethodManager imm;
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(addUsers.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

}