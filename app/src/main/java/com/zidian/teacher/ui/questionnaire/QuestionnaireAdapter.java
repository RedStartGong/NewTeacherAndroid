package com.zidian.teacher.ui.questionnaire;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zidian.teacher.model.entity.remote.Questionnaire;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/5.
 */

public class QuestionnaireAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Questionnaire.ListBean> data;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
