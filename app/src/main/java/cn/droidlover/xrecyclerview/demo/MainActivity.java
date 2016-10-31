package cn.droidlover.xrecyclerview.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.droidlover.xrecyclerview.XRecyclerView;

public class MainActivity extends Activity {

    XRecyclerView recyclerView;
    TextView tv_next;

    TestRecAdapter adapter;

    static final int MAX_PAGE = 5;
    private int pageSize = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (XRecyclerView) findViewById(R.id.recyclerView);
        tv_next = (TextView) findViewById(R.id.tv_next);

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NextActivity.class));
            }
        });

        initAdapter();
        loadData(1);
    }

    private void initAdapter() {
        if (adapter == null) adapter = new TestRecAdapter(this);
        recyclerView.verticalLayoutManager(this)
                .setAdapter(adapter);
        recyclerView.horizontalDivider(R.color.x_red, R.dimen.divider_height);
        recyclerView.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
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
            }
        }, 2000L);

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
