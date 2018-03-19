package com.example.foursage;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foursage.Adapter.Adapter.InformAdapter;
import com.example.foursage.Model.InformListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 袁凯明 on 2018/2/27.
 */

public class Inform_List extends android.support.v4.app.Fragment {
    private Context context;
    private List<InformListItem> informListItems=new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout_inform;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.inform_recyclerview,container,false);
        swipeRefreshLayout_inform=(SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayou_inform);
        swipeRefreshLayout_inform.setColorSchemeResources(R.color.swipeRefresh);
        swipeRefreshLayout_inform.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(Myapplication.getContext(),"informList刷新了",Toast.LENGTH_SHORT).show();
                swipeRefreshLayout_inform.setRefreshing(false);//表示刷新事件结束
            }
        });
        intInforms();
        RecyclerView inform_RV=(RecyclerView)view.findViewById(R.id.inform_recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        inform_RV.setLayoutManager(layoutManager);
        InformAdapter informAdapter=new InformAdapter(informListItems);
        inform_RV.setAdapter(informAdapter);
        return view;
    }
    public void intInforms(){
        for (int i=0;i<5;i++){
            InformListItem inform1=new InformListItem("http://www.iyi8.com/uploadfile/2017/0910/20170910110016346.jpg","小华","小美","笑死我了","2-3","从前有一个阿里巴巴和四十大盗的故事，",false);
            informListItems.add(inform1);
            InformListItem inform2=new InformListItem("http://www.iyi8.com/uploadfile/2017/0910/20170910110016346.jpg","小华","小美","笑死我了","2-3","http://www.iyi8.com/uploadfile/2017/0910/20170910110016346.jpg",true);
            informListItems.add(inform2);
            InformListItem inform3=new InformListItem("http://www.iyi8.com/uploadfile/2015/1009/20151009112223911.jpg","小华","小美","笑死我了","2-3","https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=7402741ab51bb0519b29bb7a5713b1d1/f603918fa0ec08fa8c486b9f5fee3d6d55fbdaba.jpg",true);
            informListItems.add(inform3);
        }
    }
}
