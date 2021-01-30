package com.ouwenbin.navigationdemo.NavigationUI.BottomNavigationView_ViewPager.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ouwenbin.navigationdemo.R;


public class FourFragment extends Fragment {


    public FourFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_four2, container, false);
    }

}
