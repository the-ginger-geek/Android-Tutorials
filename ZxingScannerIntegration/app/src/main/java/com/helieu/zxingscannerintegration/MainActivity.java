package com.helieu.zxingscannerintegration;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.helieu.zxingscannerintegration.adapter.RecyclerViewAdapter;
import com.helieu.zxingscannerintegration.listeners.RecyclerViewOnItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private static final int REQUEST_CODE = 1;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeActivity();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (REQUEST_CODE) : {
                if (resultCode == Activity.RESULT_OK) {
                    String decodedValue = data.getStringExtra(CaptureActivity.RESULT_IDENTIFIER);
                    addDecodedStringToAdapter(decodedValue);
                }
                break;
            }
        }
    }

    private void initializeActivity() {
        initializeClickListeners();

        initializeRecyclerLayoutManager();
        initializeRecyclerAdapter();
        initializeRecycler();
        setNoDataVisibility();
    }

    private void initializeRecycler() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //This flag is used to improve the performance of the Recycler. This should only
        //be set to true if you know that changes in content does not change the size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initializeRecyclerLayoutManager() {
        layoutManager = new LinearLayoutManager(this);
    }

    private void initializeRecyclerAdapter() {
        adapter = new RecyclerViewAdapter(recyclerViewOnItemClickListener);
    }

    private void initializeClickListeners() {
        findViewById(R.id.addActionButton).setOnClickListener(fabClickListener);
    }

    private void addDecodedStringToAdapter(String decodedValue) {
        adapter.addItem(decodedValue);
        setNoDataVisibility();
    }

    private void setNoDataVisibility() {
        View noDataLayout = findViewById(R.id.no_data_layout);

        if (adapter.hasData()) {
            noDataLayout.setVisibility(View.INVISIBLE);
        } else {
            noDataLayout.setVisibility(View.VISIBLE);
        }
    }

    private void removeItemFromAdapter(int position) {
        adapter.removeItem(position);
        setNoDataVisibility();
    }

    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener = new RecyclerViewOnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            removeItemFromAdapter(position);
        }
    };

    private View.OnClickListener fabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), REQUEST_CODE);
        }
    };
}
