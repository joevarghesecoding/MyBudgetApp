package com.example.budgetapp.DataManager.Model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Account Group
 * Entity and Transport object for Account Group table
 */

@Entity
public class AccountGroup {
    @PrimaryKey (autoGenerate = true)
    int id;
    String accountGroupName;    // Account Group

    /**
     * Constructor for Account Group
     * @param accountGroupName
     */
    public AccountGroup(String accountGroupName) {
        this.accountGroupName = accountGroupName;
    }

    // Get and Set methods for Account Group

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountGroupName() {
        return accountGroupName;
    }

    public void setAccountGroupName(String accountGroupName) {
        this.accountGroupName = accountGroupName;
    }
}

