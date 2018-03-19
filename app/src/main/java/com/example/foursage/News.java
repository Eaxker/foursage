package com.example.foursage;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;


/**
 * Created by 袁凯明 on 2018/2/11.
 */

public class News extends android.support.v4.app.Fragment {
    private Toolbar toolbar_news=null;
    private FrameLayout news_fl=null;
    private TextView big_TV=null;
    private TextView small_TV=null;
    private TextView bignum_TV=null;
    private TextView smallnum_TV=null;
    private FrameLayout inform_fl=null;
    private String bigshow=null;
    private String smallshow=null;
    private String bignum=null;
    private String smallnum=null;
    FragmentManager fragmentManager;

    android.support.v4.app.FragmentTransaction fragmentTransaction=null;

    private News_List news_list_fg=null;//消息界面
    private Inform_List inform_list_fg=null;//通知界面


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.news,container,false);
        //当前显示较大的布局
        toolbar_news=(Toolbar)view.findViewById(R.id.toolbar_news);
        //将ToolBar对象设置为当前Activity的ActionBar
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar_news);
        setHasOptionsMenu(true);

        news_fl = (FrameLayout) view.findViewById(R.id.news_fl);
        //当前显示较小的布局
        inform_fl=(FrameLayout)view.findViewById(R.id.inform_fl);
        big_TV=(TextView)view.findViewById(R.id.xiaoxi);
        bignum_TV=(TextView)view.findViewById(R.id.unreadednum);

        small_TV=(TextView)view.findViewById(R.id.inform);
        smallnum_TV=(TextView)view.findViewById(R.id.inform_num);

        //点击小的替换
        inform_fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Switch();
            }
        });

        fragmentManager=getFragmentManager();

        //开启一个Fragment事务
        fragmentTransaction = fragmentManager.beginTransaction();
        news_list_fg = new News_List();
        fragmentTransaction.add(R.id.content_news, news_list_fg);
//        inform_list_fg=new Inform_List();
//        fragmentTransaction.add(R.id.content_news,news_list_fg);
        fragmentTransaction.show(news_list_fg).commit();
        Log.i("11111","增加事务");
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
//        即先clear()一下, 这样按钮就只有Fragment中设置的自己的了, 不会有Activity中的按钮.
        inflater.inflate(R.menu.more_news,menu);
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_newfriend:
                Toast.makeText(getContext(),"点击了添加朋友！",Toast.LENGTH_LONG).show();
                return true;
            case R.id.create_group:
                Toast.makeText(getContext(),"点击了创建群聊！",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void Switch(){

        final Animation news_animation = AnimationUtils.loadAnimation(getContext(), R.anim.alph);
        news_fl.startAnimation(news_animation);
        final Animation inform_animation = AnimationUtils.loadAnimation(getContext(), R.anim.translate_scale);
        inform_fl.startAnimation(inform_animation);
        fragmentTransaction = fragmentManager.beginTransaction();
        hideFragments(fragmentTransaction);
        inform_animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i("11111","动画开始");
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i("11111", "动画结束");
                //当前较大的文字
                bigshow = big_TV.getText().toString();
                //当前较大的数字
                bignum = bignum_TV.getText().toString();
                smallshow = small_TV.getText().toString();
                smallnum = smallnum_TV.getText().toString();
                //动画执行完毕后交换文字和数字
                big_TV.setText(smallshow);
                bignum_TV.setText(smallnum);
                small_TV.setText(bigshow);
                smallnum_TV.setText(bignum);
                Log.i("11111", bigshow);
                if (bigshow.equals("消息") ) {
                    if (inform_list_fg==null){
                        inform_list_fg = new Inform_List();
                        fragmentTransaction.add(R.id.content_news, inform_list_fg);
                        Log.i("11111", "消息状态1");
                    }else {
                        fragmentTransaction.show(inform_list_fg);
                        Log.i("11111", "消息状态2");
                    }
                    fragmentTransaction.commit();
                } else if ((bigshow.equals("通知"))) {
                    if (news_list_fg==null){
                        news_list_fg=new News_List();
                        fragmentTransaction.add(R.id.content_news,news_list_fg);
                        Log.i("11111", "通知状态1");
                    }else {
                        fragmentTransaction.show(news_list_fg);
                        Log.i("11111", "通知状态2");
                    }
                    fragmentTransaction.commit();
                }
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
    private void hideFragments(android.support.v4.app.FragmentTransaction fragmentTransaction){
        if(news_list_fg != null){
            fragmentTransaction.hide(news_list_fg);
        }
        if(inform_list_fg != null){
            fragmentTransaction.hide(inform_list_fg);
        }
    }
}
