package com.example.foursage.Model;

/**
 * Created by 袁凯明 on 2018/3/1.
 */

public class Msg {
    public static final int TYPE_RECEIVED=0;//表示这是一条收到的消息
    public static final int TYPE_SENT=1;//表示这是条发出去的消息
    private String msg_head;//消息的头像
    private String msg_time;//消息的时间
    private String msg_content;//消息的内容
    private int type;//消息的类型
    public Msg(String msg_head,String msg_time,String msg_content,int type){
        this.msg_head=msg_head;
        this.msg_time=msg_time;
        this.msg_content=msg_content;
        this.type=type;
    }

    public String getMsg_head() {
        return msg_head;
    }

    public String getMsg_time() {
        return msg_time;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public int getType() {
        return type;
    }
}
