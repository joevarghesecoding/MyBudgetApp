package com.example.budgetapp.DataManager.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budgetapp.DataManager.Model.Version;

import java.util.List;

/**
 * VersionDao
 * DAO interface for Version entity.
 * Contains Insert, Update, Delete and Queries necessary for implementation
 */

@Dao
public interface VersionDao {

    // DAO Queries for Version

    @Insert
    void insertVersion(Version version);

    @Update
    void updateVersion(Version version);

    @Delete
    void deleteVersion(Version version);

    // Query to return all Versions
    @Query("Select * From Version Order By id DESC")
    LiveData<List<Version>> getAllVersion();

    // Query to return a single Version
    @Query("Select * From Version Where versionName = :verName")
    Version getVersion(String verName);
}

