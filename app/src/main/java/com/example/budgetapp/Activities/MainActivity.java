package com.example.budgetapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.budgetapp.DataManager.Model.Version;
import com.example.budgetapp.R;
import com.example.budgetapp.ViewModel.AccountGroupViewModel;
import com.example.budgetapp.ViewModel.GoalDetailViewModel;
import com.example.budgetapp.ViewModel.GoalViewModel;
import com.example.budgetapp.ViewModel.VersionViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    AnyChartView anyChartView;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private EditText available;


    String[] financeTypes = {"Income", "Expenses"};
    int[] financeValues = {300, 500};

    //DATABASE

    public static GoalViewModel goalViewModel;
    public static GoalDetailViewModel goalDetailViewModel;
    public static VersionViewModel versionViewModel;
    public static AccountGroupViewModel accountGroupViewModel;
    public static List<Version> versionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        //DATABASE SETUP
        goalViewModel = new ViewModelProvider(this).get(GoalViewModel.class);
        goalDetailViewModel = new ViewModelProvider(this).get(GoalDetailViewModel.class);
        versionViewModel = new ViewModelProvider(this).get(VersionViewModel.class);
        accountGroupViewModel = new ViewModelProvider(this).get(AccountGroupViewModel.class);

        versionList = new ArrayList<>();

        //Buttons and EditText set up
        available = (EditText) findViewById(R.id.dashboard_editText);

        //SIDEBAR MENU
        toolbar = findViewById(R.id.main_menu_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openNavDrawer, R.string.closeNavDrawer);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        //ACTION BAR TITLE
        setActionBar("Dashboard");


        //PIE CHART
        anyChartView = findViewById(R.id.any_chart_view);

        setupPieChart();


        //set up buttons
        //Button button_view_version = (Button) findViewById(R.id.button_view_version);

//        View.OnClickListener page_listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Button b = (Button) view;
//                //startViewVersion(b);
//            }
//        };
//
//        button_view_version.setOnClickListener(page_listener);


        //Available to spend set up


    }

    public void setupPieChart() {

        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();

        for(int i = 0 ; i < financeTypes.length; i++) {
            dataEntries.add(new ValueDataEntry(financeTypes[i], financeValues[i]));
        }

        pie.data(dataEntries);
        anyChartView.setChart(pie);
    }

//    public void startViewVersion(View view) {
//        Intent intent = new Intent(this, ViewVersion.class);
//        startActivity(intent);
//    }

    //THIS FUNCTION NAVIGATES THROUGH NAV BAR
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

    //@Override
    public void setActionBar(String heading){
        ActionBar actionBar = getSupportActionBar();
       //actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.outline_menu_white_48);
       //actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        actionBar.setTitle(heading);
        actionBar.show();
    }


}