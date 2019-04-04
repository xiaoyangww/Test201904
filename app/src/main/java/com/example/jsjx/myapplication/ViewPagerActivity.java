package com.example.jsjx.myapplication;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {
ViewPager pager;
List<Fragment> list;
TabLayout tab;
List<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        initData();
        initView();

    }

    private void initView() {
       pager=findViewById(R.id.vp);
       tab=findViewById(R.id.tab);
       pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
           @Override
           public Fragment getItem(int i) {
               return list.get(i);
           }

           @Override
           public int getCount() {
               return list.size();
           }

           @Nullable
           @Override
           public CharSequence getPageTitle(int position) {
               return titles.get(position);
           }
       });
       tab.setupWithViewPager(pager);
    }

    private void initData() {
       list=new ArrayList<>();
       list.add(new viewpager_Fragment01());
       list.add(new viewpager_Fragment02());
       list.add(new viewpager_Fragment03());

       titles=new ArrayList<>();
       titles.add("新闻");
       titles.add("视频");
       titles.add("游戏");
       }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
