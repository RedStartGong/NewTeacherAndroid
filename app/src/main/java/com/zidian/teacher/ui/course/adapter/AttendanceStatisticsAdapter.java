package com.zidian.teacher.ui.course.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zidian.teacher.R;
import com.zidian.teacher.di.ActivityContext;
import com.zidian.teacher.model.entity.remote.AttendanceStatistics;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 考勤统计 adapter
 * Created by GongCheng on 2017/4/9.
 */

public class AttendanceStatisticsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<AttendanceStatistics> attendanceStatistics;
    private static final int TYPE_TOP = 0;
    private static final int TYPE_ITEM = 1;

    @Inject
    public AttendanceStatisticsAdapter(@ActivityContext Context context) {
        this.context = context;
        attendanceStatistics = new ArrayList<>();
    }

    public void setAttendanceStatistics(List<AttendanceStatistics> attendanceStatistics) {
        this.attendanceStatistics = attendanceStatistics;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_attendance_statistics, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_attendance_statistics_top, parent, false);
            return new TopViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ItemViewHolder) {
            int index = position - 1;
            ((ItemViewHolder) holder).tvStudentName.setText(attendanceStatistics.get(index).getStudentName());
            ((ItemViewHolder) holder).tvStudentId.setText(attendanceStatistics.get(index).getStudentNumber());
            Glide.with(context)
                    .load(attendanceStatistics.get(index).getStudentIconUrl())
                    .placeholder(R.drawable.ic_student)
                    .centerCrop()
                    .into(((ItemViewHolder) holder).civStudentPortrait);

            if (attendanceStatistics.get(index).getBeLateNum() == 0) {
                ((ItemViewHolder) holder).tvLate.setVisibility(View.INVISIBLE);
            } else {
                ((ItemViewHolder) holder).tvLate.setVisibility(View.VISIBLE);
                ((ItemViewHolder) holder).tvLate.setText(String.valueOf(attendanceStatistics.get(index).getBeLateNum()));
            }
            if (attendanceStatistics.get(index).getLeaveEarlyNum() == 0) {
                ((ItemViewHolder) holder).tvLeaveEarly.setVisibility(View.INVISIBLE);
            } else {
                ((ItemViewHolder) holder).tvLeaveEarly.setVisibility(View.VISIBLE);
                ((ItemViewHolder) holder).tvLeaveEarly.setText(String.valueOf(attendanceStatistics.get(index).getLeaveEarlyNum()));
            }
            if (attendanceStatistics.get(index).getLeaveNum() == 0) {
                ((ItemViewHolder) holder).tvLeave.setVisibility(View.INVISIBLE);
            } else {
                ((ItemViewHolder) holder).tvLeave.setVisibility(View.VISIBLE);
                ((ItemViewHolder) holder).tvLeave.setText(String.valueOf(attendanceStatistics.get(index).getLeaveNum()));
            }
            if (attendanceStatistics.get(index).getTruantNum() == 0) {
                ((ItemViewHolder) holder).tvAbsenteeism.setVisibility(View.INVISIBLE);
            } else {
                ((ItemViewHolder) holder).tvAbsenteeism.setVisibility(View.VISIBLE);
                ((ItemViewHolder) holder).tvAbsenteeism.setText(String.valueOf(attendanceStatistics.get(index).getTruantNum()));
            }

        }
    }

    @Override
    public int getItemCount() {
        return attendanceStatistics.size() == 0 ? 0 : attendanceStatistics.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TOP;
        } else {
            return TYPE_ITEM;
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.civ_attendance_student_portrait)
        CircleImageView civStudentPortrait;
        @BindView(R.id.tv_student_name)
        TextView tvStudentName;
        @BindView(R.id.tv_student_id)
        TextView tvStudentId;
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

    static class TopViewHolder extends RecyclerView.ViewHolder {
        public TopViewHolder(View itemView) {
            super(itemView);
        }
    }


}
