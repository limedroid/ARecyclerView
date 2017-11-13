package cn.droidlover.xrecyclerview.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class MainActivity extends Activity {

    XRecyclerView recyclerView;
    SwipeRefreshLayout swipeLayout;
    TextView tv_next;

    TestRecAdapter adapter;

    static final int MAX_PAGE = 5;
    private int pageSize = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (XRecyclerView) findViewById(R.id.recyclerView);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        tv_next = (TextView) findViewById(R.id.tv_next);

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NextActivity.class));
            }
        });

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.onRefresh();
            }
        });
        initAdapter();
        loadData(1);
    }

    private void initAdapter() {
        if (adapter == null) adapter = new TestRecAdapter(this);
        recyclerView.verticalLayoutManager(this)        //设置layoutManager
                .setAdapter(adapter);                   //设置Adapter

        adapter.setRecItemClick(new RecyclerItemCallback<TestRecAdapter.Item, TestRecAdapter.ViewHolder>() {
            @Override
            public void onItemClick(int position, TestRecAdapter.Item model, int tag, TestRecAdapter.ViewHolder holder) {
                super.onItemClick(position, model, tag, holder);

                switch (tag) {
                    case TestRecAdapter.TAG_CLICK:
                        //TODO 事件处理
                        break;
                }
            }
        });
        recyclerView.horizontalDivider(R.color.x_red, R.dimen.divider_height);  //设置divider
        recyclerView.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() { //设置刷新和上拉加载监听
            @Override
            public void onRefresh() {
                loadData(1);
            }

            @Override
            public void onLoadMore(int page) {
                loadData(page);
            }
        });
        recyclerView.useDefLoadMoreView();
    }


    private void loadData(final int page) {
        if (page > 1) {
            Log.e("2222", "load more");
        } else {
            Log.e("2222", "refresh");
        }
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<TestRecAdapter.Item> list = buildData(page);
                if (page > 1) {
                    adapter.addData(list);
                } else {
                    adapter.setData(list);
                }
                recyclerView.setPage(page, MAX_PAGE);

                swipeLayout.setRefreshing(false);
            }
        }, 500L);

    }

    private List<TestRecAdapter.Item> buildData(int page) {
        int init = (page - 1) * pageSize;
        List<TestRecAdapter.Item> list = new ArrayList<>();

        for (int pos = init; pos < page * pageSize; pos++) {
            list.add(new TestRecAdapter.Item("测试" + pos));
        }

        return list;
    }
}
