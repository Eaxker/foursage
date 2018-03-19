package com.example.foursage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.foursage.thirdlib.TimeFormatTransform;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 袁凯明 on 2018/3/5.
 */

public class test extends Activity {
    private String time;
    private String testTime;
    private TextView time_TV;
    StringBuffer stringBuffer=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wei);
        testTime="2018-03-11 22:43:25";
        String [] strings=new String[]{"2018-03-11 22:43:25","2018-03-10 22:43:25","2018-03-9 22:43:25","2018-3-8 22:43:25","2018-3-5 22:43:25","2018-3-4 22:43:25","2018-3-3 22:43:25"};
        time_TV=(TextView)findViewById(R.id.time);
        stringBuffer=new StringBuffer();
        for (String s:strings){
            try {
                String s1=new TimeFormatTransform().getdate(s);
                stringBuffer.append(s+":");
                stringBuffer.append(new TimeFormatTransform().getdate(s)+"\n");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        try {
            long time1=new  TimeFormatTransform().datetimeToLong("2018-3-3 22:43:25");
            Date date = new Date(time1);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int w = cal.get(Calendar.DAY_OF_WEEK);
            Log.i("w",cal.toString()+w);
            stringBuffer.append("2018-03-3 22:43:25 :*"+w+"*\n");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        time_TV.setText(stringBuffer.toString());
    }
}
