package com.example.budgetapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.budgetapp.DataManager.Database.Repository;
import com.example.budgetapp.DataManager.Model.Version;


import java.util.List;

/**
 * VersionViewModel
 * Functions from Repository for VersionViewModel
 */

public class VersionViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Version>> allVersion;


    /**
     * Constructor for VersionViewModel
     * @param application
     */
    public VersionViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        this.allVersion = repository.getAllVersion();
    }

    /* Version Data Queries */
    public LiveData<List<Version>> getAllVersion() {
        return allVersion;
    }

    public Version getVersion(String verName){
        return repository.getVersion(verName);
    }

    /* Version Insert */
    public void insertVersion(Version version) {
        repository.insertVersion(version);
    }

    /* Version Update */
    public void updateVersion(Version version) {
        repository.updateVersion(version);
    }

    /* Version Delete */
    public void deleteVersion(Version version) {
        repository.deleteVersion(version);
    }

    }





