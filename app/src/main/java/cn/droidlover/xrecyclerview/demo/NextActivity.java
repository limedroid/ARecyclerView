package cn.droidlover.xrecyclerview.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class NextActivity extends Activity implements View.OnClickListener {

    XRecyclerContentLayout contentLayout;

    TestRecAdapter adapter;
    TextView tv_error;
    TextView tv_empty;
    TextView tv_content;
    TextView tv_loading;

    static final int MAX_PAGE = 5;
    private int pageSize = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        contentLayout = (XRecyclerContentLayout) findViewById(R.id.contentLayout);
        tv_error = (TextView) findViewById(R.id.tv_error);
        tv_empty = (TextView) findViewById(R.id.tv_empty);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_loading = (TextView) findViewById(R.id.tv_loading);

        tv_error.setOnClickListener(this);
        tv_empty.setOnClickListener(this);
        tv_content.setOnClickListener(this);
        tv_loading.setOnClickListener(this);

        contentLayout.getRecyclerView().setRefreshEnabled(true);    //设置是否可刷新

        initAdapter(contentLayout.getRecyclerView());
        loadData(1);
    }

    private void initAdapter(XRecyclerView recyclerView) {
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
        recyclerView.addHeaderView(new HeadView(this));

        contentLayout.loadingView(new LoadingView(this));
        contentLayout.errorView(new ErrorView(this));
        contentLayout.emptyView(new EmptyView(this));

        contentLayout.showLoading();
    }


    private void loadData(final int page) {
        contentLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<TestRecAdapter.Item> list = buildData(page);
                if (page > 1) {
                    adapter.addData(list);
                } else {
                    adapter.setData(list);
                }
                contentLayout.getRecyclerView().setPage(page, MAX_PAGE);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_error:
                contentLayout.showError();
                break;

            case R.id.tv_loading:
                contentLayout.showLoading();
                break;

            case R.id.tv_content:
                contentLayout.showContent();
                break;

            case R.id.tv_empty:
                contentLayout.showEmpty();
                break;
        }
    }
}
