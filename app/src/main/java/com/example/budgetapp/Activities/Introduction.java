package com.example.budgetapp.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.budgetapp.IntroAdapter;
import com.example.budgetapp.R;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

public class Introduction extends AppCompatActivity {

    //    private AppBarConfiguration appBarConfiguration;
//    private ActivityIntroductionBinding binding;
    ViewPager viewPager;
    TextView next;
    TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_introduction);

        viewPager = findViewById(R.id.view_pager);
        IntroAdapter adapter = new IntroAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        //next and skip options on the bottom
       next = (TextView) findViewById(R.id.intro_next);
       skip = (TextView) findViewById(R.id.intro_skip);

       //next goes down the list, to the next fragment, then goes to dashboard
        next.setOnClickListener(new View.OnClickListener()
        {
            int i = 0;
            @Override
            public void onClick(View view){
                viewPager.setCurrentItem(i);
                ++i;
                if(i > 4){
                    Intent intent = new Intent(Introduction.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        //skip, skips the whole fragment and goes straight to the dashboard
        skip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Introduction.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}