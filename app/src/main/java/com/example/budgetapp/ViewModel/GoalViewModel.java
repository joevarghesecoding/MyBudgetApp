package com.example.budgetapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.budgetapp.DataManager.Database.Repository;
import com.example.budgetapp.DataManager.Model.Goal;

import java.util.List;

/**
 * GoalViewModel
 * Functions from Repository for GoalViewModel
 */

public class GoalViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Goal>> allGoal;

    /**
     * Constructor for GoalViewModel
     * @param application
     */
    public GoalViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        this.allGoal = repository.getAllGoal();
    }

    /* Goal Data Queries */
    public LiveData<List<Goal>> getAllGoal() {
        return allGoal;
    }

    public Goal getGoal(String gName){
        return repository.getGoal(gName);
    }

    /* Goal Insert */
    public void insertGoal(Goal goal) {
        repository.insertGoal(goal);
    }

    /* Goal Update */
    public void updateGoal(Goal goal) {
        repository.updateGoal(goal);
    }

    /* Goal Delete */
    public void deleteGoal(Goal goal) {
        repository.deleteGoal(goal);
    }

}

