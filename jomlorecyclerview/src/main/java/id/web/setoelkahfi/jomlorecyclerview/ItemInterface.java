package id.web.setoelkahfi.jomlorecyclerview;

import android.view.ViewGroup;

/**
 * Created by Seto Elkahfi on 4/28/17.
 *
 */

public interface ItemInterface<T extends Adapter.ViewHolder> {
    int getTypeId();
    T createViewHolder(ViewGroup parent);
    void populateViewHolder(Adapter.ViewHolder holder);
}
