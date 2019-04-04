package com.example.jsjx.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
RadioGroup rg;
RadioButton rb_1;
List<Fragment> list=new ArrayList<>();
int i=0;
Fragment content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
         initView();

     /*    if(rb_1.isChecked()){
             getSupportFragmentManager().beginTransaction().replace(R.id.fl,new FirstFragment()).commit();
         }*/
     list.add(new FirstFragment());
     list.add(new SecondFragment());
     list.add(new ThirdFragment());
     list.add(new FourthFragment());

         rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup group, int checkedId) {
                 switch (checkedId){
                     case R.id.rb_1:
                       /* getSupportFragmentManager().beginTransaction().replace(R.id.fl,new FirstFragment()).commit();*/
                       //list.get(index);
                       i=0;
                       break;
                     case R.id.rb_2:
                         i=1;
                         break;
                     case R.id.rb_3:
                         i=2;
                         break;
                     case R.id.rb_4:
                        i=3;
                         break;
                 }
             }
         });
  /*      rb_1.setChecked(true);
        manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.fl,list.get(i)).commit();*/
        Fragment fragment=list.get(i);
        switchFragment(content,fragment);
    }

    private void initView() {
        rg=findViewById(R.id.rg);
        rb_1=findViewById(R.id.rb_1);
     }
     private void switchFragment(Fragment from,Fragment to){
        if(from!=to){
            content=to;
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            if(!to.isAdded()){
                if(from!=null){
                   ft.hide(from);
                }
                if(to!=null){
                    ft.add(R.id.fl,to).commit();
                }
            }else{
                if(from!=null){
                    ft.hide(from);
                }
                if(to!=null){
                    ft.show(to).commit();
                }
            }
        }
     }
}
