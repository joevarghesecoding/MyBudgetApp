package com.example.budgetapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.budgetapp.DataManager.Database.Repository;
import com.example.budgetapp.DataManager.Model.GoalDetail;

import java.util.List;

/**
 * GoalDetailViewModel
 * Functions from Repository for GoalDetailViewModel
 */

public class GoalDetailViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<GoalDetail>> allGoalDetail;

    /**
     * Constructor for GoalDetailViewModel
     * @param application
     */
    public GoalDetailViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        this.allGoalDetail = repository.getAllGoalDetail();
    }

    /* GoalDetail Data Queries */
    public LiveData<List<GoalDetail>> getAllGoalDetail() {
        return allGoalDetail;
    }

    public GoalDetail getGoalDetail(String verName, String gName, String agName){
        return repository.getGoalDetail(verName, gName, agName);
    }

    /* GoalDetail Insert */
    public void insertGoalDetail(GoalDetail goalDetail) {
        repository.insertGoalDetail(goalDetail);
    }

    /* GoalDetail Update */
    public void updateGoalDetail(GoalDetail goalDetail) {
        repository.updateGoalDetail(goalDetail);
    }

    /* GoalDetail Delete */
    public void deleteGoalDetail(GoalDetail goalDetail) {
        repository.deleteGoalDetail(goalDetail);
    }


}


