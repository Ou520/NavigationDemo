package com.ouwenbin.navigationdemo.NavigationUI.BottomNavigationView.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ouwenbin.navigationdemo.R;


public class SecondFragment extends Fragment {


    public SecondFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_second, container, false);
    }

}
