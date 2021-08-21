package com.example.budgetapp.DataManager.Database.Dao;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budgetapp.DataManager.Model.VersionEntry;

import java.util.List;

/**
 * VersionEntryDao
 * DAO interface for VersionEntry entity.
 * Contains Insert, Update, Delete and Queries necessary for implementation
 */

@Dao
public interface VersionEntryDao {
    //DAO Queries for VersionEntry

    @Insert
    void insertVersionEntry(VersionEntry versionEntry);

    @Update
    void updateVersionEntry(VersionEntry versionEntry);

    @Delete
    void deleteVersionEntry(VersionEntry versionEntry);

    // Returns all VersionEntry objects
    @Query("Select * From VersionEntry Order By id DESC")
    LiveData<List<VersionEntry>> getAllVersionEntry();

    // Returns VersionEntry objects for a specified versionName and accountClassName
    @Query("Select * From VersionEntry Where versionName = :versionName and accountClassName = :accountClassName Order By id DESC")
    LiveData<List<VersionEntry>> getVersionEntryClass(String versionName, String accountClassName);

    // Returns VersionEntry objects for a specified versionName and summarized to the same level as GoalDetail
//    @Query("Select versionName,  accountClassName, null As accountTypeName, null As entryName, null as entryDesc, Sum(entryAmount) As entryAmount from VersionEntry Where versionName = :verName Group By versionName,  accountClassName")
//    LiveData<List<VersionEntry>> getVersionEntrySummary(String verName);

    @Query("Select * From VersionEntry Where versionName = :verName and entryName = :eName")
    VersionEntry getVersionEntry(String verName, String eName);

}
