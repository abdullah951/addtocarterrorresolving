package com.example.hahaha.addtocarterrorresolving;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.hahaha.addtocarterrorresolving.Volley.ServerCallback;
import com.example.hahaha.addtocarterrorresolving.Volley.VolleyGetData;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    RecyclerView recyclerViewFruits;
    private RecyclerView.Adapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewFruits = findViewById(R.id.recyclerView);
        //recyclerViewFruits.hasFixedSize();
        recyclerViewFruits.setHasTransientState(true);

        recyclerViewFruits.setHasFixedSize(false);
        recyclerViewFruits.addItemDecoration(new EqualSpacingItemDecoration(6, EqualSpacingItemDecoration.HORIZONTAL));
        recyclerViewFruits.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        VolleyGetData.getData(this, Constant.uriFruit, new ServerCallback() {
            @Override
            public void onSuccess(List<object> list) {
                myadapter = new VegAdapter1(list, getApplicationContext());
                Log.d(TAG, "list: onActivityCreated" + list.size());
                recyclerViewFruits.setAdapter(myadapter);

            }

            @Override
            public void onError(String message) {

            }
        });
    }
}
