package com.example.foursage.Model;

import android.widget.ImageView;

/**
 * Created by 袁凯明 on 2018/2/28.
 */

public class InformListItem {
    private String headurl;//头像连接
    private String name1;//评论者
    private String name2;//被评论者
    private String inform;//评论消息
    private String content_time;//评论时间
    private String another_content;//是一张图片或者是所评论的内容
    private Boolean isTu;//another_content是否是图片的Uri否则为文字

    public InformListItem(){

    }
    public InformListItem(String headurl,String name1,String name2,String inform,String content_time,String another_content,Boolean isTu){
        this.headurl=headurl;
        this.name1=name1;
        this.name2=name2;
        this.inform=inform;
        this.content_time=content_time;
        this.another_content=another_content;
        this.isTu=isTu;
    }


    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getAnother_content() {
        return another_content;
    }

    public void setAnother_content(String anothercontent) {
        this.another_content = anothercontent;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }



    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getInform() {
        return inform;
    }

    public void setInform(String inform) {
        this.inform = inform;
    }

    public String getContent_time() {
        return content_time;
    }

    public void setContent_time(String content_time) {
        this.content_time = content_time;
    }

    public Boolean getTu() {
        return isTu;
    }
    public void setTu(Boolean tu) {
        isTu = tu;
    }
}
