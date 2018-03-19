package com.example.foursage.Adapter.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.foursage.Model.NewsListItem;
import com.example.foursage.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 袁凯明 on 2018/2/25.
 */

public class NewsAdapter extends ArrayAdapter<NewsListItem> {
    private int resourceId;
    private List<NewsListItem> newList;
    public NewsAdapter(Context context, int textViewResourceId, List<NewsListItem> newsList) {
        super(context, textViewResourceId, newsList);
        resourceId = textViewResourceId;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        NewsListItem newsListItem = getItem(position);
        View view;
        ViewHolder viewHolder;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder=new ViewHolder();
            viewHolder.head=(ImageView)view.findViewById(R.id.head);
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.lastnews = (TextView) view.findViewById(R.id.lastnews);
            viewHolder.lasttime=(TextView)view.findViewById(R.id.lasttime);
            viewHolder.newsnum=(TextView)view.findViewById(R.id.newsnum);
            view.setTag(viewHolder);//将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder=(ViewHolder)view.getTag();//重新获取ViewHolder
        }
        String  imageUri=newsListItem.getHeadurl();
//        String imageUri = "http://www.iyi8.com/uploadfile/2017/0910/20170910110016346.jpg";
        Glide.with(getContext()).load(imageUri).into(viewHolder.head);
//        int imageReSourceId=R.drawable.head1;
//        Glide.with(getContext()).load(imageReSourceId).into(viewHolder.head);
        viewHolder.name.setText(newsListItem.getName());
        viewHolder.lastnews.setText(newsListItem.getLastnews());
        viewHolder.lasttime.setText(newsListItem.getLasttime());
        if (newsListItem.getUnreadnum()=="0") {
            viewHolder.newsnum.setText(newsListItem.getUnreadnum());
            viewHolder.newsnum.setVisibility(View.GONE);
        }else if (Integer.parseInt(newsListItem.getUnreadnum())>99){
            viewHolder.newsnum.setText("99+");
        }else {
            viewHolder.newsnum.setText(newsListItem.getUnreadnum());
        }
        return view;
    }

    class ViewHolder {
        ImageView head;
        TextView name;
        TextView lastnews;
        TextView lasttime;
        TextView newsnum;

    }
    public void setData(List<NewsListItem> newsList){
        this.newList=newsList;
    }

}

