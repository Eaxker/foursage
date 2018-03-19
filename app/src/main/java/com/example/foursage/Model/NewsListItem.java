package com.example.foursage.Model;

import android.net.Uri;

import java.util.Objects;

/**
 * Created by 袁凯明 on 2018/2/25.
 */

public class NewsListItem {

    private String headurl;     //头像图片链接
    private String name;        //昵称
    private String lastnews;    //最近消息
    private String lasttime;    //最近消息时间
    private String unreadnum;   //未读消息个数

    public NewsListItem(){
    }
    public NewsListItem(String headurl,String name,String lastnews,String lasttime,String unreadnum){
        this.headurl=headurl;
        this.name=name;
        this.lastnews=lastnews;
        this.lasttime=lasttime;
        this.unreadnum=unreadnum;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getLastnews() {
        return lastnews;
    }

    public void setLastnews(String lastnews) {
        this.lastnews = lastnews;
    }

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnreadnum() {
        return unreadnum;
    }

    public void setUnreadnum(String unreadnum) {
        this.unreadnum = unreadnum;
    }

}