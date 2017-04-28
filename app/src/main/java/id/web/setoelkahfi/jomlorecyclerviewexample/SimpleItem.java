package id.web.setoelkahfi.jomlorecyclerviewexample;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.web.setoelkahfi.jomlorecyclerview.Adapter;
import id.web.setoelkahfi.jomlorecyclerview.Item;

/**
 * Created by Seto Elkahfi on 4/28/17.
 *
 */

class SimpleItem extends Item<SimpleItem.ViewHolder> {

    private String title;
    private String subTitle;

    SimpleItem(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    @Override
    protected int getViewId() {
        return R.layout.item_simple;
    }

    @Override
    public ViewHolder createViewHolder(ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(getViewId(), parent, false));
    }

    @Override
    protected void onPopulateViewHolder(ViewHolder holder) {
        holder.title.setText(title);
        holder.subTitle.setText(subTitle);
    }

    class ViewHolder extends Adapter.ViewHolder {

        private TextView title;
        private TextView subTitle;

        ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            subTitle = (TextView) itemView.findViewById(R.id.subtitle);
        }
    }
}
