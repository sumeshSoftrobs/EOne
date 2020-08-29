package com.example.eone.mainLayouts.Todo;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eone.MainActivity;
import com.example.eone.R;
import com.example.eone.database.DBRepository;
import com.example.eone.database.TodoEntity;
import com.example.eone.mainLayouts.Todo.addTodo.AddTodoActivity;
import com.example.eone.mainLayouts.Todo.addTodo.TodoRecyclerAdapter;
import com.example.eone.mainLayouts.Todo.updateTodo.UpdateTodoActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ToDoFragment extends Fragment {

    private ToDoViewModel mViewModel;
    private FloatingActionButton todoFab;
    private RecyclerView todoRecycler;
    private TodoRecyclerAdapter adapter;
    List<TodoEntity> allTodos = new ArrayList<>();
    DBRepository dbRepository;
    public static ToDoFragment newInstance() {
        return new ToDoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.to_do_fragment, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbRepository = new DBRepository(getActivity().getApplication());;
        adapter = new TodoRecyclerAdapter(getContext(),dbRepository);
        todoFab = (FloatingActionButton)view.findViewById(R.id.fabTodo);
        todoRecycler = (RecyclerView)view.findViewById(R.id.todoRecycler);
        todoRecycler.setHasFixedSize(true);
        todoRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        todoRecycler.setAdapter(adapter);
        todoFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddTodoActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ToDoViewModel.class);
        mViewModel.getAllTodos().observe(this, new Observer<List<TodoEntity>>() {
            @Override
            public void onChanged(List<TodoEntity> todoEntities) {
                adapter.setTodos(todoEntities);
                allTodos = todoEntities;
            }
        });
        adapter.setOnItemClickListener(new TodoRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(TodoEntity todoEntity) {
               Intent i = new Intent(getContext(), UpdateTodoActivity.class);
                i.putExtra("title",todoEntity.getTodoTitle());
                i.putExtra("desc",todoEntity.getTodoDesc());
                i.putExtra("date",todoEntity.getTodoDate());
                i.putExtra("time",todoEntity.getTodoTime());
                i.putExtra("id",todoEntity.getTodoId());

                startActivity(i);
            }
        });


    }


}