package com.example.jsjx.myapplication;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class viewpager_Fragment02 extends Fragment {
    ImageView img;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_fragment02, null);
        img = view.findViewById(R.id.iv_translate1);
        img.setOnTouchListener(new OnTouchListener());
        return view;
    }

    class OnTouchListener implements View.OnTouchListener {
        public int mode = 0;
        public int move = 1;
        public int scale = 2;
        Matrix matrix = new Matrix();
        Matrix savedMatrix = new Matrix();
        //第一个按下的手指的点
        PointF startPointF = new PointF();
        //两个按下的手指点的中点
        PointF midPointF = new PointF();
        //初始的两个手指按下的距离
        float oriDis = 1f;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    //单点触控

                    mode = move;
                    matrix.set(img.getImageMatrix());
                    savedMatrix.set(matrix);
                    startPointF.set(event.getX(), event.getY());
                    break;
                case MotionEvent.ACTION_MOVE:
                    //手指滑动

                    if (mode == move) {
                        //一个手指滑动

                        float dx = event.getX() - startPointF.x;
                        float dy = event.getX() - startPointF.y;
                        savedMatrix.set(matrix);
                        savedMatrix.postTranslate(dx, dy);
                    } else if (mode == scale) {
                        //两个手指滑动

                        float newDist = distance(event);
                        if (newDist > 10f) {
                            savedMatrix.set(matrix);
                            float suofang = newDist / oriDis;
                            savedMatrix.postScale(suofang, suofang, midPointF.x, midPointF.y);
                        }
                    }

                    break;
                case MotionEvent.ACTION_UP:
                    mode = 0;
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    mode=scale;
                    //多点触控
                    oriDis = distance(event);
                    if (oriDis > 10f) {
                        savedMatrix.set(matrix);
                        midPointF = midPointF(event);
                    }

                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    //手指放开事件
                    mode = 0;
                    break;
            }
            img.setImageMatrix(savedMatrix);
            return true;
        }
        /*
         *  计算两个手指头之间的中心点的位置
         *   x=(x1+x2)/2
         *   y=(y1+y2)/2
         *
         *
         * */
        private PointF midPointF(MotionEvent event) {
            float x=(event.getX(1)+event.getX(0))/2;
            float y=(event.getY(1)+event.getY(0))/2;
            return new PointF(x,y);  //返回中心点的坐标
        }


        /*
         *  计算两个手指之间的距离
         *
         *
         * */
        private float distance(MotionEvent event) {
            float x=event.getX(1)-event.getX(0);
            float y=event.getY(1)-event.getY(0);
            return (float) Math.sqrt(x*x+y*y);  //返回两个手指之间的距离
        }
    }
}
