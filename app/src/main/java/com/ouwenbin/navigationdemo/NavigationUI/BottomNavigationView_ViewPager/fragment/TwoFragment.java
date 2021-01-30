package com.ouwenbin.navigationdemo.NavigationUI.BottomNavigationView_ViewPager.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ouwenbin.navigationdemo.R;


public class TwoFragment extends Fragment {


    public TwoFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_two2, container, false);
    }

}
