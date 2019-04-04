package com.example.jsjx.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class viewpager_Fragment03 extends Fragment {
    ListView lv;
    List<Student> list;
    TextView tv1,tv2,tv3;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.viewpager_fragment03,null);
        lv=view.findViewById(R.id.lv);
        initData();
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return list.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                 View view1=LayoutInflater.from(getActivity()).inflate(R.layout.mulu,null);
                 tv1=view1.findViewById(R.id.tv1);
                 tv2=view1.findViewById(R.id.tv2);
                 tv3=view1.findViewById(R.id.tv3);
                 tv1.setText(list.get(position).getBanji());
                 tv2.setText(list.get(position).getName());
                 tv3.setText(list.get(position).getAge()+"");
                return view1;
            }
        });
        return view;
    }

    private void initData() {
       list=new ArrayList<>();
      list.add(new Student("开发a1701","小王",18));
      list.add(new Student("开发a1701","小郝",18));
      list.add(new Student("开发a1701","小李",18));
      list.add(new Student("开发a1701","小杜",18));
      list.add(new Student("开发a1701","小郭",18));
      list.add(new Student("开发a1701","小段",18));
    }
}
