package com.example.budgetapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.budgetapp.DataManager.Database.Repository;
import com.example.budgetapp.DataManager.Model.AccountGroup;

import java.util.List;

/**
 * AccountGroupViewModel
 * Functions from Repository for AccountGroupViewModel
 */
public class AccountGroupViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<AccountGroup>> allAccountGroup;

    /**
     * Constructor for AccountGroupViewModel
     * @param application
     */
    public AccountGroupViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        this.allAccountGroup = repository.getAllAccountGroup();
    }

    /* AccountGroup Data Queries */
    public LiveData<List<AccountGroup>> getAllAccountGroup() {
        return allAccountGroup;
    }

    public AccountGroup getAccountGroup(String accountGroupName) {
        return repository.getAccountGroup(accountGroupName);
    }

    /* AccountGroup Insert */
    public void insertAccountGroup(AccountGroup accountGroup) {
        repository.insertAccountGroup(accountGroup);
    }

    /* AccountGroup Update */
    public void updateAccountGroup(AccountGroup accountGroup) {
        repository.updateAccountGroup(accountGroup);
    }

    /* AccountGroup Delete */
    public void deleteAccountGroup(AccountGroup accountGroup) {
        repository.deleteAccountGroup(accountGroup);
    }

/*    public AccountGroup getAccountGroup(){
        AccountGroup accGroup = (AccountGroup) allAccountGroup.getValue();
        return accGroup;
    }*/


}

