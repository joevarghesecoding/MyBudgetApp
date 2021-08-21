package com.example.budgetapp.DataManager.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budgetapp.DataManager.Model.Goal;
import java.util.List;

/**
 * GoalDao
 * DAO interface for Goal entity.
 * Contains Insert, Update, Delete and Queries necessary for implementation
 */

@Dao
public interface GoalDao {

    // DAO Queries for Goal

    @Insert
    void insertGoal(Goal goal);

    @Update
    void updateGoal(Goal goal);

    @Delete
    void deleteGoal(Goal goal);

    // Query to return all Goals
    @Query("Select * From Goal Order By id DESC")
    LiveData<List<Goal>> getAllGoal();

    // Query to return a specific Goal
    @Query("Select * From Goal Where goalName = :gName")
    Goal getGoal(String gName);

}

