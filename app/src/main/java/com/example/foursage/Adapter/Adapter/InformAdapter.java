package com.example.foursage.Adapter.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foursage.Model.InformListItem;
import com.example.foursage.Model.NewsListItem;
import com.example.foursage.Myapplication;
import com.example.foursage.R;

import java.util.List;

/**
 * Created by 袁凯明 on 2018/2/28.
 */

public class InformAdapter extends RecyclerView.Adapter<InformAdapter.ViewHolder>{


    private List<InformListItem> informListItems;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View informView;
        ImageView head_IV;
        TextView name1_TV;
        TextView name2_TV;
        TextView inform_TV;//评论的内容
        TextView related_content;//相关的图片或内容文字
        TextView time_TV;
        ImageView related_picture_IV;
        public ViewHolder(View view){
            super(view);
            informView=view;
            head_IV=(ImageView)view.findViewById(R.id.infor_head);
            name1_TV=(TextView)view.findViewById(R.id.name1);
            name2_TV=(TextView)view.findViewById(R.id.name2);
            inform_TV=(TextView)view.findViewById(R.id.inform_content);
            related_content=(TextView)view.findViewById(R.id.related_content);
            time_TV=(TextView)view.findViewById(R.id.inform_time);
            related_picture_IV=(ImageView)view.findViewById(R.id.related_picture);
        }
    }
    public InformAdapter(List<InformListItem> informListItems){
        this.informListItems=informListItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.informlist_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        InformListItem informListItem=informListItems.get(position);
        String head_uri=informListItem.getHeadurl();
        Glide.with(Myapplication.getContext()).load(head_uri).into(holder.head_IV);//设置头像
        holder.name1_TV.setText(informListItem.getName1());
        holder.name2_TV.setText(informListItem.getName2());
        holder.inform_TV.setText(informListItem.getInform());
        holder.time_TV.setText(informListItem.getContent_time());
        if (informListItem.getTu().equals(true)){//是图
            String  imageUri=informListItem.getAnother_content();
//        String imageUri = "http://www.iyi8.com/uploadfile/2017/0910/20170910110016346.jpg";
            Glide.with(Myapplication.getContext()).load(imageUri).into(holder.related_picture_IV);//
            holder.related_picture_IV.setVisibility(View.VISIBLE);//设置图片可见
            holder.related_content.setVisibility(View.GONE);        //设置相关文字隐藏
        }else {
            holder.related_content.setText(informListItem.getAnother_content());//设置文字内容
            holder.related_content.setVisibility(View.VISIBLE);                 //设置文字可见
            holder.related_picture_IV.setVisibility(View.GONE);                 //设置图片隐藏
        }
    }

    @Override
    public int getItemCount() {
        return informListItems.size();
    }
    public void setData(List<InformListItem> informList){
        this.informListItems=informList;
    }
}
