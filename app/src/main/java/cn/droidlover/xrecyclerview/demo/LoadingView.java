package cn.droidlover.xrecyclerview.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * @author wanglei
 * @version 1.5.2
 * @description
 * @createTime 2016/10/31 16:27
 * @editTime
 * @editor
 */
public class LoadingView extends RelativeLayout {

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView(context);
    }

    private void setupView(Context context){
        inflate(context,R.layout.view_loading,this);
    }
}
