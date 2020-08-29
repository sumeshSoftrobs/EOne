package com.example.eone.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo")
public class TodoEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "todo_id")
    private long todoId;
    @ColumnInfo(name = "todo_title")
    private String todoTitle;
    @ColumnInfo(name = "todo_desc")
    private String todoDesc;
    @ColumnInfo(name = "todo_date")
    private String todoDate;
    @ColumnInfo(name = "todo_time")
    private String todoTime;
    @ColumnInfo(name = "todo_priority")
    private String todoPriority;

    public TodoEntity() {

    }

    public TodoEntity(String todoTitle, String todoDesc, String todoDate, String todoTime, String todoPriority) {
        this.todoTitle = todoTitle;
        this.todoDesc = todoDesc;
        this.todoDate = todoDate;
        this.todoTime = todoTime;
        this.todoPriority = todoPriority;
    }

    public long getTodoId() {
        return todoId;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public String getTodoDesc() {
        return todoDesc;
    }

    public String getTodoDate() {
        return todoDate;
    }

    public String getTodoTime() {
        return todoTime;
    }

    public String getTodoPriority() {
        return todoPriority;
    }

    public void setTodoId(long todoId) {
        this.todoId = todoId;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public void setTodoDesc(String todoDesc) {
        this.todoDesc = todoDesc;
    }

    public void setTodoDate(String todoDate) {
        this.todoDate = todoDate;
    }

    public void setTodoTime(String todoTime) {
        this.todoTime = todoTime;
    }

    public void setTodoPriority(String todoPriority) {
        this.todoPriority = todoPriority;
    }
}
