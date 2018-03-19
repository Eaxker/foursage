package com.example.foursage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.foursage.thirdlib.FllowerAnimation;

/**
 * Created by 袁凯明 on 2018/3/1.
 */

public class Start_flower extends Activity {
    private Button btn_start;
    // 撒花特效
    private RelativeLayout rlt_animation_layout;
    private FllowerAnimation fllowerAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flower);

        // 撒花初始化
        rlt_animation_layout = (RelativeLayout) findViewById(R.id.rlt_animation_layout);
        rlt_animation_layout.setVisibility(View.VISIBLE);
        fllowerAnimation = new FllowerAnimation(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        fllowerAnimation.setLayoutParams(params);
        rlt_animation_layout.addView(fllowerAnimation);

        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 开始撒花
                fllowerAnimation.startAnimation();
            }
        });
    }
}
