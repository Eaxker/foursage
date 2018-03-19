package com.example.foursage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 袁凯明 on 2018/3/6.
 */

public class SettingSingleChat extends Activity {
    private ImageView back_Setting_chat;
    private TextView defriend;//拉黑
    private TextView jubao;//举报
    private TextView zhuye;//查看空间


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_singlechat);
        back_Setting_chat=(ImageView)findViewById(R.id.back_chat_setting);
        defriend=(TextView)findViewById(R.id.lahei) ;
        jubao=(TextView)findViewById(R.id.report);
        zhuye=(TextView)findViewById(R.id.look_home);

        back_Setting_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        defriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Myapplication.getContext(),"已拉黑",Toast.LENGTH_SHORT).show();
            }
        });
        jubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Myapplication.getContext(),"已举报",Toast.LENGTH_SHORT).show();
            }
        });
        zhuye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Myapplication.getContext(),"查看主页",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
