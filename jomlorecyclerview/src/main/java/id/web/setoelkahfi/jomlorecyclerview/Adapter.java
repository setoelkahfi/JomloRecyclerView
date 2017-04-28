package id.web.setoelkahfi.jomlorecyclerview;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seto Elkahfi on 4/28/17.
 *
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ItemInterface<? extends ViewHolder>> dataSet = null;
    private int lastPosition;

    public Adapter(List<ItemInterface<? extends ViewHolder>> dataSet) {
        this.dataSet = dataSet;
    }

    public Adapter() {
        this.dataSet = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        ItemInterface<? extends ViewHolder> item = dataSet.get(position);
        return item.getTypeId();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        for (ItemInterface<? extends ViewHolder> item : dataSet) {
            if (item.getTypeId() == i)
                return item.createViewHolder(viewGroup);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ItemInterface<? extends ViewHolder> item = dataSet.get(i);
        item.populateViewHolder(viewHolder);

        Log.d("JomloRecyclerView", "OnBindViewHolder("+i+"'");
        //setAnimation(viewHolder.itemView, i);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

//    private void setAnimation(View viewToAnimate, int position)
//    {
//        // If the bound view wasn't previously displayed on screen, it's animated
//        if (position > lastPosition)
//        {
//            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), R.anim.slide_top_down);
//            viewToAnimate.startAnimation(animation);
//            lastPosition = position;
//        }
//    }

    public void add(ItemInterface item) {
        if (dataSet != null)
            dataSet.add(item);
    }

    public void add(int index, ItemInterface item) {
        if (dataSet != null)
            dataSet.add(index, item);
    }

    public void remove(ItemInterface item) {
        if (dataSet != null)
            dataSet.remove(item);
    }

    public void remove(int index) {
        if (dataSet != null)
            dataSet.remove(index);
    }

    public void clear() {
        if (dataSet != null)
            dataSet.clear();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
