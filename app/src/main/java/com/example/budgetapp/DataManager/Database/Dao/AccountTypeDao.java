package com.example.budgetapp.DataManager.Database.Dao;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budgetapp.DataManager.Model.AccountType;


import java.util.List;

/**
 * AccountTypeDao
 * DAO interface for AccountType entity.
 * Contains Insert, Update, Delete and Queries necessary for implementation
 */

@Dao
public interface AccountTypeDao {

    // DAO Queries for AccountType

    @Insert
    void insertAccountType(AccountType accountType);

    @Update
    void updateAccountType(AccountType accountType);

    @Delete
    void deleteAccountType(AccountType accountType);

    // Query to return all Account Types
    @Query("Select * From AccountType Order By id DESC")
    LiveData<List<AccountType>> getAllAccountType();

    // Query to return a specific Account Type
    @Query("Select * From AccountType Where accountTypeName = :accountTypeName")
    AccountType getAccountType(String accountTypeName);
}

