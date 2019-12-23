package com.roger.recyclerviewdemo.baseadapter;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.roger.recyclerviewdemo.R;

import java.util.List;

public class DemoAdapter extends BaseQuickAdapter<DataBean, DemoAdapter.MyViewHolder> {
    // source
//public class DemoAdapter extends BaseQuickAdapter<DataBean, BaseViewHolder> {

    public DemoAdapter(int layoutResId, @Nullable List<DataBean> data) {
        super(layoutResId, data);
    }

    public DemoAdapter(@Nullable List<DataBean> data) {
        super(data);
    }

    public DemoAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull MyViewHolder holder, DataBean item) {
        int adapterPosition = holder.getAdapterPosition();
        if (adapterPosition % 2 == 0) {
            holder.setBackgroundColor(R.id.demo_item_rootview, Color.RED);
        } else {
            holder.setBackgroundColor(R.id.demo_item_rootview, Color.BLUE);
        }


        holder.name.setText(item.getName());
        holder.age.setText(item.getAge() + "");

        // source
//        holder.setText(holder.name, item.getName());
//        holder.setText(R.id.age, item.getAge() + "");
    }

    public class MyViewHolder extends BaseViewHolder {

        private final LinearLayout rootView;
        private final TextView name;
        private final TextView age;

        public MyViewHolder(View view) {
            super(view);
            rootView = view.findViewById(R.id.demo_item_rootview);
            name = view.findViewById(R.id.name);
            age = view.findViewById(R.id.age);
        }
    }
}
