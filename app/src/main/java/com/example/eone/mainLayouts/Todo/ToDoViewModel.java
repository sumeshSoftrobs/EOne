package com.example.eone.mainLayouts.Todo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.eone.database.AppDatabase;
import com.example.eone.database.DBRepository;
import com.example.eone.database.TodoEntity;

import java.util.List;

public class ToDoViewModel extends AndroidViewModel {
    private DBRepository dbRepository;
    AppDatabase db;
    private LiveData<List<TodoEntity>> alltodos;
    public ToDoViewModel(@NonNull Application application) {
        super(application);
        db = Room.databaseBuilder(getApplication(),AppDatabase.class,"Brand").allowMainThreadQueries().build();
        dbRepository = new DBRepository(application);
        alltodos = dbRepository.getAllTodos();
    }
    public void insertTodo(TodoEntity todoEntity){
        dbRepository.insertTodo(todoEntity);
    }
    public  void updateTodo(TodoEntity todoEntity){
        dbRepository.updateTodo(todoEntity);
    }
    public  void deleteTodo(TodoEntity todoEntity){
        dbRepository.deleteTodo(todoEntity);
    }
    public void deleteTodo(long todoId){
        db.todoDao().deleteByTodoId(todoId);
    }

    public LiveData<List<TodoEntity>> getAllTodos() {
        return alltodos;
    }
}