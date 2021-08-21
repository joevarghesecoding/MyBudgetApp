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


public class AddTransaction extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {

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
    
    private Button saveButton;

    ArrayList<String> versionSpinnerArray = new ArrayList<String>();
    ArrayList<String> typeSpinnerArray = new ArrayList<String>();
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
        setContentView(R.layout.activity_add_transaction);

        //DATA VIEW MODEL : INITALIZE
        goalViewModel = ViewModelProviders.of(this).get(GoalViewModel.class);
        goalDetailViewModel = ViewModelProviders.of(this).get(GoalDetailViewModel.class);
        versionViewModel = ViewModelProviders.of(this).get(VersionViewModel.class);
        accountGroupViewModel = ViewModelProviders.of(this).get(AccountGroupViewModel.class);
        accountTypeViewModel = ViewModelProviders.of(this).get(AccountTypeViewModel.class);
        versionEntryViewModel =ViewModelProviders.of(this).get(VersionEntryViewModel.class);

        //SPINNER ARRAY
//        ArrayList<String> versionSpinnerArray = new ArrayList<String>();
        versionSpinnerArray.add("Select a Version");
        typeSpinnerArray.add("Select a Type");

        //INITIALIZING BUTTONS AND EDIT TEXTS
        transactionType = (Spinner) findViewById(R.id.add_transaction_type_spinner);
        selectVersion = (Spinner) findViewById(R.id.add_transaction_version_spinner);

        transactionName = (EditText) findViewById(R.id.add_transaction_name_entry);
        transactionDescription = (EditText) findViewById(R.id.add_transaction_description_entry);
        transactionAmount = (EditText) findViewById(R.id.add_transaction_amount_entry);

        saveButton = (Button) findViewById(R.id.add_transaction_save_button);

        //SIDEBAR MENU
        toolbar = findViewById(R.id.add_transaction_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.add_transaction_drawer_layout);
        navigationView = findViewById(R.id.add_transaction_nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        setActionBar("Add Transaction");






        //ARRAY ADAPTER
        //VERSION ARRAY
        //SPINNER
        Spinner spinner = findViewById(R.id.add_transaction_version_spinner);
        ArrayAdapter<String> addTransactionArrayAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                versionSpinnerArray
        );

        addTransactionArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(addTransactionArrayAdapter);
        spinner.setOnItemSelectedListener(this);

        //SPINNER
        //TYPE ARRAY
        Spinner spinnerType = findViewById(R.id.add_transaction_type_spinner);
        ArrayAdapter<String> addTransactionTypeAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                typeSpinnerArray
        );

        addTransactionTypeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerType.setAdapter(addTransactionTypeAdapter);
        spinnerType.setOnItemSelectedListener(this);

        //SET UP OBSERVER VERSIONS
        versionViewModel.getAllVersion().observe(this, new Observer<List<Version>>() {
            @Override
            public void onChanged(List<Version> versions) {
                for (Version v : versions) {
                    versionSpinnerArray.add(v.getVersionName());
                }
                addTransactionArrayAdapter.notifyDataSetChanged();
            }
        });

        //SET UP OBERSEVER TYPES
        accountTypeViewModel.getAllAccountType().observe(this, new Observer<List<AccountType>>() {
            @Override
            public void onChanged(List<AccountType> accountTypes) {
                for(AccountType at : accountTypes){
                    typeSpinnerArray.add(at.getAccountTypeName());

                }
                addTransactionTypeAdapter.notifyDataSetChanged();
            }
        });

        //VERSION SELECTION SPINNER
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
              @Override
              public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                  versionName = adapterView.getItemAtPosition(i).toString();
                  Log.d(TAG, "Selected Version name is : " + versionName);
                  //versionNameEntry.setText(versionName);

              }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //TYPE SELECTION SPINNER
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                typeName = adapterView.getItemAtPosition(i).toString();
                if(!typeName.equals("Select a Type")) {
//                    AccountType accountType = accountTypeViewModel.getAccountType(typeName);
//                    accountClass = accountType.getAccountClassName();
//                    accountGroup = accountType.getAccountGroupName();

                    Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                        @Override
                        public void run() {
                            AccountType at;
                            at = accountTypeViewModel.getAccountType(typeName);
                            accountClass = at.getAccountClassName();
                            accountGroup = at.getAccountGroupName();

                        }
                    });
                }
                Log.d(TAG, "Selected Type name is : " + typeName);
                //versionNameEntry.setText(versionName);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


                //REMOVING TEXT WHEN CLICKED
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

        /**
         * REVERT HERE 7:15
         */
        //SETING UP TEXT WATCHER LISTENER FOR ENTRIES
        TextWatcher entryTextWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String getName;
                String getDesc;
                String getAmt;

                getName = transactionName.getText().toString();
                getDesc = transactionDescription.getText().toString();
                getAmt = transactionAmount.getText().toString();

 //               saveButton.setEnabled(!getName.isEmpty() && !getDesc.isEmpty() && !getAmt.isEmpty());
                //name = getName;
//                getDesc = getDesc;
//                amount = Double.parseDouble(getAmt);

                try {
                    name = getName;
                    //debugging
                    //Toast.makeText(CreateVersion.this, versionName, Toast.LENGTH_SHORT).show();
                } catch (IllegalFormatException ex) {
                    transactionName.setText("");
                    name = "";
                }

                try {
                    description = getDesc;
                    //debugging
                    //Toast.makeText(CreateVersion.this, versionName, Toast.LENGTH_SHORT).show();
                } catch (IllegalFormatException ex) {
                    transactionDescription.setText("");
                    description = "";
                }

                try {
                    Double val = Double.valueOf(getAmt);

                    Log.d(TAG, "val = " + val);
                    //amount = 0.0;
                    amount = val;
                    Log.d(TAG, "amount = " + amount);
                    //debugging
                    //Toast.makeText(CreateVersion.this, versionName, Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    //transactionAmount.setText("");
                }


                Log.d(TAG, "name = " + name);
                Log.d(TAG, "description = " + description);
                Log.d(TAG, "amount = " + amount);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        transactionName.addTextChangedListener(entryTextWatcher);
        transactionDescription.addTextChangedListener(entryTextWatcher);
        transactionAmount.addTextChangedListener(entryTextWatcher);

        //ADD TRANSACTION SAVE BUTTON
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "SAVE BUTTON IS CLICKED");

                VersionEntry entry = new VersionEntry(versionName, accountClass, accountGroup, typeName, name, description, amount);

//                Log.d(TAG, "versionName IS " + versionName);
//                Log.d(TAG, "accountClass IS " + accountClass);
//                Log.d(TAG, "accountGroup IS " + accountGroup);
//                Log.d(TAG, "typeName IS " + typeName);
//                Log.d(TAG, "name IS " + name);
//                Log.d(TAG, "description IS " + description);
//                Log.d(TAG, "amount IS " + amount);


                versionEntryViewModel.insertVersionEntry(entry);

                openDialog();
            }
        });



    }

    //DIALOG BOX CODE
    public void openDialog(){
        CreateVersionDialog createVersionDialog = new CreateVersionDialog();
        createVersionDialog.show(getSupportFragmentManager(), "saved dialog box");

        versionEntryViewModel.getAllVersionEntry().observe(this, new Observer<List<VersionEntry>>() {
            @Override
            public void onChanged(List<VersionEntry> versionEntries) {
                for(VersionEntry ve : versionEntries){
                    Log.d(TAG, "versionName IS " + ve.getVersionName());
                    Log.d(TAG, "accountClass IS " + ve.getAccountClassName());
                    Log.d(TAG, "accountGroup IS " + ve.getAccountGroupName());
                    Log.d(TAG, "typeName IS " + ve.getAccountTypeName());
                    Log.d(TAG, "name IS " + ve.getEntryName());
                    Log.d(TAG, "description IS " + ve.getEntryDesc());
                    Log.d(TAG, "amount IS " + ve.getEntryAmount());
                }
            }
        });
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

    //SPINNER EXTRAS
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
