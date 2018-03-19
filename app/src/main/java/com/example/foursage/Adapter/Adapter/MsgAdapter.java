package com.example.foursage.Adapter.Adapter;

import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foursage.Model.Msg;
import com.example.foursage.Myapplication;
import com.example.foursage.R;
import com.example.foursage.thirdlib.TimeFormatTransform;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.example.foursage.thirdlib.TimeFormatTransform.datetimeToLong;
import static com.example.foursage.thirdlib.TimeFormatTransform.getTime;

/**
 * Created by 袁凯明 on 2018/3/1.
 */

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    private List<Msg> mMsgList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        LinearLayout left_msg;
        LinearLayout right_msg;
        TextView left_msg_time;
        TextView right_msg_time;
        TextView left_msg_content;
        TextView right_msg_content;
        ImageView left_head;
        ImageView right_head;
        public ViewHolder(View view){
            super(view);
            leftLayout=(LinearLayout)view.findViewById(R.id.left_layout);
            rightLayout=(LinearLayout)view.findViewById(R.id.right_layout);
            left_msg=(LinearLayout)view.findViewById(R.id.left_msg);
            right_msg=(LinearLayout)view.findViewById(R.id.right_msg);
            left_msg_time=(TextView)view.findViewById(R.id.left_msg_time);
            right_msg_time=(TextView)view.findViewById(R.id.right_msg_time);
            left_msg_content=(TextView)view.findViewById(R.id.left_msg_content);
            right_msg_content=(TextView)view.findViewById(R.id.right_msg_content);
            left_head=(ImageView)view.findViewById(R.id.left_head);
            right_head=(ImageView)view.findViewById(R.id.right_head);
        }

    }
    public MsgAdapter(List<Msg> msgList){
        this.mMsgList=msgList;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Msg msg=mMsgList.get(position);
        if (position==0){
            if(msg.getType()==Msg.TYPE_RECEIVED){
                //如果是收到的消息，则显示左边的消息布局，将右边的消息布局影藏
                holder.leftLayout.setVisibility(View.VISIBLE);
                holder.rightLayout.setVisibility(View.GONE);
                Glide.with(Myapplication.getContext()).load(msg.getMsg_head()).into(holder.left_head);//设置左头像
//            holder.left_msg_time.setText(msg.getMsg_time());//设置时间
                try {
                    holder.left_msg_time.setText(TimeFormatTransform.getdate(msg.getMsg_time()));//设置时间
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                holder.left_msg_content.setText(msg.getMsg_content());//设置内容

            }else if(msg.getType()==Msg.TYPE_SENT){
                //如果是发出的消息，则显示右边的消息布局，将在左边的消息布局隐藏
                holder.rightLayout.setVisibility(View.VISIBLE);
                holder.leftLayout.setVisibility(View.GONE);
                Glide.with(Myapplication.getContext()).load(msg.getMsg_head()).into(holder.right_head);//设置右头像
//            holder.right_msg_time.setText(msg.getMsg_time());//设置时间
                try {
                    holder.right_msg_time.setText(getTime(datetimeToLong(msg.getMsg_time())));//设置时间
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                holder.right_msg_content.setText(msg.getMsg_content());//设置内容
            }
        }else {
            Msg msg1=mMsgList.get(position-1);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
            long l= df.parse(msg.getMsg_time()).getTime()-df.parse(msg1.getMsg_time()).getTime();
                int m= (int) (l/1000/60);//算出多少分钟;
                if (m>5){//如果这条消息比上次的消息晚5分钟则显示时间
                    if(msg.getType()==Msg.TYPE_RECEIVED){
                        //如果是收到的消息，则显示左边的消息布局，将右边的消息布局影藏
                        holder.leftLayout.setVisibility(View.VISIBLE);
                        holder.rightLayout.setVisibility(View.GONE);
                        Glide.with(Myapplication.getContext()).load(msg.getMsg_head()).into(holder.left_head);//设置左头像
//            holder.left_msg_time.setText(msg.getMsg_time());//设置时间
                        try {
                            holder.left_msg_time.setText(TimeFormatTransform.getdate(msg.getMsg_time()));//设置时间
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        holder.left_msg_content.setText(msg.getMsg_content());//设置内容

                    }else if(msg.getType()==Msg.TYPE_SENT){
                        //如果是发出的消息，则显示右边的消息布局，将在左边的消息布局隐藏
                        holder.rightLayout.setVisibility(View.VISIBLE);
                        holder.leftLayout.setVisibility(View.GONE);
                        Glide.with(Myapplication.getContext()).load(msg.getMsg_head()).into(holder.right_head);//设置右头像
//            holder.right_msg_time.setText(msg.getMsg_time());//设置时间
                        try {
                            holder.right_msg_time.setText(getTime(datetimeToLong(msg.getMsg_time())));//设置时间
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        holder.right_msg_content.setText(msg.getMsg_content());//设置内容
                    }
                }else {//如果这条消息距上次发送的消息在5分钟内则不显示时间
                    if(msg.getType()==Msg.TYPE_RECEIVED){
                        //如果是收到的消息，则显示左边的消息布局，将右边的消息布局影藏
                        holder.leftLayout.setVisibility(View.VISIBLE);
                        holder.left_msg_time.setVisibility(View.GONE);
                        holder.rightLayout.setVisibility(View.GONE);
                        Glide.with(Myapplication.getContext()).load(msg.getMsg_head()).into(holder.left_head);//设置左头像
//            holder.left_msg_time.setText(msg.getMsg_time());//设置时间
                        try {
                            holder.left_msg_time.setText(TimeFormatTransform.getdate(msg.getMsg_time()));//设置时间
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        holder.left_msg_content.setText(msg.getMsg_content());//设置内容
                    }else if(msg.getType()==Msg.TYPE_SENT){
                        //如果是发出的消息，则显示右边的消息布局，将在左边的消息布局隐藏
                        holder.rightLayout.setVisibility(View.VISIBLE);
                        holder.right_msg_time.setVisibility(View.GONE);
                        holder.leftLayout.setVisibility(View.GONE);
                        Glide.with(Myapplication.getContext()).load(msg.getMsg_head()).into(holder.right_head);//设置右头像
//            holder.right_msg_time.setText(msg.getMsg_time());//设置时间
                        try {
                            holder.right_msg_time.setText(getTime(datetimeToLong(msg.getMsg_time())));//设置时间
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        holder.right_msg_content.setText(msg.getMsg_content());//设置内容
                    }

                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }
}
