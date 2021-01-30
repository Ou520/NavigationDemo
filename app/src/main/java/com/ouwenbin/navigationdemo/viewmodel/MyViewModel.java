package com.ouwenbin.navigationdemo.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private MutableLiveData<String> text;

    public MyViewModel(){ }

    public MutableLiveData<String> getText(){

        //初始化text
        if (text == null){
            text = new MutableLiveData<>();
            text.setValue("OneFragment");
        }
        return text;
    }


    public void add(String s) {

        getText().setValue(s);
    }
}
