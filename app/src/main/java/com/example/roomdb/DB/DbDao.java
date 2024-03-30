package com.example.roomdb.DB;

import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;
import java.util.Map;

@Dao
public interface DbDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<DbListModel> dbModels);

    @Query("SELECT * from leaderboard_db ORDER By dbplayerPoints1+dbplayerPoints2+dbplayerPoints3+dbplayerPoints4+dbplayerPoints5+dbplayerPoints6+dbplayerPoints7+dbplayerPoints8+dbplayerPoints9+dbplayerPoints10+dbplayerPoints11 DESC")
    LiveData<List<DbListModel>> getdbModel();


    @Query("UPDATE leaderboard_db SET dbplayerPoints1 = :replacementText WHERE dbplayerName1 = :matchingText")
    void replaceText(String matchingText, String replacementText);

    @Query("UPDATE leaderboard_db SET dbplayerPoints2 = :replacementText2 WHERE dbplayerName2 = :matchingText2")
    void replaceText2(String matchingText2, String replacementText2);

    @Query("UPDATE leaderboard_db SET dbplayerPoints3 = :replacementText3 WHERE dbplayerName3 = :matchingText3")
    void replaceText3(String matchingText3, String replacementText3);

    @Query("UPDATE leaderboard_db SET dbplayerPoints4 = :replacementText4 WHERE dbplayerName4 = :matchingText4")
    void replaceText4(String matchingText4, String replacementText4);

    @Query("UPDATE leaderboard_db SET dbplayerPoints5 = :replacementText5 WHERE dbplayerName5 = :matchingText5")
    void replaceText5(String matchingText5, String replacementText5);

    @Query("UPDATE leaderboard_db SET dbplayerPoints6 = :replacementText6 WHERE dbplayerName6 = :matchingText6")
    void replaceText6(String matchingText6, String replacementText6);

    @Query("UPDATE leaderboard_db SET dbplayerPoints7 = :replacementText7 WHERE dbplayerName7 = :matchingText7")
    void replaceText7(String matchingText7, String replacementText7);

    @Query("UPDATE leaderboard_db SET dbplayerPoints8 = :replacementText8 WHERE dbplayerName8 = :matchingText8")
    void replaceText8(String matchingText8, String replacementText8);

    @Query("UPDATE leaderboard_db SET dbplayerPoints9 = :replacementText9 WHERE dbplayerName9 = :matchingText9")
    void replaceText9(String matchingText9, String replacementText9);

    @Query("UPDATE leaderboard_db SET dbplayerPoints10 = :replacementText10 WHERE dbplayerName10 = :matchingText10")
    void replaceText10(String matchingText10, String replacementText10);

    @Query("UPDATE leaderboard_db SET dbplayerPoints11 = :replacementText11 WHERE dbplayerName11 = :matchingText11")
    void replaceText11(String matchingText11, String replacementText11);





}
