package com.example.roomdb.DB;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DbRepository {
    DbRoomDatabase dbRoomDatabase;
    DbDao dbDao;
    private LiveData<List<DbListModel>> db_model;
    private ExecutorService executor;


    public DbRepository(Application application) {
        dbRoomDatabase = DbRoomDatabase.getDatabase(application);
        dbDao = dbRoomDatabase.dbDao();
        db_model = dbDao.getdbModel();
        executor = Executors.newSingleThreadExecutor();
    }

    public void insertData(List<DbListModel> db_model) {
        dbRoomDatabase.databaseWriteExecutor.execute(() -> dbDao.insert(db_model));
    }


    // Method to replace text asynchronously
    public void replaceText(String matchingText, String replacementText) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dbDao.replaceText(matchingText, replacementText);
            }
        });
    }

    public void replaceText2(String matchingText2, String replacementText2) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dbDao.replaceText2(matchingText2, replacementText2);
            }
        });

    }

    public void replaceText3(String matchingText3, String replacementText3) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dbDao.replaceText3(matchingText3, replacementText3);
            }
        });

    }

    public void replaceText4(String matchingText4, String replacementText4) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dbDao.replaceText4(matchingText4, replacementText4);
            }
        });

    }

    public void replaceText5(String matchingText5, String replacementText5) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dbDao.replaceText5(matchingText5, replacementText5);
            }
        });

    }

    public void replaceText6(String matchingText6, String replacementText6) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dbDao.replaceText6(matchingText6, replacementText6);
            }
        });

    }

    public void replaceText7(String matchingText7, String replacementText7) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dbDao.replaceText7(matchingText7, replacementText7);
            }
        });

    }

    public void replaceText8(String matchingText8, String replacementText8) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dbDao.replaceText8(matchingText8, replacementText8);
            }
        });

    }

    public void replaceText9(String matchingText9, String replacementText9) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dbDao.replaceText9(matchingText9, replacementText9);
            }
        });

    }

    public void replaceText10(String matchingText10, String replacementText10) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dbDao.replaceText10(matchingText10, replacementText10);
            }
        });

    }

    public void replaceText11(String matchingText11, String replacementText11) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dbDao.replaceText11(matchingText11, replacementText11);
            }
        });

    }

    public LiveData<List<DbListModel>> getAllData() {
        return db_model;
    }
}
