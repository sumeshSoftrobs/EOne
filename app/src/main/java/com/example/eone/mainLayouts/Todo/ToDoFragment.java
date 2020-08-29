package com.example.eone.mainLayouts.Todo;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eone.MainActivity;
import com.example.eone.R;
import com.example.eone.mainLayouts.Todo.addTodo.AddTodoActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ToDoFragment extends Fragment {

    private ToDoViewModel mViewModel;
    private FloatingActionButton todoFab;
    public static ToDoFragment newInstance() {
        return new ToDoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.to_do_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ToDoViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        todoFab = (FloatingActionButton)view.findViewById(R.id.fabTodo);
        todoFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddTodoActivity.class);
                startActivity(i);
            }
        });
    }
}