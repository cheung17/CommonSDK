package com.commonsdk.custome.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

/**
 * @author ztx
 */
public abstract class QuickRecycleAdapter<E> extends RecyclerView.Adapter<RecyclerHolder> {
    protected Context context;
    public List<E> list;
    private int[] layout;

    public QuickRecycleAdapter() {
        this.context = context;
    }

    public QuickRecycleAdapter(Context context, int... layout) {
        this.list = new ArrayList<E>();
        this.context = context;
        this.layout = layout;
    }

    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //根据getItemViewType 来确定layout
        View view = LayoutInflater.from(context).inflate(layout[viewType], parent, false);
        return new RecyclerHolder(view);
    }

    private OnItemClickListener listener;

    @Override
    public void onBindViewHolder(final RecyclerHolder holder, final int position) {
        onBindViewHolder(holder.getViewHolder(), position);
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    listener.onItemClick(v, position);
                }
            });
        }
    }


    public abstract void onBindViewHolder(ViewHolder holder, int position);

    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
