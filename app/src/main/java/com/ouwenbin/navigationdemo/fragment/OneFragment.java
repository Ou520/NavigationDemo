package com.ouwenbin.navigationdemo.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ouwenbin.navigationdemo.R;

import org.w3c.dom.Text;


public class OneFragment extends Fragment {

    private TextView tv_one;

    public OneFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();


    }

    private void initView() {

        String HomeToData =  getArguments().getString("name_key");

        tv_one = getView().findViewById(R.id.tv_one);
        tv_one.setText(HomeToData);

        //按钮
        getView().findViewById(R.id.btn_one)
                //跳转页面
                .setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_oneFragment_to_homeFragment));

    }

}
