package com.example.rickypeng99.yugiohui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daasuu.ahp.AnimateHorizontalProgressBar;
import com.liuguangqiang.cookie.CookieBar;
import com.liuguangqiang.cookie.OnActionClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class MainActivity extends AppCompatActivity  {

    private FragmentManager fragmentManager;
    private CookieBar.Builder cookie;
    private CurrentFragment fragment1,fragment2,fragment3;
    public static String str = "";
    public static int hp1,hp2;
    private int count,health,dice,gameCount;
    public static String name1,name2,coin;

    public static ArrayList<Data> events = new ArrayList<Data>();
    private static boolean vic;
    static MyAdapter myAdapter;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (fragment1 == null){
                        fragment1 = new CurrentFragment();

                    }
                    fragment1.setWhich(0);
                    transaction.replace(R.id.content,fragment1);
                    transaction.commit();
                    return true;

                case R.id.navigation_dashboard:
                    if (fragment2 == null){
                        fragment2 = new CurrentFragment();

                    }
                    fragment2.setWhich(1);
                    transaction.replace(R.id.content,fragment2);
                    transaction.commit();
                    return true;

                case R.id.navigation_notifications:
                    if(fragment3 ==null){
                        fragment3 = new CurrentFragment();

                    }
                    fragment3.setWhich(2);
                    transaction.replace(R.id.content,fragment3);
                    transaction.commit();
                    return true;
            }
            return false;
        }

    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setDefaultFragment();

        //Basic variables
        name1 = "玩家1";
        name2 = "玩家2";
        health =0;
        hp1=8000;
        hp2=8000;
        count=0;
        gameCount=1;
        vic=false;
        coin="";

        cookie = new CookieBar.Builder(MainActivity.this);
        LayoutInflater inflater = LayoutInflater.from(this);

        View content = inflater.inflate(R.layout.list_main,null);




        //ListViews
        PullToZoomListView list = (PullToZoomListView) content.findViewById(R.id.list1);
        events.add(new Data("","",-1));
        myAdapter = new MyAdapter(this,events);
        list.setAdapter(myAdapter);
        recordArray("","",4);
        list.getHeaderView().setImageResource(R.drawable.yugi);
        list.getHeaderView().setScaleType(ImageView.ScaleType.CENTER_CROP);
        list.setShadow(R.drawable.shadow_bottom);


    }

    private void setDefaultFragment(){
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        fragment1 = new CurrentFragment();
        fragment1.setWhich(0);
        transaction.replace(R.id.content,fragment1);
        transaction.commit();
    }



    public void recordArray(String name, String status, int num) {
        for(int i=0;i<events.size();i++) {
            if (events.get(i).getPlayer()==-1)
                events.remove(i);
        }

        //Transforming systematic dates to string
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);

        //determine the player
        int flag=0;
        if(name.equals(name1)){
            flag=1;
        }
        else if(name.equals(name2)){
            flag=2;
        }


        //add new events (Data file)
        if(num==0)
            events.add(new Data("第"+count+"次操作："+name+status+health+"点生命值！", str,flag));
        else if(num==1)
            events.add(new Data("第"+count+"次操作："+name+"掷了"+dice+"点！", str,flag));

        else if(num==2)
            events.add(new Data("第"+count+"次操作："+name+"掷了硬币，是"+coin+"!", str,flag));

        else if(num==3)
            events.add(new Data("第"+count+"次操作："+name+"居然清除了战斗记录！", str,flag));

        else if(num==4)
            events.add(new Data("游戏已经开始！这是你们玩的第"+gameCount+"局", str,flag));


        myAdapter.setConstruct(arrayArrange());

        count++;
    }

    public  void getDice(){

        Random random  = new Random();
        dice = random.nextInt(6)+1;

    }
    public void getCoin(){
        Random random = new Random();
        int r = random.nextInt(2);
        if(r==1)coin="正面";
        else coin="反面";
    }

    public static void determineVic(){
        if(hp1<=0||hp2<=0){
            vic=true;


        }
    }


    public void alert(String name, int status){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(vic) {

            builder.setTitle("结果提示");
            setPositiveButton(builder);
            setNegativeButton(builder);
            builder.setMessage("决斗结束！" + name + "获胜！");
            builder.create();
            builder.setCancelable(false);
            builder.show();
            ////////////////////

        }

        else if(status==1){
            builder.setTitle("通知");
            setNormalButton(builder);
            builder.setMessage("一位玩家扔了一个骰子，结果为"+dice+"!");
            builder.create();
            builder.setCancelable(false);
            builder.show();
        }

        else if(status==2){
            builder.setTitle("通知");
            setNormalButton(builder);
            builder.setMessage("一位玩家扔了一枚硬币，结果为"+coin+"!");
            builder.create();
            builder.setCancelable(false);
            builder.show();
        }


    }

    private  AlertDialog.Builder setNormalButton(AlertDialog.Builder builder) {
        return builder.setPositiveButton("好的，我知道了", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }

            private  AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder){
        return builder.setPositiveButton("好的,再来一局！", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                events.clear();
                count=0;
                hp1=8000;
                hp2=8000;
                TextView tv = (TextView)findViewById(R.id.show);
                tv.setText(name1+"血量:"+ hp1);
                TextView tv2 = (TextView)findViewById(R.id.show2);
                tv2.setText(name2+"血量:"+ hp2);
                TextView tv3 = (TextView)findViewById(R.id.text);
                tv3.setText("");
                str="";

                AnimateHorizontalProgressBar progressBar = (AnimateHorizontalProgressBar)findViewById(R.id.bar1);
                AnimateHorizontalProgressBar progressBar2 = (AnimateHorizontalProgressBar)findViewById(R.id.bar2);

                progressBar.setProgressWithAnim(MainActivity.hp1);

                progressBar2.setProgressWithAnim(8000-MainActivity.hp2);
                vic=false;
                gameCount++;
                recordArray("",null,4);

            }
        });
    }

    private AlertDialog.Builder setNegativeButton(AlertDialog.Builder builder) {
        return builder.setNegativeButton("等一下。。（稍后自行重新开始)", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

    }


    public  ArrayList<Data> arrayArrange(){
        ArrayList<Data> array = new ArrayList<Data>();
        for(int i=events.size()-1;i>=0;i--){
            array.add(events.get(i));
        }
        return array;
    }

    public void myButtonOnClicked(View v)
    {
        switch(v.getId())
        {
            case R.id.num0:
                if(strOver())
                str+="0";
                break;
            case R.id.num1:
                if(strOver())
                str+="1";
                break;
            case R.id.num2:
                if(strOver())
                str+="2";
                break;
            case R.id.num3:
                if(strOver())
                str+="3";
                break;
            case R.id.num4:
                if(strOver())
                str+="4";
                break;
            case R.id.num5:
                if(strOver())
                str+="5";
                break;
            case R.id.num6:
                if(strOver())
                str+="6";
                break;
            case R.id.num7:
                if(strOver())
                str+="7";
                break;
            case R.id.num8:
                if(strOver())
                str+="8";
                break;
            case R.id.num9:
                if(strOver())
                str+="9";
                break;
            case R.id.num00:
                if(strOver())
                str+="00";
                break;
            case R.id.back:
                if(str.length()>0)
                    str = str.substring(0, str.length()-1);
                break;

        }
        TextView tv = (TextView)this.findViewById(R.id.text);
        tv.setText(str);
    }








        public void myButtonOnClicked2(View v) {

            TextView editText = (TextView) findViewById(R.id.text);

            if(editText.getText().equals("")){


                cookie.setTitle("警告");
                cookie.setMessage("请先输入数值！输入后才能进行加减血操作!");
                cookie.setBackgroundColor(R.color.red);
                cookie.setAction("我知道了", new OnActionClickListener() {
                    @Override
                    public void onClick() {
                    }
                });
                cookie.show();
                return;
            }

            health = Integer.parseInt(editText.getText().toString());


            switch (v.getId()){
                case R.id.but1plus:
                hp1+=health;
                recordArray(name1,"恢复了",0);
                TextView tv = (TextView) findViewById(R.id.show);
                tv.setText(name1+"血量:"+ hp1);
                    AnimateHorizontalProgressBar progressBar = (AnimateHorizontalProgressBar) findViewById(R.id.bar1);
                    progressBar.setProgressWithAnim(hp1);
                determineVic();


                break;

                case R.id.but2plus:
                hp2+=health;
                recordArray(name2,"恢复了",0);
                TextView tv2 = (TextView) findViewById(R.id.show2);
                tv2.setText(name2+"血量:"+ hp2);
                    AnimateHorizontalProgressBar progressBar2 = (AnimateHorizontalProgressBar) findViewById(R.id.bar2);
                    progressBar2.setProgressWithAnim(8000-MainActivity.hp2);
                determineVic();


                break;

                case R.id.but1minus:
                recordArray(name1,"失去了",0);
                hp1-=health;
                TextView tv3 = (TextView) findViewById(R.id.show);
                tv3.setText(name1+"血量:"+ hp1);
                    AnimateHorizontalProgressBar progressBar3 = (AnimateHorizontalProgressBar) findViewById(R.id.bar1);
                    progressBar3.setProgressWithAnim(hp1);
                determineVic();

                alert(name2,0);

                break;

                case R.id.but2minus:
                recordArray(name2,"失去了",0);
                hp2-=health;
                TextView tv4 = (TextView) findViewById(R.id.show2);
                tv4.setText(name2+"血量:"+ hp2);
                    AnimateHorizontalProgressBar progressBar4 = (AnimateHorizontalProgressBar) findViewById(R.id.bar2);
                    progressBar4.setProgressWithAnim(8000-MainActivity.hp2);
                determineVic();
                alert(name1,0);

                break;
        }
        }

    public void myButtonOnClicked3(View view){

        switch (view.getId()){

            case R.id.button3:
                getDice();
                alert("",1);
                recordArray("一位玩家",null,1);
                break;

            case R.id.button4:
                getCoin();
                alert("",2);
                recordArray("一位玩家",null,2);
                break;

            case R.id.button5:

                str="";
                TextView tv = (TextView) findViewById(R.id.text);
                tv.setText(str);
                break;

        }


    }

    public boolean strOver(){

        if(str.length()>=7){
            cookie.setTitle("警告");
            cookie.setMessage("请不要输入太大的数值!");
            cookie.setBackgroundColor(R.color.red);
            cookie.setAction("我知道了", new OnActionClickListener() {
                @Override
                public void onClick() {
                }
            });
            cookie.show();
            return false;
        }

        return true;

    }



    /*public static void  pressDetermine(View view, int type, Context context){



        if(type<=4) {
            editText = (TextView)view.findViewById(R.id.text);

            if(editText.getText().equals("")){


                cookie.setTitle("警告");
                cookie.setMessage("请先输入数值！输入后才能进行加减血操作!");
                cookie.setBackgroundColor(R.color.red);
                cookie.setAction("我知道了", new OnActionClickListener() {
                    @Override
                    public void onClick() {
                    }
                });
                cookie.show();
                return;
            }

            health = Integer.parseInt(editText.getText().toString());



            if (type == 1) {

                hp1 += health;//but1plus
                recordArray(name1, "恢复了", 0);
            } else if (type == 2) {
                hp1 -= health;//but1minus
                recordArray(name1, "失去了", 0);
                determineVic();
                alert(name2);
            } else if (type == 3) {
                hp2 += health;//but2plus
                recordArray(name2, "恢复了", 0);

            } else if (type == 4) {
                hp2 -= health;//but2minus
                recordArray(name2, "失去了", 0);
                determineVic();
                alert(name1);
            }

            TextView tv = (TextView) view.findViewById(R.id.show);
            TextView tv2 = (TextView) view.findViewById(R.id.show2);

            tv.setText(name1+"血量:"+ hp1);
            tv2.setText(name2+"血量:"+ hp2);


        }//end of the first if statement


        else{
            if(type==5){
                getDice();
                recordArray("一位玩家",null,1);
            }
            else if(type==6){
                getCoin();
                recordArray("一位玩家",null,2);

            }
            else if(type==7){
                count=1;
                events.clear();
                recordArray("一位玩家",null,3);
            }
        }// end of the else statement

    }*/



}
