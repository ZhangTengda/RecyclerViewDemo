package com.roger.recyclerviewdemo.loadmore;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 根据item高度设置RecyClerView高度
 *
 * @author wangwx
 */
public class AutoSetHeightLayoutManager extends LinearLayoutManager {

    public AutoSetHeightLayoutManager(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public AutoSetHeightLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public AutoSetHeightLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

/*    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        //获取count判断，必须要有
        int count = state.getItemCount();
        if (count > 0) {
            View view = recycler.getViewForPosition(0);
            if (view != null) {
                measureChild(view, widthSpec, heightSpec);
                int measuredWidth = View.MeasureSpec.getSize(widthSpec);
                int measuredHeight = view.getMeasuredHeight();
                setMeasuredDimension(measuredWidth, measuredHeight);
            }
        } else {
            super.onMeasure(recycler, state, widthSpec, heightSpec);
        }
    }*/
}
