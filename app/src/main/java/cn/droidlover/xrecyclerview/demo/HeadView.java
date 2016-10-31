package cn.droidlover.xrecyclerview.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * @author wanglei
 * @version 1.5.2
 * @description
 * @createTime 2016/10/31  17:26
 * @editTime
 * @editor
 */
public class HeadView extends RelativeLayout{


    public HeadView(Context context) {
        this(context, null);
    }

    public HeadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView(context);
    }

    private void setupView(Context context){
        inflate(context,R.layout.view_head,this);
    }
}
