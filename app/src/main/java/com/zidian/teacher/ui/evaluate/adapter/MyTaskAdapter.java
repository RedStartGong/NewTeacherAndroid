package com.zidian.teacher.ui.evaluate.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.model.entity.remote.MyTask;
import com.zidian.teacher.ui.evaluate.listener.MyTaskOnClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by GongCheng on 2017/4/14.
 */

public class MyTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_COLLEAGUE = 0;
    private static final int TYPE_SUPERVISOR = 1;

    private List<MyTask> tasks;

    @Inject
    public MyTaskAdapter() {

    }

    public void setTasks(List<MyTask> tasks) {
        this.tasks = tasks;
    }

    public void removeTask(int position) {
        tasks.remove(position);
        notifyItemRemoved(position + 1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_COLLEAGUE) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_task_colleague, parent, false);
            return new ColleagueHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_task_supervisor, parent, false);
            return new SupervisorHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        //myRole = 0 我是发起请求的人 myRole = 1 我是被请求的人
        //requestType :请求类型：0为请求评价别人，1为请求别人评价‘我’
        //myRole 跟 requestType
        // [0,0]表示‘我发起请求评价别人’
        // [0,1]表示‘我请求别人评价我’
        // [1,0]表示‘别人请求评价我’
        // [1,1]表示‘别人请求我评价他
        //requestState:请求所处状态：0为请求发出，待确认；1为同意，待评价；2为已拒绝；3为已完成
        int requestState = tasks.get(position).getRequestState();
        int myRole = tasks.get(position).getMyRole();
        int requestType = tasks.get(position).getRequestType();

        if (holder instanceof ColleagueHolder) {//同行评价
            switch (requestState) {
                //未确认
                case 0:
                    if (myRole == 0) {
                        ((ColleagueHolder) holder).viewUntreated.setVisibility(View.VISIBLE);
                        ((ColleagueHolder) holder).viewInvite.setVisibility(View.GONE);
                    } else {
                        ((ColleagueHolder) holder).viewInvite.setVisibility(View.VISIBLE);
                        ((ColleagueHolder) holder).viewUntreated.setVisibility(View.GONE);
                    }
                    break;
                //待评价
                case 1:
                    if (myRole == 0 && requestType == 0) {
                        ((ColleagueHolder) holder).viewUntreated.setVisibility(View.GONE);
                        ((ColleagueHolder) holder).viewEvaluate.setVisibility(View.VISIBLE);
                    } else if (myRole == 0 && requestType == 1) {
                        ((ColleagueHolder) holder).viewEvaluate.setVisibility(View.GONE);
                        ((ColleagueHolder) holder).viewUntreated.setVisibility(View.VISIBLE);
                    } else if (myRole == 1 && requestType == 0) {
                        ((ColleagueHolder) holder).viewUntreated.setVisibility(View.VISIBLE);
                        ((ColleagueHolder) holder).viewEvaluate.setVisibility(View.GONE);
                    } else {
                        ((ColleagueHolder) holder).viewUntreated.setVisibility(View.GONE);
                        ((ColleagueHolder) holder).viewEvaluate.setVisibility(View.VISIBLE);
                    }
                    break;
                //已拒绝
                case 2:
                    ((ColleagueHolder) holder).viewRejected.setVisibility(View.VISIBLE);
                    ((ColleagueHolder) holder).viewCheck.setVisibility(View.GONE);
                    ((ColleagueHolder) holder).viewFinished.setVisibility(View.GONE);
                    break;
                //已完成
                case 3:
                    //我申请评价别人—已完成 -- 可查看
                    if (myRole == 0 && requestType == 0) {
                        ((ColleagueHolder) holder).viewRejected.setVisibility(View.GONE);
                        ((ColleagueHolder) holder).viewCheck.setVisibility(View.VISIBLE);
                        ((ColleagueHolder) holder).viewFinished.setVisibility(View.GONE);

                    } else if (myRole == 1 && requestType == 1) {
                        ((ColleagueHolder) holder).viewRejected.setVisibility(View.GONE);
                        ((ColleagueHolder) holder).viewCheck.setVisibility(View.VISIBLE);
                        ((ColleagueHolder) holder).viewFinished.setVisibility(View.GONE);
                    } else {
                        ((ColleagueHolder) holder).viewRejected.setVisibility(View.GONE);
                        ((ColleagueHolder) holder).viewCheck.setVisibility(View.GONE);
                        ((ColleagueHolder) holder).viewFinished.setVisibility(View.VISIBLE);
                    }
                    break;
            }

            if (myRole == 0 && requestType == 0) {
                //我申请评价别人
                ((ColleagueHolder) holder).tvEvaluateName.setText("我");
                ((ColleagueHolder) holder).tvEvaluatedName.setText(context.getString(R.string.evaluate_others_course,
                        tasks.get(position).getToTeacherName()));
                ((ColleagueHolder) holder).ivEvaluateAction.setImageResource(R.drawable.ic_apply_evaluate);

            } else if (myRole == 0 && requestType == 1) {
                //我邀请别人评价我
                ((ColleagueHolder) holder).ivEvaluateAction.setImageResource(R.drawable.ic_invite_evaluate);
                ((ColleagueHolder) holder).tvEvaluateName.setText("我");
                ((ColleagueHolder) holder).tvEvaluatedName.setText(context.getString(R.string.someone_evaluate_my_course,
                        tasks.get(position).getToTeacherName()));
            } else if (myRole == 1 && requestType == 0) {
                //别人申请评价我的课
                ((ColleagueHolder) holder).ivEvaluateAction.setImageResource(R.drawable.ic_apply_evaluate);
                ((ColleagueHolder) holder).tvEvaluateName.setText(tasks.get(position).getToTeacherName());
                ((ColleagueHolder) holder).tvEvaluatedName.setText(R.string.evaluate_my_course);
            } else {
                //别人邀请我评价他的课
                ((ColleagueHolder) holder).ivEvaluateAction.setImageResource(R.drawable.ic_invite_evaluate);
                ((ColleagueHolder) holder).tvEvaluateName.setText(tasks.get(position).getToTeacherName());
                ((ColleagueHolder) holder).tvEvaluatedName.setText(R.string.evaluate_his_course);
            }
            //公用组件
            ((ColleagueHolder) holder).tvEvaluateCourse.setText(tasks.get(position).getCourseName());
            ((ColleagueHolder) holder).tvCourseDate.setText(tasks.get(position).getTeachingCalendar());
            ((ColleagueHolder) holder).tvCourseLocation.setText(tasks.get(position).getCourseLocation());
            ((ColleagueHolder) holder).tvMessage.setText(tasks.get(position).getRequestExplain());
        } else if (holder instanceof SupervisorHolder) {//督导评价
            //myRole = 0 我是发起请求的人 myRole = 1 我是被请求的人
            //requestType :请求类型：0为请求评价别人，1为请求别人评价‘我’
            //requestState:请求所处状态：0为请求发出，待确认；1为同意，待评价；2为已拒绝；3为已完成
            switch (requestState) {
                case 0:
                    if (myRole == 0) {
                        ((SupervisorHolder) holder).viewUntreated.setVisibility(View.VISIBLE);
                        ((SupervisorHolder) holder).viewConfirm.setVisibility(View.GONE);
                    } else {
                        ((SupervisorHolder) holder).viewUntreated.setVisibility(View.GONE);
                        ((SupervisorHolder) holder).viewConfirm.setVisibility(View.VISIBLE);
                    }
                    break;
                case 1:
                    if (myRole == 0) {
                        ((SupervisorHolder) holder).viewEvaluate.setVisibility(View.VISIBLE);
                        ((SupervisorHolder) holder).viewUntreated.setVisibility(View.GONE);
                    } else {
                        ((SupervisorHolder) holder).viewEvaluate.setVisibility(View.GONE);
                        ((SupervisorHolder) holder).viewUntreated.setVisibility(View.VISIBLE);
                    }
                    break;
                case 2:
                    if (myRole == 0) {
                        ((SupervisorHolder) holder).viewCheck.setVisibility(View.VISIBLE);
                        ((SupervisorHolder) holder).viewFinished.setVisibility(View.GONE);
                    } else {
                        ((SupervisorHolder) holder).viewCheck.setVisibility(View.GONE);
                        ((SupervisorHolder) holder).viewFinished.setVisibility(View.VISIBLE);
                    }
                    break;
            }
            if (myRole== 0) {//我发起请求
                ((SupervisorHolder) holder).tvEvaluateName.setText("我");
                ((SupervisorHolder) holder).tvEvaluatedName.setText(
                        context.getString(R.string.others_course, tasks.get(position).getToTeacherName()));
            } else {//我是被请求的人
                ((SupervisorHolder) holder).tvEvaluateName.setText(tasks.get(position).getToTeacherName());
                ((SupervisorHolder) holder).tvEvaluatedName.setText(context.getString(R.string.my_course));
            }
            //公用组件
            ((SupervisorHolder) holder).ivEvaluateAction.setImageResource(R.drawable.ic_evaluate_others);
            ((SupervisorHolder) holder).tvEvaluateCourse.setText(tasks.get(position).getCourseName());
            ((SupervisorHolder) holder).tvCourseDate.setText(tasks.get(position).getTeachingCalendar());
            ((SupervisorHolder) holder).tvCourseLocation.setText(tasks.get(position).getCourseLocation());
        }
    }

    @Override
    public int getItemCount() {
        return tasks == null ? 0 : tasks.size();
    }

    /**
     * evaluationType 评价类型：2代表教师评价，9代表督导评价
     */
    @Override
    public int getItemViewType(int position) {
        if (tasks.get(position).getEvaluationType() == 2) {
            return TYPE_COLLEAGUE;
        } else {
            return TYPE_SUPERVISOR;
        }
    }

    private MyTaskOnClickListener myTaskOnClickListener;

    public void setMyTaskOnClickListener(MyTaskOnClickListener myTaskOnClickListener) {
        this.myTaskOnClickListener = myTaskOnClickListener;
    }

    class ColleagueHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_evaluate_name)
        TextView tvEvaluateName;
        @BindView(R.id.iv_evaluate_action)
        ImageView ivEvaluateAction;
        @BindView(R.id.tv_evaluated_name)
        TextView tvEvaluatedName;
        @BindView(R.id.tv_evaluate_course)
        TextView tvEvaluateCourse;
        @BindView(R.id.tv_course_date)
        TextView tvCourseDate;
        @BindView(R.id.tv_course_location)
        TextView tvCourseLocation;
        @BindView(R.id.tv_message)
        TextView tvMessage;
        @BindView(R.id.view_invite)
        LinearLayout viewInvite;
        @BindView(R.id.view_untreated)
        TextView viewUntreated;
        @BindView(R.id.view_evaluate)
        TextView viewEvaluate;
        @BindView(R.id.view_unevaluate)
        TextView viewUnevaluated;
        @BindView(R.id.view_rejected)
        TextView viewRejected;
        @BindView(R.id.view_finished)
        TextView viewFinished;
        @BindView(R.id.view_check)
        TextView viewCheck;

        public ColleagueHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.view_evaluate, R.id.view_check, R.id.tv_agree, R.id.tv_reject})
        public void onClick(View view) {
            if (myTaskOnClickListener == null) {
                return;
            }
            // 使用了XRecyclerView的缘故,这个的adapterPosition比实际多了 1

            switch (view.getId()) {
                case R.id.view_evaluate:
                    myTaskOnClickListener.evaluate(getAdapterPosition() - 1);
                    break;
                case R.id.view_check:
                    myTaskOnClickListener.colleagueCheck(getAdapterPosition() - 1);
                    break;
                case R.id.tv_agree:
                    myTaskOnClickListener.agree(getAdapterPosition() - 1);
                    break;
                case R.id.tv_reject:
                    myTaskOnClickListener.reject(getAdapterPosition() - 1);
                    break;
            }
        }
    }

    class SupervisorHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_evaluate_name)
        TextView tvEvaluateName;
        @BindView(R.id.iv_evaluate_action)
        ImageView ivEvaluateAction;
        @BindView(R.id.tv_evaluated_name)
        TextView tvEvaluatedName;
        @BindView(R.id.tv_evaluate_course)
        TextView tvEvaluateCourse;
        @BindView(R.id.tv_course_date)
        TextView tvCourseDate;
        @BindView(R.id.tv_course_location)
        TextView tvCourseLocation;
        @BindView(R.id.view_check)
        TextView viewCheck;
        @BindView(R.id.view_confirm)
        TextView viewConfirm;
        @BindView(R.id.view_evaluate)
        TextView viewEvaluate;
        @BindView(R.id.view_untreated)
        TextView viewUntreated;
        @BindView(R.id.view_finished)
        TextView viewFinished;


        public SupervisorHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.view_confirm, R.id.view_evaluate, R.id.view_check})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.view_confirm:
                    myTaskOnClickListener.supervisorConfirm(getAdapterPosition() - 1);
                    break;
                case R.id.view_evaluate:
                    myTaskOnClickListener.supervisorEvaluate(getAdapterPosition() - 1);
                    break;
                case R.id.view_check:
                    myTaskOnClickListener.supervisorCheck(getAdapterPosition() - 1);
                    break;
            }
        }
    }
}
