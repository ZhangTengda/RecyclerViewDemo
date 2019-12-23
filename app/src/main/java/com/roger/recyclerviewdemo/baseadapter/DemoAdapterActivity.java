package com.roger.recyclerviewdemo.baseadapter;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.roger.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

public class DemoAdapterActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private List<DataBean> dataList = new ArrayList<>();
    private DemoAdapter adapter;
    private int count = 0;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        swipeRefreshLayout = findViewById(R.id.swiperefresh);

        RecyclerView recyclerView = findViewById(R.id.demo_rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new DemoAdapter(R.layout.activity_demo_item, dataList);

        adapter.setLoadMoreView(new CustomLoadMoreView());

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, recyclerView);

        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(this);

        onRefresh();
    }


    private int loadMoreCount = 0;

    private void loadMore() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<DataBean> userDataList = requestData(false);

                if (loadMoreCount == 1) {
                    //正常加载更多，还有下一页
                    adapter.addData(userDataList);
                    adapter.loadMoreComplete();
                } else if (loadMoreCount == 3) {
                    //返回加载失败
                    adapter.loadMoreFail();
                } else if (loadMoreCount == 2) {
                    //加载到最后
                    adapter.addData(userDataList.subList(0, 6));
                    adapter.loadMoreEnd();
                }
            }
        }, 3000);
    }

    private List<DataBean> requestData(boolean isRefresh) {

        if (isRefresh) {
            count = 0;
        }

        List<DataBean> dataList = new ArrayList<>();
        for (int i = count; i < count + 25; i++) {
            if (isRefresh) {
                loadMoreCount = 0;
                dataList.add(new DataBean("下拉刷新数据", i));
            } else {
                dataList.add(new DataBean("上拉加载更多数据", i));
            }
        }

        if (!isRefresh) {
            loadMoreCount++;
        }

        count += 25;

        return dataList;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<DataBean> dataBeans = requestData(true);
                adapter.setNewData(dataBeans);
                adapter.loadMoreComplete();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);

    }
}
