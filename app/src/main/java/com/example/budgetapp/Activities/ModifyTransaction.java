package com.example.budgetapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.budgetapp.DataManager.Model.AccountType;
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
import java.util.IllegalFormatException;
import java.util.List;
import java.util.concurrent.Executors;

public class ModifyTransaction extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private String TAG;

    private Spinner transactionType;
    private Spinner selectVersion;

    private EditText transactionName;
    private EditText transactionDescription;
    private EditText transactionAmount;


    private String name;
    private String description;
    private Double amount = 0.0;
    private String versionName;
    private String typeName;
    private String accountClass;
    private String accountGroup;
    private String selectedTransaction;

    private Button saveButton;

    private VersionEntry versionEntry;

    ArrayList<String> versionSpinnerArray = new ArrayList<String>();
    ArrayList<String> typeSpinnerArray = new ArrayList<String>();
    ArrayList<String> transSpinnerArray = new ArrayList<String>();
    Integer goalID = 0;

    //DATA VIEW MODEL
    GoalViewModel goalViewModel;
    GoalDetailViewModel goalDetailViewModel;
    VersionViewModel versionViewModel;
    AccountGroupViewModel accountGroupViewModel;
    AccountTypeViewModel accountTypeViewModel;
    VersionEntryViewModel versionEntryViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_transaction);

        //DATA VIEW MODEL : INITALIZE
        goalViewModel = ViewModelProviders.of(this).get(GoalViewModel.class);
        goalDetailViewModel = ViewModelProviders.of(this).get(GoalDetailViewModel.class);
        versionViewModel = ViewModelProviders.of(this).get(VersionViewModel.class);
        accountGroupViewModel = ViewModelProviders.of(this).get(AccountGroupViewModel.class);
        accountTypeViewModel = ViewModelProviders.of(this).get(AccountTypeViewModel.class);
        versionEntryViewModel =ViewModelProviders.of(this).get(VersionEntryViewModel.class);

        //INITIALIZING BUTTONS AND EDIT TEXTS
//        transactionName = (EditText) findViewById(R.id.modify_transaction_name_entry);
//        transactionDescription = (EditText) findViewById(R.id.modify_transaction_description_entry);
//        transactionAmount = (EditText) findViewById(R.id.modify_transaction_amount_entry);

        saveButton = (Button) findViewById(R.id.modify_transaction_save_button);

        //SPINNER ARRAY
//        ArrayList<String> versionSpinnerArray = new ArrayList<String>();
        versionSpinnerArray.add("Select a Version");
        typeSpinnerArray.add("Select a Type");
        transSpinnerArray.add("Select a Transaction");

        //SIDEBAR MENU
        toolbar = findViewById(R.id.modify_transaction_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.modify_transaction_drawer_layout);
        navigationView = findViewById(R.id.modify_transaction_nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        setActionBar("Modify Transaction");


        //SPINNER
        //VERSION ENTRY (TRANSACTIONS) ARRAY
        Spinner spinnerTrans = findViewById(R.id.modify_transaction_transaction_spinner);
        ArrayAdapter<String> modifyTransactionTransAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                transSpinnerArray
        );

        modifyTransactionTransAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerTrans.setAdapter(modifyTransactionTransAdapter);
        spinnerTrans.setOnItemSelectedListener(this);

        //SET UP OBSERVER VERSIONS
        versionEntryViewModel.getAllVersionEntry().observe(this, new Observer<List<VersionEntry>>() {
            @Override
            public void onChanged(List<VersionEntry> versionEntry) {
                for (VersionEntry ve : versionEntry) {
                    transSpinnerArray.add(ve.getEntryName());
                }
                modifyTransactionTransAdapter.notifyDataSetChanged();
            }
        });

        spinnerTrans.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedTransaction = adapterView.getItemAtPosition(i).toString();




            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //SPINNER
        //VERSION ARRAY
        Spinner spinnerVersion = findViewById(R.id.modify_transaction_version_spinner);
        ArrayAdapter<String> modifyTransactionVersionAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                versionSpinnerArray
        );

        modifyTransactionVersionAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerVersion.setAdapter(modifyTransactionVersionAdapter);
        spinnerVersion.setOnItemSelectedListener(this);

        //SET UP OBSERVER VERSIONS
        versionViewModel.getAllVersion().observe(this, new Observer<List<Version>>() {
            @Override
            public void onChanged(List<Version> version) {
                for (Version v : version) {
                    versionSpinnerArray.add(v.getVersionName());
                }
                modifyTransactionVersionAdapter.notifyDataSetChanged();
            }
        });


        //SPINNER
        //Type ARRAY
        Spinner spinnerType = findViewById(R.id.modify_transaction_type_spinner);
        ArrayAdapter<String> modifyTransactionTypeAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                typeSpinnerArray
        );

        modifyTransactionTypeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerType.setAdapter(modifyTransactionTypeAdapter);
        spinnerType.setOnItemSelectedListener(this);

        //SET UP OBSERVER VERSIONS
        accountTypeViewModel.getAllAccountType().observe(this, new Observer<List<AccountType>>() {
            @Override
            public void onChanged(List<AccountType> accountType) {
                for (AccountType v : accountType) {
                    typeSpinnerArray.add(v.getAccountTypeName());
                }
                modifyTransactionTypeAdapter.notifyDataSetChanged();
            }
        });



        spinnerVersion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                versionName = adapterView.getItemAtPosition(i).toString();




            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });






//
//        //REMOVING TEXT WHEN CLICKED
//        transactionName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                transactionName.setText("");
//            }
//        });
//        transactionDescription.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                transactionDescription.setText("");
//            }
//        });
//        transactionAmount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                transactionAmount.setText("");
//            }
//        });

//        //SETING UP TEXT WATCHER LISTENER FOR ENTRIES
//        TextWatcher entryTextWatcher = new TextWatcher() {
//
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                String getName;
//                String getDesc;
//                String getAmt;
//
//                getName = transactionName.getText().toString();
//                getDesc = transactionDescription.getText().toString();
//                getAmt = transactionAmount.getText().toString();
//
//                //               saveButton.setEnabled(!getName.isEmpty() && !getDesc.isEmpty() && !getAmt.isEmpty());
//                //name = getName;
////                getDesc = getDesc;
////                amount = Double.parseDouble(getAmt);
//
//                try {
//                    name = getName;
//                    //debugging
//                    //Toast.makeText(CreateVersion.this, versionName, Toast.LENGTH_SHORT).show();
//                } catch (IllegalFormatException ex) {
//                    transactionName.setText("");
//                    name = "";
//                }
//
//                try {
//                    description = getDesc;
//                    //debugging
//                    //Toast.makeText(CreateVersion.this, versionName, Toast.LENGTH_SHORT).show();
//                } catch (IllegalFormatException ex) {
//                    transactionDescription.setText("");
//                    description = "";
//                }
//
//                try {
//                    Double val = Double.valueOf(getAmt);
//
//                    Log.d(TAG, "val = " + val);
//                    //amount = 0.0;
//                    amount = val;
//                    Log.d(TAG, "amount = " + amount);
//                    //debugging
//                    //Toast.makeText(CreateVersion.this, versionName, Toast.LENGTH_SHORT).show();
//                } catch (NumberFormatException e) {
//                    //transactionAmount.setText("");
//                }
//
//
//                Log.d(TAG, "name = " + name);
//                Log.d(TAG, "description = " + description);
//                Log.d(TAG, "amount = " + amount);
//
//            }
//
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        };

//        transactionName.addTextChangedListener(entryTextWatcher);
//        transactionDescription.addTextChangedListener(entryTextWatcher);
//        transactionAmount.addTextChangedListener(entryTextWatcher);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //VersionEntry entry = versionEntryViewModel.getVersionEntry(versionName, selectedTransaction);

                Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        VersionEntry ve;
                        ve =versionEntryViewModel.getVersionEntry(versionName, selectedTransaction);
//                        accountClass = at.getAccountClassName();
//                        accountGroup = at.getAccountGroupName();
                        versionEntryViewModel.deleteVersionEntry(ve);
                    }
                });



                openDialog();
            }
        });



    }

    //DIALOG BOX CODE
    public void openDialog(){
        CreateVersionDialog createVersionDialog = new CreateVersionDialog();
        createVersionDialog.show(getSupportFragmentManager(), "saved dialog box");
    }

    public void setActionBar(String heading){
        ActionBar actionBar = getSupportActionBar();
        //actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.outline_menu_white_48);
        //actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        actionBar.setTitle(heading);
        actionBar.show();
    }

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

    //SPINNER STUFF
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
