package com.example.budgetapp.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.budgetapp.DataManager.Model.AccountGroup;
import com.example.budgetapp.DataManager.Model.Goal;
import com.example.budgetapp.DataManager.Model.GoalDetail;
import com.example.budgetapp.DataManager.Model.Version;
import com.example.budgetapp.DataManager.Model.VersionEntry;
import com.example.budgetapp.R;
import com.example.budgetapp.ViewModel.AccountGroupViewModel;
import com.example.budgetapp.ViewModel.GoalDetailViewModel;
import com.example.budgetapp.ViewModel.GoalViewModel;
import com.example.budgetapp.ViewModel.VersionViewModel;
import com.google.android.material.navigation.NavigationView;


import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;


public class CreateVersion extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //TEST TO GIT

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private EditText versionNameEntry;
    private EditText estimateIncomeEntry;
    private EditText estimateExpensesEntry;

    private TextView remainingBalance;
    private TextView essentialsEntry;
    private TextView nonessentialsEntry;
    private TextView savingsEntry;

    private SeekBar essentialsBar;
    private SeekBar nonessentialsBar;
    private SeekBar savingsBar;

    private TextView essentialsPercent;
    private TextView nonessentialsPercent;
    private TextView savingsPercent;


    private Button saveButton;

    private String versionName;
    private Double estimateIncome= 0.0;
    private Double estimateExpenses;
    private Double essentialsVal = 0.0;
    private Double nonessentialsVal = 0.0;
    private Double savingsVal = 0.0;
    private Double essentialsValStart;
    private Double nonessentialsValStart;
    private Double savingsValStart;
    private Double balance = 0.0;
    private String TAG;

    //DATABASE VIEW MODEL
    GoalViewModel goalViewModel;
    GoalDetailViewModel goalDetailViewModel;
    VersionViewModel versionViewModel;
    AccountGroupViewModel accountGroupViewModel;


    //ADD THESE TO GET VALUES
    ArrayList<Integer> goal_id = new ArrayList<Integer>();
    ArrayList<Integer> version_id = new ArrayList<Integer>();
    ArrayList<Integer> acc_grp_id = new ArrayList<Integer>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_version);



        //DATABASE: VIEW MODELS
        //goalViewModel = new ViewModelProvider(this).get(GoalViewModel.class);
        //goalDetailViewModel = new ViewModelProvider(this).get(GoalDetailViewModel.class);
        //versionViewModel = new ViewModelProvider(this).get(VersionViewModel.class);
        //accountGroupViewModel = new ViewModelProvider(this).get(AccountGroupViewModel.class);

        goalViewModel =  ViewModelProviders.of(this).get(GoalViewModel.class);
        goalDetailViewModel = ViewModelProviders.of(this).get(GoalDetailViewModel.class);
        versionViewModel = ViewModelProviders.of(this).get(VersionViewModel.class);
        accountGroupViewModel = ViewModelProviders.of(this).get(AccountGroupViewModel.class);

        //observePls();
        //versionList = new ArrayList<>();

        //SIDEBAR MENU
        toolbar = findViewById(R.id.create_version_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.create_version_drawer_layout);
        navigationView = findViewById(R.id.create_version_nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        setActionBar("Create Version");

        //Buttons and choices
        versionNameEntry = (EditText) findViewById(R.id.create_version_response1);
        estimateIncomeEntry = (EditText) findViewById(R.id.create_version_response2);
        estimateExpensesEntry = (EditText) findViewById(R.id.create_version_response3);

        remainingBalance = (TextView) findViewById(R.id.create_version_remaining_entry);
        essentialsEntry = (TextView) findViewById(R.id.create_version_essentials_amt);
        nonessentialsEntry = (TextView) findViewById(R.id.create_version_nonessentials_amt);
        savingsEntry = (TextView) findViewById(R.id.create_version_savings_amt);


        essentialsPercent = (TextView) findViewById(R.id.create_version_essential_percentage);
        nonessentialsPercent = (TextView) findViewById(R.id.create_version_nonessential_percentage);
        savingsPercent = (TextView) findViewById(R.id.create_version_savings_percentage);


        essentialsBar = (SeekBar) findViewById(R.id.create_version_seekBar_essentials);
        nonessentialsBar = (SeekBar) findViewById(R.id.create_version_seekBar_nonessentials);
        savingsBar = (SeekBar) findViewById(R.id.create_version_seekBar_savings);

        saveButton = (Button) findViewById(R.id.create_version_save_changes);



        //SETTING UP LISTENERS
        //entryTextWatcher controls the listening of all the three entries.

        TextWatcher entryTextWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String enteredIncome = estimateIncomeEntry.getText().toString();
                String enteredExpense = estimateExpensesEntry.getText().toString();
                String enteredVName = versionNameEntry.getText().toString();

                saveButton.setEnabled(!enteredVName.isEmpty() && !enteredIncome.isEmpty()
                        && !enteredExpense.isEmpty());

                try {
                    versionName = enteredVName;
                    //debugging
                    //Toast.makeText(CreateVersion.this, versionName, Toast.LENGTH_SHORT).show();
                } catch (IllegalFormatException ex) {
                    versionNameEntry.setText("");
                    versionName = "";
                }

                try {
                    Double val = Double.parseDouble(enteredIncome);
                    estimateIncome = val;
                    balance = val;
                    Double fifty = val * 0.50;
                    Double thirty = val * 0.30;
                    Double twenty = val * 0.20;


                    String a = String.format("%.2f", fifty);
                    essentialsEntry.setText("$");
                    essentialsEntry.append(a);
                    essentialsValStart = fifty;
                    essentialsVal = essentialsValStart;


                    String b = String.format("%.2f", thirty);
                    nonessentialsEntry.setText("$");
                    nonessentialsEntry.append(b);
                    nonessentialsValStart = thirty;
                    nonessentialsVal = nonessentialsValStart;


                    String c = String.format("%.2f", twenty);
                    savingsEntry.setText("$");
                    savingsEntry.append(c);
                    savingsValStart = twenty;
                    savingsVal = savingsValStart;


                    essentialsBar.setProgress(50, true);
                    nonessentialsBar.setProgress(30, true);
                    savingsBar.setProgress(20, true);


                } catch (NumberFormatException e) {
                    essentialsEntry.setText("$0");
                    nonessentialsEntry.setText("$0");
                    savingsEntry.setText("$0");
                }

                try {
                    Double val = Double.parseDouble(enteredExpense);

                    estimateExpenses = val;
                } catch (NumberFormatException e) {
                    nonessentialsEntry.setText("$0");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        estimateIncomeEntry.addTextChangedListener(entryTextWatcher);
        estimateExpensesEntry.addTextChangedListener(entryTextWatcher);
        versionNameEntry.addTextChangedListener(entryTextWatcher);

        //Scrollbar changing values
        //essentials bar
        essentialsBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //Double half = (i*(estimateIncome/100.0));
                Double val = (i * (estimateIncome / 100.0));

                //Toast.makeText(CreateVersion.this, val.toString(), Toast.LENGTH_SHORT).show();
                essentialsEntry.setText("$");
                essentialsEntry.append(val.toString());
                essentialsVal = val;


                Integer essPer = seekBar.getProgress();
                essentialsPercent.setText(essPer.toString());
                essentialsPercent.append("%");

                Log.d(TAG, "essentialsVal: " + essentialsVal);
                //Toast.makeText(CreateVersion.this, essentialsVal.toString(), Toast.LENGTH_SHORT).show();

                //balance = essentialsVal;
                balance = estimateIncome - essentialsVal - nonessentialsVal - savingsVal;
                Log.d(TAG, "balance: " + balance);


                // Log.d(TAG, "Balance at last try: " + balance);
                remainingBalance.setText("$");
                remainingBalance.append(balance.toString());
                if (balance < 0) {
                    remainingBalance.setTextColor(Color.RED);
                } else {
                    remainingBalance.setTextColor(Color.BLACK);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });

        //nonessentials bar
        nonessentialsBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Double val = (i * (estimateIncome / 100.0));
                //Toast.makeText(CreateVersion.this, val.toString(), Toast.LENGTH_SHORT).show();
                nonessentialsEntry.setText("$");
                nonessentialsEntry.append(val.toString());
                nonessentialsVal = val;
                Log.d(TAG, "nonessentialsVal: " + nonessentialsVal);

                Integer nonessPer = seekBar.getProgress();
                nonessentialsPercent.setText(nonessPer.toString());
                nonessentialsPercent.append("%");

                //Toast.makeText(CreateVersion.this, nonessentialsVal.toString(), Toast.LENGTH_SHORT).show();

                balance = estimateIncome - essentialsVal - nonessentialsVal - savingsVal;

                Log.d(TAG, "balance: " + balance);


                remainingBalance.setText("$");
                remainingBalance.append(balance.toString());
                if (balance < 0) {
                    remainingBalance.setTextColor(Color.RED);
                } else {
                    remainingBalance.setTextColor(Color.BLACK);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //savings bar
        savingsBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Double val = (i * (estimateIncome / 100.0));
                //Toast.makeText(CreateVersion.this, val.toString(), Toast.LENGTH_SHORT).show();
                savingsEntry.setText("$");
                savingsEntry.append(val.toString());
                savingsVal = val;

                Integer savPer = seekBar.getProgress();
                savingsPercent.setText(savPer.toString());
                savingsPercent.append("%");


                Log.d(TAG, "savingsVal: " + savingsVal);

                balance = estimateIncome - essentialsVal - nonessentialsVal - savingsVal;
                Log.d(TAG, "balance: " + balance);


                // Log.d(TAG, "Balance at last try: " + balance);
                remainingBalance.setText("$");
                remainingBalance.append(balance.toString());
                if (balance < 0) {
                    remainingBalance.setTextColor(Color.RED);
                } else {
                    remainingBalance.setTextColor(Color.BLACK);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

//        Goal goal = new Goal(versionName);
//
//        AccountGroup accGroup = new AccountGroup(versionName);
//
//        Version version = new Version(versionName);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "SAVE BUTTON IS CLICKED");

                Goal goal_temp = new Goal(versionName);
                //AccountGroup acc_grp_temp = new AccountGroup(versionName);
                Version version_temp = new Version(versionName);

                //VersionViewModel
                versionViewModel.insertVersion(version_temp);

                //GoalViewModel
                goalViewModel.insertGoal(goal_temp);

                //AccountGroupViewModel
                //accountGroupViewModel.insertAccountGroup();

                Log.d(TAG, "VERSION, GOAL, ACC GROUP MADE");


                Log.d(TAG, "AFTER OBSERVE PLS");


                GoalDetail essential = new GoalDetail(goal_temp.getGoalName(), "Essential",
                        essentialsVal, version_temp.getVersionName());

                GoalDetail nonessential = new GoalDetail(goal_temp.getGoalName(), "NonEssential",
                        nonessentialsVal, version_temp.getVersionName());

                GoalDetail savings = new GoalDetail(goal_temp.getGoalName(), "Savings",
                        savingsVal, version_temp.getVersionName());

                GoalDetail income = new GoalDetail(goal_temp.getGoalName(), "Income",
                        estimateIncome, version_temp.getVersionName());

                GoalDetail expenses = new GoalDetail(goal_temp.getGoalName(), "Expenses",
                        estimateExpenses, version_temp.getVersionName());

                Log.d(TAG, "Goal name " + goal_temp.getGoalName());
               // Log.d(TAG, "Account Group Name  " + acc_grp_temp.getAccountGroupName());
                Log.d(TAG, "Essential amount " + essential.getAmount());
                Log.d(TAG, "Version Name " + version_temp.getVersionName());

                goalDetailViewModel.insertGoalDetail(essential);
                goalDetailViewModel.insertGoalDetail(nonessential);
                goalDetailViewModel.insertGoalDetail(savings);
                goalDetailViewModel.insertGoalDetail(income);
                goalDetailViewModel.insertGoalDetail(expenses);

                Log.d(TAG, "AFTER GOAL DETAIL INSERTION ");

                observeAC();

                observeGD();

                openDialog();
            }


        });

    }

    private void observeAC() {
        accountGroupViewModel.getAllAccountGroup().observe(this, new Observer<List<AccountGroup>>() {
            @Override
            public void onChanged(List<AccountGroup> accountGroups) {
                for(AccountGroup ac : accountGroups){
                    Log.d(TAG, "account Group name = " + ac.getAccountGroupName());
                }
            }
        });
    }
    //comment
    private void observeGD() {
        goalDetailViewModel.getAllGoalDetail().observe(this, new Observer<List<GoalDetail>>() {
            @Override
            public void onChanged(List<GoalDetail> goalDetails) {
                for(GoalDetail v : goalDetails){
                    Log.d(TAG, "goalDetails goal idd = " + v.getId());
                    Log.d(TAG, "amount  = $" + v.getAmount());
                }
            }
        });
    }



    //DIALOG BOX CODE
    public void openDialog(){
        CreateVersionDialog createVersionDialog = new CreateVersionDialog();
        createVersionDialog.show(getSupportFragmentManager(), "saved dialog box");
    }

    //TOOL BAR CODE
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}




