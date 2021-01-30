package com.ouwenbin.navigationdemo.NavigationUI.BottomNavigationView_ViewPager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ouwenbin.navigationdemo.R;

public class OneFragment extends Fragment {



    public OneFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one2, container, false);
    }

}
