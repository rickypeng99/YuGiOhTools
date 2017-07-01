package ricky.example.yugiohui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daasuu.ahp.AnimateHorizontalProgressBar;
import com.tomer.fadingtextview.FadingTextView;


/**
 * Created by Ricky on 2017/6/16.
 */

public class CurrentFragment extends android.app.Fragment {

    private int which=0;
    private View contentView;





    public void setWhich(int which) {
        this.which = which;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(which==0){
          contentView = inflater.inflate(R.layout.content_main,null);
            TextView tv = (TextView) contentView.findViewById(R.id.show);
            TextView tv2 = (TextView) contentView.findViewById(R.id.show2);
            TextView tv3 = (TextView) contentView.findViewById(R.id.text);
            ImageView iv = (ImageView) contentView.findViewById(R.id.imageMain);
            ImageView iv2 =(ImageView) contentView.findViewById(R.id.imageMain2);
            AnimateHorizontalProgressBar progressBar = (AnimateHorizontalProgressBar) contentView.findViewById(R.id.bar1);
            AnimateHorizontalProgressBar progressBar2 = (AnimateHorizontalProgressBar) contentView.findViewById(R.id.bar2);

            progressBar.setMax(8000);
            progressBar.setProgressWithAnim(MainActivity.hp1);
            progressBar2.setMax(8000);
            progressBar2.setProgress(8000);

            progressBar2.setProgressWithAnim(8000-MainActivity.hp2);

            tv.setText(MainActivity.name1+"血量:"+ MainActivity.hp1);
            tv2.setText(MainActivity.name2+"血量:"+ MainActivity.hp2);
            tv3.setText(MainActivity.str);
            iv.setImageResource(R.drawable.jiangzemin1);
            iv2.setImageResource(R.drawable.jiangzemin2);


        }
        else if(which==1) {
            contentView = inflater.inflate(R.layout.list_main, null);

            PullToZoomListView list = (PullToZoomListView) contentView.findViewById(R.id.list1);
            list.setAdapter(MainActivity.myAdapter);
            list.getHeaderView().setImageResource(R.drawable.splash);
            list.getHeaderView().setScaleType(ImageView.ScaleType.CENTER_CROP);
            list.setShadow(R.drawable.shadow_bottom);
        }


        else if(which==2){
            contentView = inflater.inflate(R.layout.intro_main,null);

            String[] texts = {"程序设计","功能设计","后台开发","交互设计"};
            FadingTextView FTV = (FadingTextView) contentView.findViewById(R.id.fadingTextView);
            FTV.setTexts(texts); //You can use an array resource or a string array as the parameter

            String[] texts2 = {"彭瑞其","彭瑞其","彭瑞其","彭瑞其"};
            FadingTextView FTV2 = (FadingTextView) contentView.findViewById(R.id.fadingTextView2);
            FTV2.setTexts(texts2);



        }


        return contentView;
    }






}


/*  //button1
            Button but1 = (Button) contentView.findViewById(R.id.but1plus);
            but1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.pressDetermine(contentView,1,getActivity());
                }
            });

            //button2
            Button but2 = (Button) contentView.findViewById(R.id.but1minus);
            but2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.pressDetermine(contentView,2,getActivity());
                }
            });

            //button3
            Button but3 = (Button) contentView.findViewById(R.id.but2plus);
            but3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.pressDetermine(contentView,3,getActivity());
                }
            });

            //button4
            Button but4 = (Button) contentView.findViewById(R.id.but2minus);
            but4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.pressDetermine(contentView,4,getActivity());
                }
            });

            //button5
            Button but5 = (Button) contentView.findViewById(R.id.button3);
            but5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.pressDetermine(contentView,5,getActivity());
                }
            });

            //button6
            Button but6 = (Button) contentView.findViewById(R.id.button4);
            but6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.pressDetermine(contentView,6,getActivity());
                }
            });

            //button7
            Button but7 = (Button) contentView.findViewById(R.id.button5);
            but7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.pressDetermine(contentView,7,getActivity());
                }
            });*/