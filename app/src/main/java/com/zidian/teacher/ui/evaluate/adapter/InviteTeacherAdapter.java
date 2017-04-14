package com.zidian.teacher.ui.evaluate.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.model.entity.remote.InviteTeacher;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GongCheng on 2017/4/14.
 */

public class InviteTeacherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<InviteTeacher> teachers;

    public interface OnItemClickListener {
        void onClick(InviteTeacher teacher);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setTeachers(List<InviteTeacher> teachers) {
        this.teachers = teachers;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_invite_teacher, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).tvTeacherName.setText(teachers.get(position).getTeacherName());
            ((ItemViewHolder) holder).tvTeacherCollege.setText(teachers.get(position).getTeacherCollege());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onClick(teachers.get(holder.getLayoutPosition()));
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return teachers == null ? 0 : teachers.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_invite_teacher_name)
        TextView tvTeacherName;
        @BindView(R.id.tv_invite_teacher_college)
        TextView tvTeacherCollege;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
