package com.example.budgetapp.DataManager.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budgetapp.DataManager.Model.GoalDetail;

import java.util.List;
/**
 * GoalDetailDao
 * DAO interface for GoalDetail entity.
 * Contains Insert, Update, Delete and Queries necessary for implementation
 */

@Dao
public interface GoalDetailDao {

    // DAO Queries for GoalDetail

    @Insert
    void insertGoalDetail(GoalDetail goalDetail);

    @Update
    void updateGoalDetail(GoalDetail goalDetail);

    @Delete
    void deleteGoalDetail(GoalDetail goalDetail);

    // Returns all Goal Details
    @Query("Select * From GoalDetail Order By id DESC")
    LiveData<List<GoalDetail>>getAllGoalDetail();

    // Returns a specific Goal Detail
    @Query("Select * From GoalDetail WHERE versionName = :verName and goalName = :gName and accountGroupName = :agName")
    GoalDetail getGoalDetail(String verName, String gName, String agName);


}
