package id.web.setoelkahfi.jomlorecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Seto Elkahfi on 4/28/17.
 *
 */

public abstract class Item<T extends Adapter.ViewHolder> implements ItemInterface<T> {

    @Override
    public int getTypeId() {
        return this.getClass().hashCode();
    }

    @Override
    public T createViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(getViewId(), parent, false);

        try {
            String className = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0].toString().split("\\s")[1];
            return (T)Class.forName(className).getConstructor(this.getClass(), View.class).newInstance(this, v);
        } catch (Exception e) {
            throw new RuntimeException("please create a viewholder accepting view as arg");
        }
    }

    @Override
    public void populateViewHolder(Adapter.ViewHolder holder) {
        onPopulateViewHolder((T) holder);
    }

    protected abstract int getViewId();

    protected abstract void onPopulateViewHolder(T holder);
}
