package com.helieu.androidtutorials.recyclerview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.helieu.androidtutorials.R;
import com.helieu.androidtutorials.recyclerview.adapter.RecyclerDemoAdapter;
import com.helieu.androidtutorials.recyclerview.listeners.InputDialogCallback;
import com.helieu.androidtutorials.recyclerview.listeners.RecyclerViewOnItemClickListener;
import com.helieu.androidtutorials.recyclerview.utilities.MockDataGeneratorUtility;
import com.helieu.androidtutorials.recyclerview.widget.InputDialog;

/**
 * Created by Neil on 15/01/30.
 */
public class RecyclerFragment extends Fragment {

    private View rootView;

    private RecyclerView recyclerView;
    private RecyclerDemoAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_recycler, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initialize();
    }

    private void initialize() {
        //first initialize the variables used in the recyclerView
        initializeRecyclerLayoutManager();
        initializeRecyclerAdapter();

        //then initialize the recyclerView with set parameters
        initializeRecycler();
    }

    private void initializeRecycler() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.myDemoRecycler);

        //This flag is used to improve the performance of the Recycler. This should only
        //be set to true if you know that changes in content does not change the size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initializeRecyclerLayoutManager() {
        layoutManager = new LinearLayoutManager(getActivity());
    }

    private void initializeRecyclerAdapter() {
        adapter = new RecyclerDemoAdapter(MockDataGeneratorUtility.generateMockDataList(getActivity()), recyclerViewOnItemClickListener);
    }

    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener = new RecyclerViewOnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            adapter.removeItem(position);
        }
    };

    public void invokeFABAction() {
        InputDialog dialog = new InputDialog(getActivity(), new InputDialogCallback() {
            @Override
            public void inputText(String text) {
                adapter.addItem(text);
            }
        });

        dialog.show();
    }
}
