package com.example.budgetapp.DataManager.Database.Dao;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budgetapp.DataManager.Model.AccountGroup;

import java.util.List;

/**
 * AccountGroupDao
 * DAO interface for AccountGroup entity.
 * Contains Insert, Update, Delete and Queries necessary for implementation
 */

@Dao
public interface AccountGroupDao {

    // DAO Queries for AccountGroup

    @Insert
    void insertAccountGroup(AccountGroup accountGroup);

    @Insert
    void insertAll(AccountGroup[] accountGroups);

    @Insert
    void insertPreloadAccountGroup(AccountGroup[] accountGroups);

    @Update
    void updateAccountGroup(AccountGroup accountGroup);

    @Delete
    void deleteAccountGroup(AccountGroup accountGroup);

    // Query to return all Account Groups
    @Query("Select * From AccountGroup Order By id DESC")
    LiveData<List<AccountGroup>> getAllAccountGroup();

    // Query to return a specific Account Group
    @Query("Select * From AccountGroup WHERE accountGroupName = :accountGroupName")
    AccountGroup getAccountGroup(String accountGroupName);

}
