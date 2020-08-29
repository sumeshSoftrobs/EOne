package com.example.eone.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DBRepository {
    //Member Variables//
    private TodoDao todoDao;


    private LiveData<List<TodoEntity>> allTodos;


    //Repository class constructor to access it in any view model//
    public DBRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        todoDao = appDatabase.todoDao();
        allTodos = todoDao.getAllTodo();

    }

    //insert, delete and update methods do database operation in backround//BrandEntity
    public void insertTodo(TodoEntity todoEntity) {
        new InsertTodoAsyncTask(todoDao).execute(todoEntity);
    }

    public void deleteTodo(TodoEntity todoEntity) {
        new DeleteTodoAsyncTask(todoDao).execute(todoEntity);
    }

    public void updateTodo(TodoEntity todoEntity) {
        new UpdateTodoAsyncTask(todoDao).execute(todoEntity);
    }


    public LiveData<List<TodoEntity>> getAllTodos() {
        return allTodos;
    }



    //Insert Async task method
    private static class InsertTodoAsyncTask extends AsyncTask<TodoEntity, Void, Void> {

        private TodoDao todoDao;

        InsertTodoAsyncTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(TodoEntity... todoEntities) {
            todoDao.insertTodo(todoEntities[0]);
            return null;
        }
    }








    private static class DeleteTodoAsyncTask extends AsyncTask<TodoEntity, Void, Void> {
        private TodoDao todoDao;

        DeleteTodoAsyncTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(TodoEntity... todoEntities) {
            todoDao.deleteTodo(todoEntities[0]);
            return null;
        }
    }

    private static class UpdateTodoAsyncTask extends AsyncTask<TodoEntity, Void, Void> {
        private TodoDao todoDao;

        UpdateTodoAsyncTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(TodoEntity... todoEntities) {
            todoDao.updateTodo(todoEntities[0]);
            return null;
        }
    }


}
