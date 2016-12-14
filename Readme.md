# ARecyclerView

对RecyclerView的封装，功能强大、使用简单、扩展性强。该库主要分成三部分：**RecyclerAdapter**、**XRecyclerView**、**XRecyclerContentLayout**


>该库在商业项目中历经一年多时间打磨，欢迎star、fork，后期会有更多分享，期待您的建议和关注。

**本库已经迁移到JitPack***


## 说明

关于RecyclerView，有很多库。ARecyclerView与其他库有这几个区别，也许能更好的进行扩展，方便您的使用。

* **ARecyclerView继承自RecyclerView，它就是一个封装了常见功能的RecyclerView，而不是继承FrameLayout**
* **ARecyclerView中实现了Header、Footer，header和Footer可以有多个**
* **ARecyclerView的每一个header、footer的viewType是不同的，而大部分开源库的header、footer的viewtype是相同的，其直接后果是界面卡顿**
* **ARecyclerView可以做出几乎任何的界面效果，可以取代ScrollView，你只需要使用header或者footer**
* **ARecyclerView中实现了上拉加载更多，可以自定义加载更多的效果，只需要实现LoadMoreUIHandler接口即可**
* **ARecyclerView并未实现下拉刷新功能，您可以选择SwipeRefreshLayout或者其他的下拉刷新viewGroup包裹，即你可以自由选择下拉刷新功能的实现。**
* **为了方便自定义使用，特别集成了XRecyclerContentLayout控件，你可以根据业务进行扩展，XRecyclerContentLayout只是一个示例，当然也可以满足绝大部分需求了**
* **欢迎您提出宝贵的意见**


<p align="center">
  <img src="art/xrecyclerview.gif" alt="XRecyclerView" />
</p>


## 使用

* Github ： [**ARecyclerView**](https://github.com/limedroid/ARecyclerView)

### step1 

在根项目的`build.gradle`文件中添加

```groovy
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
	}
}
```

### step2

添加依赖

```groovy
dependencies {
	   compile 'com.github.limedroid:ARecyclerView:v1.0.0'
}
```



## RecyclerAdapter

RecyclerAdapter简化了Adapter的开发，封装了一些常用的逻辑，包括数据集合操作、接口监听RecyclerItemCallback可以满足99%的需求。

### 使用示例

```java
public class TestRecAdapter extends RecyclerAdapter<TestRecAdapter.Item, TestRecAdapter.ViewHolder> {

	@Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      
    }

	@Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        
    }
}
```


## XRecyclerView

XRecyclerView是对RecyclerView的封装，其主要特性包括:

* 一行代码添加、删除、修改Header或者Footer
* 一行代码添加默认的上拉加载效果
* 一行代码切换自定义上拉加载效果
* 一行代码轻松添加LayoutManager
* 一行代码添加divider
* Adapter规范及封装**RecyclerAdapter**

### 使用示例

```java
recyclerView.verticalLayoutManager(this)        //设置layoutManager
            .setAdapter(adapter);                   //设置Adapter
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
recyclerView.useDefLoadMoreView();      //使用默认的上拉刷新样式
recyclerView.addHeaderView(headView);       //添加header
recyclerView.addFooterView(footview);       //添加footer
recyclerView.removeHeaderView(headview);    //删除header
recyclerView.removeFooterView(footview);    //删除footer
```

## XRecyclerContentLayout

XRecyclerContentLayout继承了[**QTContentLayout**](https://github.com/limedroid/QTContentLayout)，可自定义Loading、Error、Empty、Content四种显示状态，满足了绝大部分需求.

### 使用示例

```xml
<cn.droidlover.xrecyclerview.XRecyclerContentLayout
        android:id="@+id/contentLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:recyclerBackgroundColor="#f5f5f5"
        app:recyclerScrollbarNone="true"
        app:recyclerScrollbarStyle="outsideOverlay" />
```

```java
contentLayout.loadingView(new LoadingView(this));
             .errorView(new ErrorView(this));
             .emptyView(new EmptyView(this));

contentLayout.showLoading();
contentLayout.showError();
contentLayout.showEmpty();
contentLayout.showContent();
```

### 自定义LoadMoreView的实现

自定义LoaderMoreView只需实现LoadMoreUIHandler接口，然后调用xrecyclerView的loadMoreFooterView(loadMoreView)方法即可。

```java
 @Override
    public void onLoading() {
        setVisibility(VISIBLE);
        tvMsg.setText("加载中");
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void onLoadFinish(boolean hasMore) {
        if (hasMore) {
            setVisibility(GONE);
        } else {
            setVisibility(VISIBLE);
            tvMsg.setText("没有更多数据");
            progressBar.setVisibility(GONE);
        }
    }
```
设置loadMoreView
```java
recyclerView.loadMoreFooterView(loadMoreView);
```

### 自定义实现XRecyclerContentLayout

在XRecyclerContentLayout中内置了SwipeRefreshLayout下拉刷新样式，您也可以自定义实现XRecyclerContentLayout，只需实现XRecyclerView.StateCallback接口即可.

```java
public interface StateCallback {
        void notifyEmpty();     //数据为空

        void notifyContent();   //显示contentview

        void refreshState(boolean isRefresh);   //更新刷新状态

        void refreshEnabled(boolean isEnabled); //刷新是否可用
    }
```

***详情demo可见app module。***

## 关于我

**Email** : droidlover@126.com

 

