package com.example.budgetapp.DataManager.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Version
 * Entity and Transport object for Version table
 */
@Entity
public class Version {
    @PrimaryKey (autoGenerate = true)
    int id;
    String versionName;

//    public Version()
//    { this.versionName = "test"; }

    /**
     * Constructor for Version Entity
     * @param versionName
     */
    public Version(String versionName) {
        this.versionName = versionName;
    }

    // Get and Set Methods for fields of Version

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
}
