package com.commonsdk.custome.recycleview;

import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by Administrator on 2015/8/5.
 */
public class ViewHolder {
    private SparseArray<View> viewHolder;
    private View view;

    public static ViewHolder getViewHolder(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        return viewHolder;
    }

    public ViewHolder(View view) {
        this.view = view;
        viewHolder = new SparseArray();
        view.setTag(viewHolder);
    }

    public <T extends View> T get(int id) {
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }

    public View itemView() {
        return view;
    }

    public TextView text(int id) {
        return get(id);
    }

    public RelativeLayout getRelativeLayout(int id) {
        return get(id);
    }

    public void text(int id, CharSequence charSequence) {
        text(id).setText(charSequence);
    }


    public Button button(int id) {
        return get(id);
    }

    public ImageView image(int id) {
        return get(id);
    }


}
