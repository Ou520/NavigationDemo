package com.ouwenbin.navigationdemo.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ouwenbin.navigationdemo.R;
import com.ouwenbin.navigationdemo.viewmodel.MyViewModel;


public class HomeFragment extends Fragment {

    private Button btn;
    private Button btn2;
    private EditText ed_main;
    private MyViewModel model;


    //构造方法
    public HomeFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initListener();
    }



    private void initView() {

        btn = getView().findViewById(R.id.btn_main);
        btn2 = getView().findViewById(R.id.btn_main2);
        ed_main = getView().findViewById(R.id.ed_main);
    }


    private void initListener() {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //获取EditText输入的内容
                String text = ed_main.getText().toString();

                if (text.equals("")){
                    Toast.makeText(getActivity(), "请输入内容！", Toast.LENGTH_SHORT).show();
                }else {

                    //使用Bundle包装要传递的数据
                    Bundle bundle = new Bundle();
                    bundle.putString("name_key",text);

                    //跳转页面并传递数据(bundle)
                    NavController controller = Navigation.findNavController(view);
                    controller.navigate(R.id.action_homeFragment_to_oneFragment,bundle);

                }

            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //获取EditText输入的内容
                String text = ed_main.getText().toString();
                if (!text.equals("")){

                    //使用ViewModel来实现数据的传递
                    model = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
                    model.add(text);
                }

                //跳转页面
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_homeFragment_to_twoFragment);
            }
        });

    }

}
