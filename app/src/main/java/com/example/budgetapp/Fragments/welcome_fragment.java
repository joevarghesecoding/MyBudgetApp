package com.example.budgetapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.budgetapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link welcome_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class welcome_fragment extends Fragment {

    TextView next;
    TextView skip;

    ViewPager viewPager;
    
    public welcome_fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome_fragment, container, false);


        //Initialize ViewPager from Introduction activity.
        viewPager = getActivity().findViewById(R.id.view_pager);
        next = view.findViewById(R.id.intro_next);
        skip = view.findViewById(R.id.intro_skip);

//        next.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view){
//                viewPager.setCurrentItem(1);
//            }
//        });

//        skip.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view){
//                startDashboard(view);
//            }
//        });

        return view;
    }
//    public void startDashboard(View view) {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }
}