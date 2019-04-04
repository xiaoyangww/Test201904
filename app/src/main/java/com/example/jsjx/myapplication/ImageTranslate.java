package com.example.jsjx.myapplication;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class ImageTranslate extends AppCompatActivity {
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_translate);
        initView();
        img.setOnTouchListener(new OnTouchListener());
    }

    private void initView() {
        img = findViewById(R.id.iv_translate);
    }

    class OnTouchListener implements View.OnTouchListener {
        public int mode=0;
        public int move=1;
        public int scale=2;
        Matrix matrix = new Matrix();
        Matrix savedMatrix = new Matrix();
        //第一个按下的手指的点
        PointF startPointF = new PointF();
        //两个按下的手指点的中点
        PointF midPointF=new PointF();
        //初始的两个手指按下的距离
        float oriDis=1f;
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    //单点触控
                    mode=move;
                    savedMatrix.set(img.getImageMatrix());
                    startPointF.set(event.getX(), event.getY());
                    break;
                case MotionEvent.ACTION_MOVE:
                    //手指滑动

                    if(mode==move){
                        //一个手指滑动

                        float dx = event.getX() - startPointF.x;
                        float dy = event.getX() - startPointF.y;
                     matrix.set(savedMatrix);
                     matrix.postTranslate(dx,dy);
                    }else if(mode==scale){
                       //两个手指滑动

                        float newDist=distance(event);
                        if(newDist>10f){
                            matrix.set(savedMatrix);
                            float suofang =newDist/oriDis;
                            matrix.postScale(suofang,suofang,midPointF.x,midPointF.y);
                        }
                    }

                    break;
                case MotionEvent.ACTION_UP:
                    mode=0;
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    //多点触控
                    oriDis=distance(event);
                    if(oriDis>10f){
                        savedMatrix.set(img.getImageMatrix());
                        midPointF=midPointF(event);
                        mode=scale;
                    }

                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    //手指放开事件

                    mode=0;
                    break;
            }
            img.setImageMatrix(matrix);
            return true;
        }
    /*
    *  计算两个手指头之间的中心点的位置
    *   x=(x1+x2)/2
    *   y=(y1+y2)/2
    *
    * */
        private PointF midPointF(MotionEvent event) {
            float x=(event.getX(1)+event.getX(0))/2;
            float y=(event.getY(1)+event.getY(0))/2;
            return new PointF(x,y);  //返回两个手指之间的距离
        }

        /*
        *   计算两个手指间的距离
        * */
        private float distance(MotionEvent event) {
            float x=event.getX(1)-event.getX(0);
            float y=event.getY(1)-event.getY(0);
            return (float) Math.sqrt(x*x+y*y);  //返回两个手指之间的距离
        }
    }
}
