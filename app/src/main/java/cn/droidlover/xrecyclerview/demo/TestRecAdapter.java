package cn.droidlover.xrecyclerview.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.droidlover.xrecyclerview.RecyclerAdapter;

/**
 * @author wanglei
 * @version 1.5.2
 * @description
 * @createTime 2016/10/31 12:57
 * @editTime
 * @editor
 */
public class TestRecAdapter extends RecyclerAdapter<TestRecAdapter.Item, TestRecAdapter.ViewHolder> {


    public TestRecAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_test, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = data.get(position);
        holder.tv_msg.setText(item.getText());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_msg;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_msg = (TextView) itemView.findViewById(R.id.tv_msg);
        }
    }


    public static class Item {
        private String text;

        public Item(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
