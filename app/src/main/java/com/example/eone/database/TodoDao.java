package com.example.eone.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {
    @Query("SELECT * FROM todo ORDER BY todo_time DESC")
    LiveData<List<TodoEntity>> getAllTodo();

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    public void insertTodo(TodoEntity todoEntity);
    @Update
    public void updateTodo(TodoEntity todoEntity);
    @Delete
    public void deleteTodo(TodoEntity todoEntity);
    //Deleting from join DATA//
    @Query("DELETE FROM todo WHERE todo.todo_id = :todoId")
    public void deleteByTodoId(long todoId);

}
