package com.example.eone.mainLayouts.Todo.addTodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.eone.R;

public class AddTodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Productivity");
        setContentView(R.layout.activity_add_todo);
    }
}