package com.example.foursage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foursage.Adapter.Adapter.MsgAdapter;
import com.example.foursage.Model.Msg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 袁凯明 on 2018/3/1.
 */

public class SingleChatActivity extends Activity{
    private List<Msg> msgList=new ArrayList<>();
    private EditText inputText;//输入框按钮
    private Button send;//发送按钮
    private Button tape;//录音按钮
    private Button chat_look;//表情按钮
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    private ImageView back_SingleChat;//返回键
    private ImageView setting_SingleChat;//设置键
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_ptop);

        intMsgs();//初始化数据
        inputText=(EditText)findViewById(R.id.input_text);
        send=(Button)findViewById(R.id.send_chat);
//        tape=(Button)findViewById(R.id.tape);
//        chat_look=(Button)findViewById(R.id.chat_look);
        msgRecyclerView=(RecyclerView)findViewById(R.id.msg_recycler_view);
        back_SingleChat=(ImageView)findViewById(R.id.back_chat);
        setting_SingleChat=(ImageView)findViewById(R.id.chat_setting);


        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter=new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content=inputText.getText().toString();
                if (!"".equals(content)){
                    Date dt = new java.util.Date();
                    java.text.SimpleDateFormat sdf =
                            new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String currentTime = sdf.format(dt);
                    Msg msg=new Msg("https://b-ssl.duitang.com/uploads/item/201411/19/20141119125117_jtJQs.thumb.700_0.jpeg",currentTime,content,1);
                    Toast.makeText(Myapplication.getContext(),dt.toString(),Toast.LENGTH_SHORT).show();
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);//插入最后一个当有新消息时刷新RecyclerView中的显示
                    msgRecyclerView.scrollToPosition(msgList.size()-1);//将RecyclerView定位到最后一行
                    inputText.setText("");//清空输入框中的内容
                }
            }
        });
        back_SingleChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setting_SingleChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent1=new Intent(SingleChatActivity.this,SettingSingleChat.class);
//                Toast.makeText(SingleChatActivity.this,"设置",Toast.LENGTH_SHORT).show();
                startActivity(intent1);
            }
        });
    }

    private void intMsgs() {
        Msg msg1=new Msg("http://www.iyi8.com/uploadfile/2017/0910/20170910110016346.jpg","2018-03-12 13:12:25","你好！",0);
        msgList.add(msg1);
        Msg msg2=new Msg("https://b-ssl.duitang.com/uploads/item/201411/19/20141119125117_jtJQs.thumb.700_0.jpeg","2018-03-12 13:12:25","你好!你叫什么名字？",1);
        msgList.add(msg2);
        Msg msg3=new Msg("http://www.iyi8.com/uploadfile/2017/0910/20170910110016346.jpg","2018-03-11 13:12:25","我叫苏小含",0);
        msgList.add(msg3);
    }
}
