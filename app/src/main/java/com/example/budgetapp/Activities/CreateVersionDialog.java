package com.example.budgetapp.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.budgetapp.DataManager.Model.GoalDetail;
import com.example.budgetapp.DataManager.Model.Version;
import com.example.budgetapp.ViewModel.AccountGroupViewModel;
import com.example.budgetapp.ViewModel.GoalDetailViewModel;
import com.example.budgetapp.ViewModel.GoalViewModel;
import com.example.budgetapp.ViewModel.VersionViewModel;

import java.util.List;


public class CreateVersionDialog extends AppCompatDialogFragment {

    //
//    GoalViewModel goalViewModel = new ViewModelProvider(this).get(GoalViewModel.class);
//    GoalDetailViewModel goalDetailViewModel = new ViewModelProvider(this).get(GoalDetailViewModel.class);
//    VersionViewModel versionViewModel = new ViewModelProvider(this).get(VersionViewModel.class);
//    AccountGroupViewModel accountGroupViewModel = new ViewModelProvider(this).get(AccountGroupViewModel.class);
    List<Version> versionList;
    private String TAG;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(TAG, "INSIDE DIALOG");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Saved")
                .setMessage("Your Contents are Saved")
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }

                });


        return builder.create();


    }
}
