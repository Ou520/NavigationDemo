package com.ouwenbin.navigationdemo.NavigationUI.BottomNavigationView_ViewPager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ouwenbin.navigationdemo.NavigationUI.BottomNavigationView_ViewPager.fragment.FourFragment;
import com.ouwenbin.navigationdemo.NavigationUI.BottomNavigationView_ViewPager.fragment.OneFragment;
import com.ouwenbin.navigationdemo.NavigationUI.BottomNavigationView_ViewPager.fragment.ThreeFragment;
import com.ouwenbin.navigationdemo.NavigationUI.BottomNavigationView_ViewPager.fragment.TwoFragment;
import com.ouwenbin.navigationdemo.R;


/*
 * NavigationUI的使用
 * 地址：https://zhuanlan.zhihu.com/p/69653419?from_voters_page=true
 * */

public class Bnv_ViewPagerActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private Fragment One = new OneFragment();
    private Fragment Two = new TwoFragment();
    private Fragment Three = new ThreeFragment();
    private Fragment Four = new FourFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bnv__view_pager);

        initView();
        initListener();

    }




    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        navigationView = findViewById(R.id.nav_view2);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListener() {

        //监听viewPager页面变化
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                navigationView.getMenu().getItem(position).setChecked(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        //禁止ViewPager滑动
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

        //BottomNavigationView的点击监听
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                // menu/menu.xml里加的android:orderInCategory属性就是下面item.getOrder()取的值
                viewPager.setCurrentItem(menuItem.getOrder());
                return true;
            }
        });

        //ViewPager的适配器
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return One;
                    case 1:
                        return Two;
                    case 2:
                        return Three;
                    case 3:
                        return Four;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });

    }

}
