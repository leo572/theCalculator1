package com.example.thecalculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    String[] str=new String[]{"进入","欢迎"};
    List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tabLayout=findViewById(R.id.tabs);
        viewPager=findViewById(R.id.viewpager2);
        initFragment();
        viewPager.setAdapter(new myAdapter(getSupportFragmentManager(),fragments));
        tabLayout.setupWithViewPager(viewPager);
    }
    private void initFragment(){
        fragments=new ArrayList<>();
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
    }
    private class myAdapter extends FragmentPagerAdapter {
        private List<Fragment> list;
        public myAdapter(@NonNull FragmentManager fm, List<Fragment>list) {
            super(fm);
            this.list=list;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return str[position];
        }
    }
}
