package com.example.foursage.thirdlib;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 袁凯明 on 2018/3/11.
 */

public class TimeFormatTransform {
    /**
     * 根据long类型的时间戳，转换为一个String类型的描述性时间
     * 通话记录如果发生在今天：“15：30”
     * 发生在昨天：“昨天8:23”
     * 发生在前天：“前天4:56”
     * 更早：     “2016/04/15”
     *
     * @param timeStample
     * @return
     */
    //timeStample是聊天记录发生的时间
    public static String getTime(long timeStample) {
        //得到现在的时间戳
        long now = System.currentTimeMillis();

        //在java中,int类型的数进行除法运算,只能的整数,正是利用这一点,
        //在下列日期中,只要没过昨天24点,无论相差了1s还是23小时,除法得到的结果都是前一天,
        int day = (int) (now / 1000 / 60 / 60 / 24 - timeStample / 1000 / 60 / 60 / 24);
        System.out.println(day);
        if (day>7) {
//            SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy:MM:dd");
            SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return sdf3.format(timeStample);
        }else {
            switch (day) {
                //如果是0这则说明是今天,显示时间
                case 0:
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    return sdf.format(timeStample);
                //如果是1说明是昨天,显示昨天+时间
                case 1:
                    SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
                    return "昨天" + sdf1.format(timeStample);
                //如果是1说明是前天,显示前天+时间
                case 2:
                    SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
                    return "前天" + sdf2.format(timeStample);
                //结果大于2就只显示年月日
                default:
                    SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm");
                    return getWeekOfDate(timeStample)+ sdf3.format(timeStample);
            }
        }
    }
    /***
     * 把datetime转换为long格式
     */
    public static long datetimeToLong(String datetime) throws ParseException {
        Date d2;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        d2=sdf.parse(datetime);//将String to Date类型
        System.out.println(d2);
        long t3=d2.getTime();
        System.out.println(t3);
        return t3;
    }
    /***
     * 把datetime格式的转换为特定格式的日期字符串
     *
     */
    public static String getdate(String datetime) throws ParseException {
        String s;
        return s=getTime(datetimeToLong(datetime));

    }
    /**
     * 星期几
     *
     * @param time
     * long 系统时间的long类型
     * @return 星期一到星期日
     *
     * */
    public static String getWeekOfDate(long time) {

        Date date = new Date(time);
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK)-1;
        Log.i("w",date.toString()+w);
//        if (w < 0)
//            w = 0;
        return weekDays[w];
    }
}
