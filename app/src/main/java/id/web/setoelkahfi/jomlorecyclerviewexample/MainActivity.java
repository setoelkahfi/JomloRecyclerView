package id.web.setoelkahfi.jomlorecyclerviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import id.web.setoelkahfi.jomlorecyclerview.Adapter;
import id.web.setoelkahfi.jomlorecyclerview.R;

public class MainActivity extends AppCompatActivity {

    private Adapter jomloAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jomloAdapter = new Adapter();
    }
}
