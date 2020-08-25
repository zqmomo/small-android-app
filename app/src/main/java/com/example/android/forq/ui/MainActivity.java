package com.example.android.forq.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.android.forq.R;
import com.example.android.forq.base.BaseActivity;
import com.example.android.forq.base.BasePresenter;
import com.example.android.forq.ui.fragment.GkxxFragment;
import com.example.android.forq.ui.fragment.GrzxFragment;
import com.example.android.forq.ui.fragment.JykxFragment;
import com.example.android.forq.ui.fragment.WdjyFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<BasePresenter> {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private List<Fragment> myFragments = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();

        //主界面四个页面的初始化
            myFragments.add(new GkxxFragment());
            myFragments.add(new JykxFragment());
            myFragments.add(new WdjyFragment());
            myFragments.add(new GrzxFragment());
            // 设置viewpager能缓存多少个页面
            viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


            @Override
            public int getCount() {
                return myFragments.size();
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return myFragments.get(position);
            }

        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 底部选中
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                // 页面弹窗判断
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int pos = 0;
            switch (item.getItemId()) {
                case R.id.tab_one:
                    break;
                case R.id.tab_two:
                    pos = 1;
                    break;
                case R.id.tab_three:
                    pos = 2;
                    break;
                case R.id.tab_four:
                    pos = 3;
                    break;
            }
            viewPager.setCurrentItem(pos);
            return true;
        });
    }

    private void initView() {
        viewPager = findViewById(R.id.view_pager_main);
        bottomNavigationView = findViewById(R.id.bottom_main);
    }


    @Override
    public com.example.android.forq.base.BasePresenter getPresenter() {
        return new com.example.android.forq.base.BasePresenter() {
            @Override
            public void unsubscribe() {

            }
        };
    }
}
