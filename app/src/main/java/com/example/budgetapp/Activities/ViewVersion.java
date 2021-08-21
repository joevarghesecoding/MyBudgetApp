package com.example.budgetapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.budgetapp.DataManager.Model.Version;
import com.example.budgetapp.DataManager.Model.VersionEntry;
import com.example.budgetapp.R;
import com.example.budgetapp.ViewModel.AccountGroupViewModel;
import com.example.budgetapp.ViewModel.AccountTypeViewModel;
import com.example.budgetapp.ViewModel.GoalDetailViewModel;
import com.example.budgetapp.ViewModel.GoalViewModel;
import com.example.budgetapp.ViewModel.VersionEntryViewModel;
import com.example.budgetapp.ViewModel.VersionViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ViewVersion extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    ArrayList<String> versionSpinnerArray = new ArrayList<String>();
    ArrayList<String> transSpinnerArray = new ArrayList<String>();

    GoalViewModel goalViewModel;
    GoalDetailViewModel goalDetailViewModel;
    VersionViewModel versionViewModel;
    AccountGroupViewModel accountGroupViewModel;
    AccountTypeViewModel accountTypeViewModel;
    VersionEntryViewModel versionEntryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_version);

        //DATA VIEW MODEL : INITALIZE
        goalViewModel = ViewModelProviders.of(this).get(GoalViewModel.class);
        goalDetailViewModel = ViewModelProviders.of(this).get(GoalDetailViewModel.class);
        versionViewModel = ViewModelProviders.of(this).get(VersionViewModel.class);
        accountGroupViewModel = ViewModelProviders.of(this).get(AccountGroupViewModel.class);
        accountTypeViewModel = ViewModelProviders.of(this).get(AccountTypeViewModel.class);
        versionEntryViewModel =ViewModelProviders.of(this).get(VersionEntryViewModel.class);

        versionSpinnerArray.add("Select a Version");
        transSpinnerArray.add("Select a Transaction");

        //SIDEBAR MENU
        toolbar = findViewById(R.id.view_version_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.view_version_drawer_layout);
        navigationView = findViewById(R.id.view_version_nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

       setActionBar("View Version");

        //SPINNER
        //VERSION ARRAY
        Spinner spinnerVersion = findViewById(R.id.view_version_version_spinner);
        ArrayAdapter<String> viewVersionVersionAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                versionSpinnerArray
        );

        viewVersionVersionAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerVersion.setAdapter(viewVersionVersionAdapter);
        spinnerVersion.setOnItemSelectedListener(this);

        //SET UP OBSERVER VERSIONS
        versionViewModel.getAllVersion().observe(this, new Observer<List<Version>>() {
            @Override
            public void onChanged(List<Version> version) {
                for (Version v : version) {
                    versionSpinnerArray.add(v.getVersionName());
                }
                viewVersionVersionAdapter.notifyDataSetChanged();
            }
        });

        //SPINNER
        //VERSION ENTRY (TRANSACTIONS) ARRAY
        Spinner spinnerTrans = findViewById(R.id.view_version_transaction_spinner);
        ArrayAdapter<String> viewVersionTransAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                transSpinnerArray
        );

        viewVersionTransAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerTrans.setAdapter(viewVersionTransAdapter);
        spinnerTrans.setOnItemSelectedListener(this);

        //SET UP OBSERVER VERSIONS
        versionEntryViewModel.getAllVersionEntry().observe(this, new Observer<List<VersionEntry>>() {
            @Override
            public void onChanged(List<VersionEntry> versionEntry) {
                for (VersionEntry ve : versionEntry) {
                    transSpinnerArray.add(ve.getEntryName());
                }
                viewVersionTransAdapter.notifyDataSetChanged();
            }
        });

    }

    //SIDEBAR EXTRAS
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.create_new_version_nav: {
                //Toast.makeText(this, "create_version is pressed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, CreateVersion.class);
                startActivity(intent);
                break;
            }
            case R.id.modify_version_nav: {
                //Toast.makeText(this, "create_version is pressed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ModifyVersion.class);
                startActivity(intent);
                break;
            }
            case R.id.add_transaction_nav: {
                //Toast.makeText(this, "create_version is pressed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, AddTransaction.class);
                startActivity(intent);
                break;
            }
            case R.id.modify_transaction_nav: {
                //Toast.makeText(this, "create_version is pressed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ModifyTransaction.class);
                startActivity(intent);
                break;
            }
            case R.id.dashboard_nav: {
                //Toast.makeText(this, "create_version is pressed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.view_version_nav_view: {
                //Toast.makeText(this, "create_version is pressed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ViewVersion.class);
                startActivity(intent);
                break;
            }
//            case R.id.view_transaction_nav: {
//                //Toast.makeText(this, "create_version is pressed", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(this, ViewTransaction.class);
//                startActivity(intent);
//                break;
//            }case R.id.view_goals_nav: {
//                //Toast.makeText(this, "create_version is pressed", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(this, ViewGoals.class);
//                startActivity(intent);
//                break;
//            }case R.id.strategies_nav: {
//                //Toast.makeText(this, "create_version is pressed", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(this, Introduction.class);
//                startActivity(intent);
//                break;
//            }

        }
        return true;

    }

    //SIDEBAR EXTRAS
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    //ACTION BAR

    public void setActionBar(String heading){
        ActionBar actionBar = getSupportActionBar();
        //actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.outline_menu_white_48);
        //actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        actionBar.setTitle(heading);
        actionBar.show();
    }

    //SPINNER EXTRAS
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //Put code here for the logic of what selected means
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}