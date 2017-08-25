package com.zidian.teacher.ui.questionnaire.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zidian.teacher.R;
import com.zidian.teacher.model.entity.remote.SelectClass;
import com.zidian.teacher.ui.questionnaire.bean.ClassBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GongCheng on 2017/5/8.
 */

public class SelectClassAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SelectClass> data;

    public SelectClassAdapter(@NonNull List<SelectClass> data) {
        this.data = data;
    }

    /**
     * 得到选中的班级
     *
     * @return 选中的班级, 用逗号分割
     */
    public String getClassNames() {
        StringBuilder sb = new StringBuilder();
        int classesCount = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isSelected()) {
                classesCount++;
            }
        }
        for (int i = 0; i < data.size(); i++) {
            if (classesCount == 1) {
                if (data.get(i).isSelected()) {
                    sb.append(data.get(i).getClassName());
                }
            } else if (classesCount == 2) {
                if (data.get(i).isSelected()) {
                    if (sb.length() == 0) {
                        sb.append(data.get(i).getClassName()).append(",");
                    } else {
                        sb.append(data.get(i).getClassName());
                    }
                }
            } else {
                if (data.get(i).isSelected()) {
                    if (sb.length() == classesCount) {
                        sb.append(data.get(i).getClassName());
                    } else {
                        sb.append(data.get(i).getClassName()).append(",");
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * 获取课程Id json字符串
     *
     * @return [1, 5, 3]
     */
    public String getClassIds() {
        Gson gson = new Gson();
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isSelected()) {
                ids.add(data.get(i).getClassId());
            }
        }
        return gson.toJson(ids);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_select_class, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            if (data.get(position).isSelected()) {
                ((ViewHolder) holder).ivSelector.setImageResource(R.drawable.ic_tag_checked);
            } else {
                ((ViewHolder) holder).ivSelector.setImageResource(R.drawable.ic_un_checked);
            }
            ((ViewHolder) holder).tvClassName.setText(data.get(position).getClassName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data.get(holder.getAdapterPosition()).isSelected()) {
                        data.get(holder.getAdapterPosition()).setSelected(false);
                    } else {
                        data.get(holder.getAdapterPosition()).setSelected(true);
                    }
                    notifyItemChanged(holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_selector)
        ImageView ivSelector;
        @BindView(R.id.tv_class_name)
        TextView tvClassName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
