package com.example.eone.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {TodoEntity.class}, version = 1)
public abstract class  AppDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "Brand")
                            .allowMainThreadQueries()
                            .build();

                }
            }
        }
        return INSTANCE;
    }

}
