package com.ouwenbin.navigationdemo.viewmodel;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ouwenbin.navigationdemo.R;
import com.ouwenbin.navigationdemo.databinding.FragmentTwoBinding;


public class TwoFragment extends Fragment {

    private MyViewModel model;
    private FragmentTwoBinding binding;

    public TwoFragment() { }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //初始化ViewMode
        model = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
        //获取DataBinding
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_two,container,false);
        //使ViewMode和DataBinding建立绑定
        binding.setData(model);
        //监听数据的变化
        binding.setLifecycleOwner(requireActivity());

        initView(binding);

        return binding.getRoot();
    }

    private void initView(FragmentTwoBinding binding) {


        //跳转页面
        binding.btnTwo.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_twoFragment_to_homeFragment));

    }


}
