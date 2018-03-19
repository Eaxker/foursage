package com.example.foursage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.foursage.Adapter.Adapter.NewsAdapter;
import com.example.foursage.Model.NewsListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 袁凯明 on 2018/2/25.
 */

public class News_List extends Fragment {

    private List<NewsListItem> newsListItems=new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout_news;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.news_recycleview,container,false);
        initNewsList();
        ListView listView_news=view.findViewById(R.id.listView_newsList);
        swipeRefreshLayout_news=(SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayou_news);
        swipeRefreshLayout_news.setColorSchemeResources(R.color.swipeRefresh);
        swipeRefreshLayout_news.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(Myapplication.getContext(),"刷新了",Toast.LENGTH_LONG).show();
                swipeRefreshLayout_news.setRefreshing(false);//表示刷新事件结束
            }
        });
//        /**
//         * 创建SwipeMenuCreator
//         */
//        SwipeMenuCreator creator = new SwipeMenuCreator() {
//
//            @Override
//            public void create(SwipeMenu menu) {
//
//                SwipeMenuItem openItem = new SwipeMenuItem(getContext());
//                openItem.setBackground(new ColorDrawable(Color.BLACK));
//                openItem.setWidth(dp2px(90));
//                openItem.setTitle("置顶");
//                openItem.setTitleSize(20);
//                openItem.setTitleColor(Color.WHITE);
//                menu.addMenuItem(openItem);
//                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext());
//                deleteItem.setBackground(new ColorDrawable(Color.RED));
//                deleteItem.setWidth(dp2px(90));
//                deleteItem.setTitle("删除");
//                deleteItem.setTitleSize(20);
//                deleteItem.setTitleColor(Color.WHITE);
//                menu.addMenuItem(deleteItem);
//                //可以加入图片
//                SwipeMenuItem deleteItem1 = new SwipeMenuItem(context);
//                deleteItem1.setBackground(new ColorDrawable(Color.LTGRAY));
//                deleteItem1.setWidth(dp2px(90));
//                deleteItem1.setIcon(android.R.drawable.ic_delete);
//                menu.addMenuItem(deleteItem1);
//            }
//        };

        /**
         * 实例化
         */
        SwipeMenuListView listView_newsList = (SwipeMenuListView)view.findViewById(R.id.listView_newsList);
//        listView_newsList.setMenuCreator(creator);
//        /**
//         * 监听事件
//         */
//        listView_newsList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
//                //index的值就是在SwipeMenu依次添加SwipeMenuItem顺序值，类似数组的下标。
//                //从0开始，依次是：0、1、2、3...
//                switch (index) {
//                    case 0:
//                        Toast.makeText(getContext(), "置顶:" + position, Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case 1:
//                        Toast.makeText(getContext(), "删除:" + position, Toast.LENGTH_SHORT).show();
//                        break;
//                }
//
//                // false : 当用户触发其他地方的屏幕时候，自动收起菜单。
//                // true : 不改变已经打开菜单的样式，保持原样不收起。
//                return false;
//            }
//        });
//        // 监测用户在ListView的SwipeMenu侧滑事件。
//        listView_newsList.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
//
//            @Override
//            public void onSwipeStart(int pos) {
//                Log.d("位置:" + pos, "开始侧滑...");
//            }
//
//            @Override
//            public void onSwipeEnd(int pos) {
//                Log.d("位置:" + pos, "侧滑结束.");
//            }
//        });
        /**
         * 绑定adapter
         */
        final NewsAdapter adapter = new NewsAdapter(getContext(), R.layout.newslist_item,newsListItems);
        listView_newsList.setAdapter(adapter);
        listView_newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsListItem news1=newsListItems.get(position);
                Toast.makeText(getContext(), news1.getName(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Myapplication.getContext(),SingleChatActivity.class);
                startActivity(intent);
                newsListItems.get(position).setUnreadnum("0");
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    private void initNewsList() {
        for (int i=0;i<5;i++){
            NewsListItem newsListItem1=new NewsListItem("http://www.iyi8.com/uploadfile/2017/0910/20170910110016346.jpg","小美","你好！","1:03","6");
            newsListItems.add(newsListItem1);
            NewsListItem newsListItem2=new NewsListItem("https://b-ssl.duitang.com/uploads/item/201411/19/20141119125117_jtJQs.thumb.700_0.jpeg","小梅","你好！","12:03","199");
            newsListItems.add(newsListItem2);
            NewsListItem newsListItem3=new NewsListItem("http://www.iyi8.com/uploadfile/2015/1009/20151009112223911.jpg","小芳","你是！","昨天","3");
            newsListItems.add(newsListItem3);
        }
    }

    public int dp2px(float dipValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
