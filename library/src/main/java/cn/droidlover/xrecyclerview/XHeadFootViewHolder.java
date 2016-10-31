package cn.droidlover.xrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wanglei on 2016/10/30.
 */

public class XHeadFootViewHolder extends RecyclerView.ViewHolder {

    public XHeadFootViewHolder(View itemView) {
        super(itemView);
        itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        itemView.setTag(this);
    }
}

