package com.example.budgetapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.budgetapp.DataManager.Database.Repository;
import com.example.budgetapp.DataManager.Model.Fact;

import java.util.List;

/**
 * FactViewModel
 * Functions from Repository for FactViewModel
 */

public class FactViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Fact>> allFact;

    /**
     * Constructor for FactViewModel
     * @param application
     */
    public FactViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        this.allFact = repository.getAllFact();
    }

    /* Fact Data Queries */
    public LiveData<List<Fact>> getAllFact() {
        return allFact;
    }

    public LiveData<List<Fact>> getFactByVer(String verName){
        return repository.getFactByVer(verName);
    }

//    public LiveData<List<Fact>> getFactSum(String verName){
//        return repository.getFactSum(verName);
//    }

    /* Fact Insert */
    public void insertFact(Fact fact) {
        repository.insertFact(fact);
    }

    /* Fact Update */
    public void updateFact(Fact fact) {
        repository.updateFact(fact);
    }

    /* Fact Delete */
    public void deleteFact(Fact fact) {
        repository.deleteFact(fact);
    }


}

