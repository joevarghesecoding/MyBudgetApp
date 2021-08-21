package com.example.budgetapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.budgetapp.DataManager.Database.Repository;
import com.example.budgetapp.DataManager.Model.AccountType;

import java.util.List;

/**
 * AccountTypeViewModel
 * Functions from Repository for AccountTypeViewModel
 */
public class AccountTypeViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<AccountType>> allAccountType;

    /**
     * Constructor for AccountTypeViewModel
     * @param application
     */
    public AccountTypeViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        this.allAccountType = repository.getAllAccountType();
    }

    /* AccountType Data Queries */
    public LiveData<List<AccountType>> getAllAccountType() {
        return allAccountType;
    }

    public AccountType getAccountType(String accountTypeName){
        return repository.getAccountType(accountTypeName);
    }

    /* AccountType Insert */
    public void insertAccountType(AccountType accountType) {
        repository.insertAccountType(accountType);
    }

    /* AccountType Update */
    public void updateAccountType(AccountType accountType) {
        repository.updateAccountType(accountType);
    }

    /* AccountType Delete */
    public void deleteAccountType(AccountType accountType) {
        repository.deleteAccountType(accountType);
    }



}

