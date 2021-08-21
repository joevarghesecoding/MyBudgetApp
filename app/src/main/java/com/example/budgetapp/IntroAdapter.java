package com.example.budgetapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.budgetapp.Fragments.choose_goals_fragment;
import com.example.budgetapp.Fragments.fifty_fragment;
import com.example.budgetapp.Fragments.instructions_fragment;
import com.example.budgetapp.Fragments.welcome_fragment;

public class IntroAdapter extends FragmentPagerAdapter {

    public IntroAdapter(FragmentManager fm){
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new welcome_fragment();
            case 1:
                return new instructions_fragment();
            case 2:
                return new choose_goals_fragment();
            case 3:
                return new fifty_fragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
