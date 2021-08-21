package com.example.budgetapp.DataManager.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * VersionEntry
 * Entity and Transport object for VersionEntry table
 */
@Entity
public class VersionEntry {
    @PrimaryKey (autoGenerate = true)
    int id;
    String versionName;         // Version Name
    String accountClassName;    // Account Class (Revenue or Expense)
    String accountGroupName;    // Account Group (Type of Spending or Balance)
    String accountTypeName;     // Account Type (Specific account type, such as Entertainment, Loans, etc..)
    String entryName;           // Name of the entry
    String entryDesc;           // Optional description
    double entryAmount;         // Amount of the entry

    /**
     * Constructor for VersionEntry
     * @param versionName
     * @param accountClassName
     * @param accountGroupName
     * @param accountTypeName
     * @param entryName
     * @param entryDesc
     * @param entryAmount
     */
    public VersionEntry(String versionName, String accountClassName, String accountGroupName,String accountTypeName, String entryName, String entryDesc, double entryAmount) {
        this.versionName = versionName;
        this.accountClassName = accountClassName;
        this.accountGroupName = accountGroupName;
        this.accountTypeName = accountTypeName;
        this.entryName = entryName;
        this.entryDesc = entryDesc;
        this.entryAmount = entryAmount;
    }

    // Get and Set methods for each field

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public String getEntryDesc() {
        return entryDesc;
    }

    public void setEntryDesc(String entryDesc) {
        this.entryDesc = entryDesc;
    }

    public double getEntryAmount() {
        return entryAmount;
    }

    public void setEntryAmount(double entryAmount) {
        this.entryAmount = entryAmount;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public String getAccountClassName() {
        return accountClassName;
    }

    public void setAccountClassName(String accountClassName) {
        this.accountClassName = accountClassName;
    }

    public String getAccountGroupName() {
        return accountGroupName;
    }

    public void setAccountGroupName(String accountGroupName) {
        this.accountGroupName = accountGroupName;
    }
}

