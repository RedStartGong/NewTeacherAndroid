package com.zidian.teacher.ui.questionnaire.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zidian.teacher.model.entity.remote.MyQuesList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by GongCheng on 2017/5/3.
 */

public class MyQuesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MyQuesList.ListBean> data;

    @Inject
    public MyQuesListAdapter() {
        data = new ArrayList<>();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
