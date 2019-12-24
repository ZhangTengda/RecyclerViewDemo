package com.roger.recyclerviewdemo.demo;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.roger.recyclerviewdemo.R;
import com.roger.recyclerviewdemo.baseadapter.DataBean;
import com.roger.recyclerviewdemo.demo.listener.PaginationScrollListener;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends AppCompatActivity {

    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = PAGE_START;
    private int TOTAL_PAGES = 3;
    private DemoTestAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        RecyclerView recyclerView = findViewById(R.id.demo_rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

//        List<DataBean> dataBeans = initData(true);

        adapter = new DemoTestAdapter(this);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
                    }
                }, 3000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
        recyclerView.setAdapter(adapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadFirstPage();
            }
        }, 1000);
    }


    private void loadFirstPage() {
//        Log.d(TAG, "loadFirstPage: ");
        List<DataBean> movies = DataBean.createDatas(adapter.getItemCount());
//        progressBar.setVisibility(View.GONE);
        adapter.addAll(movies);

        if (currentPage <= TOTAL_PAGES) {
            adapter.addLoadingFooter();
        } else {
            isLastPage = true;
        }

    }

    private void loadNextPage() {
        List<DataBean> datas = DataBean.createDatas(adapter.getItemCount());
        adapter.removeLoadingFooter();

        isLoading = false;

        adapter.addAll(datas);

        if (currentPage != TOTAL_PAGES) {
            adapter.addLoadingFooter();
        } else {
            isLastPage = true;
        }
    }

    private List<DataBean> initData(boolean isRefresh) {
        List<DataBean> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            DataBean bean = new DataBean();

            if (isRefresh) {
                bean.setName("黑暗骑士");
                bean.setAge(i);
            } else {
                bean.setName("小丑");
                bean.setAge(i);
            }
            list.add(bean);
        }
        return list;
    }
}
