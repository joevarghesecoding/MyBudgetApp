package com.example.budgetapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.budgetapp.DataManager.Database.Repository;
import com.example.budgetapp.DataManager.Model.Version;
import com.example.budgetapp.DataManager.Model.VersionEntry;

import java.util.List;

/**
 * VersionEntryViewModel
 * Functions from Repository for VersionEntryViewModel
 */

public class VersionEntryViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<VersionEntry>> allVersionEntry;

    /**
     * Constructor for VersionEntryViewModel
     * @param application
     */
    public VersionEntryViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        this.allVersionEntry = repository.getAllVersionEntry();
    }

    /* VersionEntry Data Queries */
    public LiveData<List<VersionEntry>> getAllVersionEntry() {
        return allVersionEntry;
    }

    public LiveData<List<VersionEntry>> getVersionEntryClass(String versionName, String accountTypeClass) {
        return repository.getVersionEntryClass(versionName, accountTypeClass);
    }

//    public LiveData<List<VersionEntry>> getVersionEntrySummary(String verName){
//        return repository.getVersionEntrySummary(verName);
//    }
    public VersionEntry getVersionEntry(String versionName, String accountTypeClass) {
    return repository.getVersionEntry(versionName, accountTypeClass);
}

    /* VersionEntry Insert */
    public void insertVersionEntry(VersionEntry versionEntry) {
        repository.insertVersionEntry(versionEntry);
    }

    /* VersionEntry Update */
    public void updateVersionEntry(VersionEntry versionEntry) {
        repository.updateVersionEntry(versionEntry);
    }

    /* VersionEntry Delete */
    public void deleteVersionEntry(VersionEntry versionEntry) {
        repository.deleteVersionEntry(versionEntry);
    }


}

