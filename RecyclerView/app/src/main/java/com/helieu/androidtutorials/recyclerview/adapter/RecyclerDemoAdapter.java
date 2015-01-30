package com.helieu.androidtutorials.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.helieu.androidtutorials.R;
import com.helieu.androidtutorials.recyclerview.listeners.RecyclerViewOnItemClickListener;

import java.util.List;

/**
 * Created by Neil on 15/01/29.
 */
public class RecyclerDemoAdapter extends RecyclerView.Adapter<RecyclerDemoAdapter.ViewHolder> {
    private List<String> dataSet;
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View parent;
        public TextView textView;
        public ViewHolder(View parent) {
            super(parent);

            this.parent = parent;
            this.textView = (TextView) parent.findViewById(R.id.data_text);

            //set the click listener that invokes the recycler callback listener
            this.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewOnItemClickListener.onItemClick(v, getPosition());
                }
            });
        }
    }

    public RecyclerDemoAdapter(List<String> dataset, RecyclerViewOnItemClickListener recyclerViewOnItemClickListener) {
        this.dataSet = dataset;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    @Override
    public RecyclerDemoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void addItem(String data) {
        //inserts an item at the start of the list
        dataSet.add(0, data);
        notifyItemInserted(0);
    }

    public void removeItem(int position) {
        dataSet.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}