package com.example.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SpinnerLianxi extends AppCompatActivity {
    private Spinner sp1,sp2;
    String[] provinces=new String[]{"山西省","陕西省"};
    String[][] citys=new String[][]{{"太原","长治","大同"},{"西安","咸阳","宝鸡"}};
    ArrayAdapter<String> provincesAdapter,citysAdapter;
    int provincePosition;
    TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_lianxi);
        initView();
        provincesAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,provinces);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                citysAdapter=new ArrayAdapter<String>(SpinnerLianxi.this,android.R.layout.simple_list_item_1,citys[position]);
                 provincePosition=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_show.setText(provinces[provincePosition]+citys[provincePosition][position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void initView() {
      sp1=findViewById(R.id.sp1);
      sp2=findViewById(R.id.sp2);
      tv_show=findViewById(R.id.tv_show);
    }
}
