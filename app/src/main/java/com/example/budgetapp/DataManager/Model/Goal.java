package com.example.budgetapp.DataManager.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Goal
 * Entity and Transport object for Goal table
 */
@Entity
public class Goal {
    @PrimaryKey (autoGenerate = true)
    int id;
    String goalName; // Goal Name

    /**
     * Constructor for Goal
     * @param goalName
     */
    public Goal(String goalName) {
        this.goalName = goalName;
    }

    // Get and Set Methods for each field

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

}

