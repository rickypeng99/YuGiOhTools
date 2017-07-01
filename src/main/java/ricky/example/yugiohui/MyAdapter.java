package ricky.example.yugiohui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ricky on 2017/6/12.
 * This is an adapter that will be constructing an architecture for the listView widget
 *
 */

public class MyAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Data> datas;
    LayoutInflater inflater;

    public MyAdapter(Context context, ArrayList<Data> datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);


    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.simple_item, null);


        if(datas.get(position).getContent().contains(MainActivity.name2)) {
            layout = (LinearLayout) inflater.inflate(R.layout.simple2_item, null);

        }

        ImageView imgView = (ImageView) layout.findViewById(R.id.image);
        TextView text = (TextView) layout.findViewById(R.id.event);
        TextView text2 = (TextView) layout.findViewById(R.id.event2);

        if(datas.get(position).getPlayer()==1) {
            imgView.setImageResource(R.drawable.jiangzemin1);
        }
        else if(datas.get(position).getPlayer()==2)
            imgView.setImageResource(R.drawable.jiangzemin2);


        text.setText(datas.get(position).getContent());//战斗日志
        text2.setText(datas.get(position).getTime());//操作时间





        return layout;
    }

    public void setConstruct( ArrayList<Data> datas) {

        this.datas=datas;
    }


}
