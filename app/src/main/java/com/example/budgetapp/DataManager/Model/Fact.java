package com.example.budgetapp.DataManager.Model;

import android.accounts.Account;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Fact
 * Entity and Transport object for Fact table
 */
@Entity
public class Fact {
    @PrimaryKey (autoGenerate = true)
    int id;
    String versionName;         // Version Name
    String accountGroupName;    // Account Group
    String accountTypeName;     // Account Type
    double p1;                  //semesters
    double p2;                  //semesters
    double p3;                  //semesters
    double p4;                  //semesters
    double totalSemester;       //total semesters

    /**
     * Constructor for Fact
     * @param versionName
     * @param accountGroupName
     * @param accountTypeName
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @param totalSemester
     */
    public Fact(String versionName, String accountGroupName, String accountTypeName, double p1, double p2, double p3, double p4, double totalSemester) {
        this.versionName = versionName;
        this.accountGroupName = accountGroupName;
        this.accountTypeName = accountTypeName;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.totalSemester = totalSemester;
    }

    // Get and Set methods for Fact

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getP1() {
        return p1;
    }

    public void setP1(double p1) {
        this.p1 = p1;
    }

    public double getP2() {
        return p2;
    }

    public void setP2(double p2) {
        this.p2 = p2;
    }

    public double getP3() {
        return p3;
    }

    public void setP3(double p3) {
        this.p3 = p3;
    }

    public double getP4() {
        return p4;
    }

    public void setP4(double p4) {
        this.p4 = p4;
    }

    public double getTotalSemester() {
        return totalSemester;
    }

    public void setTotalSemester(double ts) {
        this.totalSemester = ts;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getAccountGroupName() {
        return accountGroupName;
    }

    public void setAccountGroupName(String accountGroupName) {
        this.accountGroupName = accountGroupName;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }
}

