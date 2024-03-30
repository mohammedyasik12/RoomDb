package com.example.roomdb.DB;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class DbViewModel extends AndroidViewModel {
    private DbRepository dbRepository;
    private LiveData<List<DbListModel>> db_model;

    private final MutableLiveData<Boolean> updateResultLiveData = new MutableLiveData<>();


    public DbViewModel(Application application) {
        super(application);
        dbRepository = new DbRepository(application);
        db_model = dbRepository.getAllData();
    }

    public LiveData<List<DbListModel>> getAllLeaderBoardFromDB() {
        return db_model;
    }

    public void insertData(List<DbListModel> dbListModels) {
        dbRepository.insertData(dbListModels);
    }

    public void replaceText(String matchingText, String replacementText) {
        dbRepository.replaceText(matchingText, replacementText);
    }

    public void replaceText2(String matchingText2, String replacementText2) {
        dbRepository.replaceText2(matchingText2, replacementText2);
    }

    public void replaceText3(String matchingText3, String replacementText3) {
        dbRepository.replaceText3(matchingText3, replacementText3);
    }

    public void replaceText4(String matchingText4, String replacementText4) {
        dbRepository.replaceText4(matchingText4, replacementText4);
    }

    public void replaceText5(String matchingText5, String replacementText5) {
        dbRepository.replaceText5(matchingText5, replacementText5);
    }

    public void replaceText6(String matchingText6, String replacementText6) {
        dbRepository.replaceText6(matchingText6, replacementText6);
    }

    public void replaceText7(String matchingText7, String replacementText7) {
        dbRepository.replaceText7(matchingText7, replacementText7);
    }

    public void replaceText8(String matchingText8, String replacementText8) {
        dbRepository.replaceText8(matchingText8, replacementText8);
    }

    public void replaceText9(String matchingText9, String replacementText9) {
        dbRepository.replaceText9(matchingText9, replacementText9);
    }

    public void replaceText10(String matchingText10, String replacementText10) {
        dbRepository.replaceText10(matchingText10, replacementText10);
    }

    public void replaceText11(String matchingText11, String replacementText11) {
        dbRepository.replaceText11(matchingText11, replacementText11);
    }



}

