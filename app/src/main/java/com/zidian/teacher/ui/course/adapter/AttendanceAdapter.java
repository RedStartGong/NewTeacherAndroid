package com.zidian.teacher.ui.course.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zidian.teacher.R;
import com.zidian.teacher.model.entity.remote.AttendanceStudent;
import com.zidian.teacher.ui.course.activity.AttendanceActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 考勤adapter
 * Created by GongCheng on 2017/4/8.
 */

public class AttendanceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<AttendanceStudent.DataBean> students;

    public AttendanceAdapter(Context context) {
        students = new ArrayList<>();
        this.context = context;
    }

    public void setStudents(List<AttendanceStudent.DataBean> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    /**
     * 学生考勤信息Json String
     *
     * @return
     */
    public String getStudentJson() {
        String jsonResult = "";
        try {
            JSONArray jsonarray = new JSONArray();
            for (int i = 0; i < students.size(); i++) {
                JSONObject jsonObjAnswer = new JSONObject();
                jsonObjAnswer.put("studentId", students.get(i).getStudentId());
                jsonObjAnswer.put("attendanceContent", students.get(i).getAttendance());
                jsonarray.put(jsonObjAnswer);
            }
            jsonResult = jsonarray.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 设置考勤信息
     *
     * @param code 状态
     */
    public void setStudentAttendance(@AttendanceActivity.AttendanceStatus String code) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).isSelect()) {
                students.get(i).setAttendance(code);
                students.get(i).setSelect(false);
                notifyItemChanged(i);
            }
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_student_attendance, parent,
                false);
        return new ItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ItemViewHolder) {

            ((ItemViewHolder) holder).tvStudentName.setText(students.get(position).getStudentName());
            ((ItemViewHolder) holder).tvStudentId.setText(students.get(position).getStudentId());
            Glide.with(context)
                    .load(students.get(position).getStudentHeadPortrait())
                    .placeholder(R.drawable.ic_student)
                    .centerCrop()
                    .into(((ItemViewHolder) holder).civStudentPortrait);
            if (students.get(position).isSelect()) {
                ((ItemViewHolder) holder).ivSelector.setBackgroundResource(R.drawable.ic_checked);
            } else {
                ((ItemViewHolder) holder).ivSelector.setBackgroundResource(R.drawable.ic_un_checked);

            }

            if (students.get(position).getAttendance().equals("0")) {
                ((ItemViewHolder) holder).tvAbsenteeism.setVisibility(View.GONE);
                ((ItemViewHolder) holder).tvLeave.setVisibility(View.GONE);
                ((ItemViewHolder) holder).tvLate.setVisibility(View.GONE);
                ((ItemViewHolder) holder).tvLeaveEarly.setVisibility(View.GONE);
            } else if (students.get(position).getAttendance().equals("1")) {
                ((ItemViewHolder) holder).tvAbsenteeism.setVisibility(View.GONE);
                ((ItemViewHolder) holder).tvLeave.setVisibility(View.GONE);
                ((ItemViewHolder) holder).tvLate.setVisibility(View.VISIBLE);
                ((ItemViewHolder) holder).tvLeaveEarly.setVisibility(View.GONE);
            } else if (students.get(position).getAttendance().equals("2")) {
                ((ItemViewHolder) holder).tvAbsenteeism.setVisibility(View.GONE);
                ((ItemViewHolder) holder).tvLeave.setVisibility(View.GONE);
                ((ItemViewHolder) holder).tvLate.setVisibility(View.GONE);
                ((ItemViewHolder) holder).tvLeaveEarly.setVisibility(View.VISIBLE);
            } else if (students.get(position).getAttendance().equals("3")) {
                ((ItemViewHolder) holder).tvAbsenteeism.setVisibility(View.VISIBLE);
                ((ItemViewHolder) holder).tvLeave.setVisibility(View.GONE);
                ((ItemViewHolder) holder).tvLate.setVisibility(View.GONE);
                ((ItemViewHolder) holder).tvLeaveEarly.setVisibility(View.GONE);
            } else if (students.get(position).getAttendance().equals("4")) {
                ((ItemViewHolder) holder).tvAbsenteeism.setVisibility(View.GONE);
                ((ItemViewHolder) holder).tvLeave.setVisibility(View.VISIBLE);
                ((ItemViewHolder) holder).tvLate.setVisibility(View.GONE);
                ((ItemViewHolder) holder).tvLeaveEarly.setVisibility(View.GONE);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (students.get(position).isSelect()) {
                        students.get(position).setSelect(false);
                        notifyItemChanged(position);
                    } else {
                        students.get(position).setSelect(true);
                        notifyItemChanged(position);
                    }

                }
            });

            ((ItemViewHolder) holder).tvAbsenteeism.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    students.get(position).setAttendance("0");
                    notifyItemChanged(position);
                }
            });
            ((ItemViewHolder) holder).tvLeaveEarly.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    students.get(position).setAttendance("0");
                    notifyItemChanged(position);
                }
            });
            ((ItemViewHolder) holder).tvLate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    students.get(position).setAttendance("0");
                    notifyItemChanged(position);
                }
            });
            ((ItemViewHolder) holder).tvLeave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    students.get(position).setAttendance("0");
                    notifyItemChanged(position);
                }
            });


        }
    }

    @Override
    public int getItemCount() {
        return students.size() == 0 ? 0 : students.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.civ_student_portrait)
        CircleImageView civStudentPortrait;
        @BindView(R.id.tv_student_name)
        TextView tvStudentName;
        @BindView(R.id.tv_student_id)
        TextView tvStudentId;
        @BindView(R.id.iv_selector)
        ImageView ivSelector;
        @BindView(R.id.tv_absenteeism)
        TextView tvAbsenteeism;
        @BindView(R.id.tv_leave)
        TextView tvLeave;
        @BindView(R.id.tv_late)
        TextView tvLate;
        @BindView(R.id.tv_leave_early)
        TextView tvLeaveEarly;


        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
