package com.example.budgetapp.DataManager.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budgetapp.DataManager.Model.Fact;

import java.util.List;

/**
 * FactDao
 * DAO interface for Fact entity.
 * Contains Insert, Update, Delete and Queries necessary for implementation
 */

@Dao
public interface FactDao {

    // DAO Queries for Fact

    @Insert
    void insertFact(Fact fact);

    @Update
    void updateFact(Fact fact);

    @Delete
    void deleteFact(Fact fact);

    // Query to return all Fact
    @Query("Select * From Fact Order By id DESC")
    LiveData<List<Fact>> getAllFact();

    // Query to return Fact for a given Version
    @Query("Select * From Fact Where versionName = :verName Order By id DESC")
    LiveData<List<Fact>> getFactByVer(String verName);

    // Query to return Fact for a given Version and Summarize by AccountGroup
//    @Query("Select versionName,  accountGroupName, null as accountTypeName, Sum(p1) As p1, Sum(p2) as p2, Sum(p3) as P3, Sum(p4) as p4, Sum(totalSemester) as totalSemester from Fact Where versionName = :verName Group By versionName,  accountGroupName")
//    LiveData<List<Fact>> getFactSum(String verName);

}
