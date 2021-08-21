package com.example.budgetapp.DataManager.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * GoalDetail
 * Entity and Transport object for GoalDetail table
 */
@Entity
public class GoalDetail {
    @PrimaryKey (autoGenerate = true)
    int id;
    String goalName;            // Goal Name
    String accountGroupName;    // Account Group (Type of Spending or Balance)
    String versionName;         // Version name
    double amount;              // Amount

    /**
     * Constructor for GoalDetail
     * @param goalName
     * @param accountGroupName
     * @param amount
     * @param versionName
     */
    public GoalDetail(String goalName, String accountGroupName, double amount, String versionName) {
        this.goalName = goalName;
        this.accountGroupName = accountGroupName;
        this.amount = amount;
        this.versionName = versionName;
    }

    // Get and Set methods for each field

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public String getAccountGroupName() {
        return accountGroupName;
    }

    public void setAccountGroupName(String accountGroupName) {
        this.accountGroupName = accountGroupName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
}

