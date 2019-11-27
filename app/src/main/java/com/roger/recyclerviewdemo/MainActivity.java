package com.roger.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<HashMap<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        RecyclerAdapter adapter = new RecyclerAdapter(this, list);
        // 当我们确定Item的改变不会影响RecyclerView的宽高的时候可以设置setHasFixedSize(true)，
        // 并通过Adapter的增删改插方法去刷新RecyclerView，而不是通过notifyDataSetChanged()。
        // 其实可以直接设置为true，当需要改变宽高的时候就用notifyDataSetChanged()去整体刷新一下。
        // TODO 注意
        // 注意 设置了--adapter.setHasStableIds(true);
        // Adapter一定要重写 getItemId方法，否则数据会混乱
        // TODO 注意
        adapter.setHasStableIds(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        // 设置线性排列
        recyclerView.setLayoutManager(linearLayoutManager);
        // 设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                linearLayoutManager.getOrientation()));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, R.mipmap.ic_launcher_round));

        recyclerView.setAdapter(adapter);

        adapter.setMyItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, (String) list.get(position)
                        .get("title"), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("title", "Title " + (i + 1));
            map.put("content", "This is Content " + (i + 1));
            map.put("image", R.mipmap.ic_launcher);
            list.add(map);
        }
    }
}
