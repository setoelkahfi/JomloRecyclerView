## JomloRecyclerView
A detachable RecyclerView component.

Write single or multiple item (component) for the adapter, re-use it in another part. Every component act as a single entity inside the adapter.

## How To Use
### Import the file
```java
import id.web.setoelkahfi.jomlorecyclerview.Adapter;
import id.web.setoelkahfi.jomlorecyclerview.ItemInterface;
```
### Use it in activity
Create reference to the adapter, create the dataset, and hook them up together via dependency injection.
 ```java
 public class MainActivity extends AppCompatActivity {
 
     private RecyclerView recyclerView;
     private Adapter jomloAdapter;
     private List<ItemInterface<? extends Adapter.ViewHolder>> jomloDataSet = new ArrayList<>();
 
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
 
         recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
         jomloAdapter = new Adapter(jomloDataSet);
         recyclerView.setAdapter(jomloAdapter);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
 
         for (int i=0; i < 20; i++) {
             SimpleItem item = new SimpleItem("Simple Example", "Subtest");
             jomloDataSet.add(item);
         }
 
         jomloAdapter.notifyDataSetChanged();
 
     }
 }
 ```


## The Files
### Adapter
Custom adapter to hold our items. Used it as adapter for regular RecyclerView.
```java
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

    private void setAnimation(View viewToAnimate, int position)
    {
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), R.anim.slide_top_down);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

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

```

### Item
Subclass this class to create your custom item. Add this item into dataSet in your adapter.
```java
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
            throw new RuntimeException("Please create a ViewHolder accepting view as argument.");
        }
    }

    @Override
    public void populateViewHolder(Adapter.ViewHolder holder) {
        onPopulateViewHolder((T) holder);
    }

    protected abstract int getViewId();

    protected abstract void onPopulateViewHolder(T holder);
}
```

### Item interface
Interface for creating item object.
```java
interface ItemInterface<T extends Adapter.ViewHolder> {
    int getTypeId();
    T createViewHolder(ViewGroup parent);
    void populateViewHolder(Adapter.ViewHolder holder);
}
```
