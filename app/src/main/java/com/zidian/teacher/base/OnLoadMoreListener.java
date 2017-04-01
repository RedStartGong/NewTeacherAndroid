package com.zidian.teacher.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by GongCheng on 2017/3/31.
 */

public abstract class OnLoadMoreListener extends RecyclerView.OnScrollListener {
    private LinearLayoutManager layoutManager;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int itemCount = layoutManager.getItemCount();
        int lastPosition = layoutManager.findLastVisibleItemPosition();
        if (lastPosition == itemCount + 1 ) {
            onLoadMore();
        }
    }

    public abstract void onLoadMore();
}
