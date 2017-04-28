package id.web.setoelkahfi.jomlorecyclerviewexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.web.setoelkahfi.jomlorecyclerview.Adapter;
import id.web.setoelkahfi.jomlorecyclerview.ItemInterface;

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
