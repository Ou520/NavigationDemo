package com.ouwenbin.navigationdemo.NavigationUI.BottomNavigationView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ouwenbin.navigationdemo.R;


/*
* NavigationUI的使用
* 地址：https://zhuanlan.zhihu.com/p/69653419?from_voters_page=true
* */

public class BottomNavigationActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        initView();
    }

    private void initView() {
        bottomNavigationView= findViewById(R.id.bottomNavigationView);

        //创建导航控制器
        NavController controller = Navigation.findNavController(this,R.id.fragment2);
        //创建标题栏配置的对象
        AppBarConfiguration configuration = new  AppBarConfiguration.Builder(bottomNavigationView.getMenu()).build();
        //添加标题栏
        NavigationUI.setupActionBarWithNavController(this,controller,configuration);
        //使底部导航栏与导航控制器建立绑定关系
        NavigationUI.setupWithNavController(bottomNavigationView,controller);


    }
}
