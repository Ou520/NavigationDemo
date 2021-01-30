package com.ouwenbin.navigationdemo.NavigationUI.BottomNavigationView.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ouwenbin.navigationdemo.R;


public class ThirdFragment extends Fragment {


    public ThirdFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_third, container, false);
    }

}
