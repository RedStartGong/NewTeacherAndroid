package com.zidian.teacher.ui.evaluate.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.model.entity.remote.EvaTeacher;
import com.zidian.teacher.model.entity.remote.InviteTeacher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by GongCheng on 2017/4/14.
 */

public class InviteTeacherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<EvaTeacher> teachers;

    @Inject
    public InviteTeacherAdapter() {
        teachers = new ArrayList<>();
    }

    public void setTeachers(List<EvaTeacher> teachers) {
        this.teachers = teachers;
        this.teachers = new ArrayList<>(new HashSet<>(this.teachers));
        notifyDataSetChanged();
    }

    /**
     * 得到教师列表
     *
     * @return json字符串
     */
    public String getTeachers() {
        if (teachers.size() == 0) {
            return "";
        }
        if (teachers.size() >= 30) {
            return "30";
        }
        List<Integer> teacherIds = new ArrayList<>();
        for (int i = 0; i < teachers.size(); i++) {

            teacherIds.add(teachers.get(i).getTeacherId());
        }
        return teacherIds.toString();
    }

    public void addTeachers(List<EvaTeacher> teachers) {
        this.teachers.addAll(teachers);
        this.teachers = new ArrayList<>(new HashSet<>(this.teachers));
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_invite_teacher, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).tvTeacherName.setText(teachers.get(position).getTeacherName());
        }
    }

    @Override
    public int getItemCount() {
        return teachers == null ? 0 : teachers.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_invite_teacher_name)
        TextView tvTeacherName;
        @BindView(R.id.tv_invite_teacher_college)
        TextView tvTeacherCollege;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.iv_delete)
        public void deleteItem() {
            teachers.remove(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());
        }
    }
}
