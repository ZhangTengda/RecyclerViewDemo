package com.roger.recyclerviewdemo.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roger.recyclerviewdemo.R;
import com.roger.recyclerviewdemo.baseadapter.DataBean;

import java.util.ArrayList;
import java.util.List;

public class DemoTestAdapter extends RecyclerView.Adapter {

    private final LayoutInflater inflater;
    private List<DataBean> datas;

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private boolean isLoadingAdded = false;

    public List<DataBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DataBean> datas) {
        this.datas = datas;
    }

    public DemoTestAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        datas = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;

            case LOADING:
                View view = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.activity_demo_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DataBean dataBean = datas.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                MyViewHolder myHolder = (MyViewHolder) holder;
                myHolder.name.setText(dataBean.getName());
                myHolder.age.setText(dataBean.getAge() + "");
                break;
            case LOADING:
                //nothing to do
                break;
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == datas.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }


    /*
   ViewHolder
   _________________________________________________________________________________________________
    */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView age;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public LoadingViewHolder(View view) {
            super(view);
        }
    }


    /*
   Helpers
   _________________________________________________________________________________________________
    */

    public void add(DataBean mc) {
        datas.add(mc);
        notifyItemInserted(datas.size() - 1);
    }

    public void addAll(List<DataBean> mcList) {
        for (DataBean datas : mcList) {
            add(datas);
        }
    }

    public void remove(DataBean city) {
        int position = datas.indexOf(city);
        if (position > -1) {
            datas.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new DataBean());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = datas.size() - 1;
        DataBean item = getItem(position);

        if (item != null) {
            datas.remove(position);
            notifyItemRemoved(position);
        }
    }

    public DataBean getItem(int position) {
        return datas.get(position);
    }
}
