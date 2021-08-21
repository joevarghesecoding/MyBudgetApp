package com.example.budgetapp.DataManager.Model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Account Type
 * Entity and Transport object for Account Type table
 */
@Entity
public class AccountType {
    @PrimaryKey (autoGenerate = true)
    int id;
    String accountTypeName;     // Account Type
    String accountGroupName;    // Account Group
    String accountClassName;    // Account Class
    char creditSign;            // Credit Sign (+ for revenue, - for expense)
    int creditSignMultiplier;   // Multiplier (1 for revenue, -1 for expense)

    /**
     * Constructor for Account Type
     * @param accountTypeName
     * @param accountGroupName
     * @param accountClassName
     * @param creditSign
     * @param creditSignMultiplier
     */
    public AccountType(String accountTypeName, String accountGroupName, String accountClassName, char creditSign, int creditSignMultiplier) {
        this.accountTypeName = accountTypeName;
        this.accountGroupName = accountGroupName;
        this.accountClassName = accountClassName;
        this.creditSign = creditSign;
        this.creditSignMultiplier = creditSignMultiplier;
    }

    // Get and Set methods for Account Type
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public char getCreditSign() {
        return creditSign;
    }

    public void setCreditSign(char creditSign) {
        this.creditSign = creditSign;
    }

    public int getCreditSignMultiplier() {
        return creditSignMultiplier;
    }

    public void setCreditSignMultiplier(int creditSignMultiplier) {
        this.creditSignMultiplier = creditSignMultiplier;
    }

    public String getAccountGroupName() {
        return accountGroupName;
    }

    public void setAccountGroupName(String accountGroupName) {
        this.accountGroupName = accountGroupName;
    }

    public String getAccountClassName() {
        return accountClassName;
    }

    public void setAccountClassName(String accountClassName) {
        this.accountClassName = accountClassName;
    }
}

