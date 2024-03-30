package com.example.roomdb.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {DbListModel.class}, version = 2, exportSchema = false)
public abstract class DbRoomDatabase extends RoomDatabase {

    public abstract DbDao dbDao();
    private static volatile DbRoomDatabase dbRoomDatabase;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static DbRoomDatabase getDatabase(final Context context) {
        if (dbRoomDatabase == null) {
            synchronized (DbRoomDatabase.class) {
                if (dbRoomDatabase == null) {
                    dbRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                                    DbRoomDatabase.class, "leaderboard_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return dbRoomDatabase;
    }
}