package com.roger.recyclerviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter {

    private final LayoutInflater inflater;
    private List<HashMap<String, Object>> list;

    private MyItemClickListener myItemClickListener;

    // 绑定监听器
    public void setMyItemClickListener(MyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }

    // 构造函数，传入数据
    public RecyclerAdapter(Context context, List<HashMap<String, Object>> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ViewHolder绑定Item的布局
        return new MyViewHolder(inflater.inflate(R.layout.recyclerview_cell, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //在这里绑定数据到ViewHolder里面
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.titleText.setText((String) list.get(position).get("title"));
        viewHolder.contentText.setText((String) list.get(position).get("content"));
        viewHolder.imageView.setImageResource((Integer) list.get(position).get("image"));
    }


    //TODO 注意 <----------------------------------------------->
    //
    // 注意 设置了--adapter.setHasStableIds(true);
    // 一定要重写 getItemId方法，否则数据会混乱
    //
    //TODO 注意 <----------------------------------------------->

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleText;
        private final TextView contentText;
        private final ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.text_title);
            contentText = itemView.findViewById(R.id.text_content);
            imageView = itemView.findViewById(R.id.recycler_cell_iv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (myItemClickListener != null) {
                        myItemClickListener.onItemClick(view, getLayoutPosition());
                    }
                }
            });
        }
    }
}
