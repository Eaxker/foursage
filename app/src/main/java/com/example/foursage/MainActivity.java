package com.example.foursage;


import android.app.Fragment;
import android.content.Context;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

import static com.example.foursage.R.drawable.dazhongfabu_selector;


public class MainActivity extends AppCompatActivity {
    private Context context=null;
    private FragmentManager fm=null;
    private FragmentTransaction transaction=null;
    private VpAdapter adapter=null;
    private List<android.support.v4.app.Fragment> fragments=null;
    private ViewPager viewPager=null;
    private BottomNavigationViewEx bnve=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm=getSupportFragmentManager();
        transaction=fm.beginTransaction();
        bnve = (BottomNavigationViewEx) findViewById(R.id.bnve);
        /**测试消息提醒用
        addBadgeAt(0,1);
        addBadgeAt(1,3);
        addBadgeAt(2,2);
         **/
        fragments = new ArrayList<>(3);
        adapter = new VpAdapter(getSupportFragmentManager(), fragments);
        viewPager = (ViewPager) findViewById(R.id.content);
        Dazhongfabu dazhongfabu=new Dazhongfabu();
        News news=new News();
        Mine mine=new Mine();
        fragments.add(dazhongfabu);
        fragments.add(news);
        fragments.add(mine);
        viewPager.setAdapter(adapter);
         /*给底部导航栏菜单项添加点击事件*/
        bnve.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.addOnPageChangeListener(mOnPageChangeListener);
        setSelected(0);


    }
    private ViewPager.OnPageChangeListener mOnPageChangeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageSelected(int position) {
            bnve.setCurrentItem(position);
            setSelected(position);
        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    //底部菜单栏各个菜单项的点击事件处理
    private BottomNavigationViewEx.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.dazhongfabu://大众发布
                    viewPager.setCurrentItem(0);
                    setSelected(0);
                    return true;
                case R.id.news://消息
                    viewPager.setCurrentItem(1);
                    setSelected(1);
                    return true;
                case R.id.mine://我的
                    viewPager.setCurrentItem(2);
                    setSelected(2);
                    return true;
            }
           return false;
        }
    };

    /**
     * view pager adapter
     */
    private static class VpAdapter extends FragmentPagerAdapter {
        private List<android.support.v4.app.Fragment> data;
        public VpAdapter(FragmentManager fm, List<android.support.v4.app.Fragment> data) {
            super(fm);
            this.data = data;
        }
        @Override
        public int getCount() {
            return data.size();
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return data.get(position);
        }
    }

    //消息小红点
    private Badge addBadgeAt(final int position, int number) {
        // add badge
        return new QBadgeView(this)
                .setBadgeNumber(number)
                .setGravityOffset(12, 2, true)
                .bindTarget(bnve.getBottomNavigationItemView(position))
                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState){
                            //消息个数置0
                            badge.setBadgeNumber(0);
                        }

                    }
                });
    }
    //设置选中某一导航栏
    private void setSelected(int num){
                switch (num){
                    case 0:
                        bnve.getBottomNavigationItemView(0).setSelected(true);
                        bnve.getBottomNavigationItemView(1).setSelected(false);
                        bnve.getBottomNavigationItemView(2).setSelected(false);
                        break;
                    case 1:
                        bnve.getBottomNavigationItemView(0).setSelected(false);
                        bnve.getBottomNavigationItemView(1).setSelected(true);
                        bnve.getBottomNavigationItemView(2).setSelected(false);
                        break;
                    case 2:
                        bnve.getBottomNavigationItemView(0).setSelected(false);
                        bnve.getBottomNavigationItemView(1).setSelected(false);
                        bnve.getBottomNavigationItemView(2).setSelected(true);
                        break;
                    default:
                        break;

        }

        }




}


