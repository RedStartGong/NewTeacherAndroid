package com.zidian.teacher.ui.evaluate.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.model.entity.remote.CheckColleagueEva;
import com.zidian.teacher.model.entity.remote.CheckSupervisorEva;
import com.zidian.teacher.model.entity.remote.EvaluateTag;
import com.zidian.teacher.recyclerviewpager.flowlayout.FlowTagLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GongCheng on 2017/4/20.
 */

public class SupervisorCheckAdapter extends RecyclerView.Adapter<SupervisorCheckAdapter.SimpleViewHolder> {

    private final Context context;
    private int itemCount;
    private Map<String, Integer> data = new HashMap<>();
    private List<EvaluateTag.ThreeIndexListBean> beans = new ArrayList<>();
    Map<Integer, String> map;
    private static final String[] TAGS = {"非常符合", "比较符合", "一般符合", "比较不符合", "非常不符合"};
    private String customEva;
    private SimpleViewHolder holder;

    public SupervisorCheckAdapter(Context context, List<EvaluateTag.ThreeIndexListBean> beans, String customEva) {
        this.context = context;
        this.beans = beans;
        itemCount = beans.size() % 4 == 0 ? beans.size() / 4 : (beans.size() / 4 + 1);
        this.customEva = customEva;
        map = new HashMap<Integer, String>();
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public TagTextAdapter tagTextAdapter1;
        public TagTextAdapter tagTextAdapter2;
        public TagTextAdapter tagTextAdapter3;
        public TagTextAdapter tagTextAdapter4;
        FlowTagLayout tagEvaluate1;
        FlowTagLayout tagEvaluate2;
        FlowTagLayout tagEvaluate3;
        FlowTagLayout tagEvaluate4;
        CardView card1;
        CardView card2;
        CardView card3;
        CardView card4;
        TextView title1;
        TextView title2;
        TextView title3;
        TextView title4;
        ImageView arrowBack;
        ImageView arrowNext;
        TextView tvCustomEva;
        TextView tvCustomEvaTitle;
        LinearLayout llCustomEva;

        public SimpleViewHolder(View view) {
            super(view);

            arrowBack = (ImageView) view.findViewById(R.id.arrow_back_iv);
            arrowNext = (ImageView) view.findViewById(R.id.arrow_next_iv);
            tagEvaluate1 = (FlowTagLayout) view.findViewById(R.id.item_evaluate_tags1);
            tagEvaluate2 = (FlowTagLayout) view.findViewById(R.id.item_evaluate_tags2);
            tagEvaluate3 = (FlowTagLayout) view.findViewById(R.id.item_evaluate_tags3);
            tagEvaluate4 = (FlowTagLayout) view.findViewById(R.id.item_evaluate_tags4);
            card1 = (CardView) view.findViewById(R.id.card1);
            card2 = (CardView) view.findViewById(R.id.card2);
            card3 = (CardView) view.findViewById(R.id.card3);
            card4 = (CardView) view.findViewById(R.id.card4);
            title1 = (TextView) view.findViewById(R.id.item_title1);
            title2 = (TextView) view.findViewById(R.id.item_title2);
            title3 = (TextView) view.findViewById(R.id.item_title3);
            title4 = (TextView) view.findViewById(R.id.item_title4);
            tvCustomEva = (TextView) view.findViewById(R.id.tv_custom_eva);
            tvCustomEvaTitle = (TextView) view.findViewById(R.id.tv_custom_eva_title);
            llCustomEva = (LinearLayout) view.findViewById(R.id.ll_custom_eva);
        }
    }


    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_evaluate_tab, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        this.holder = holder;
        holder.llCustomEva.setVisibility(View.GONE);
        if (beans.size() % 4 != 0) {

            if (position == itemCount - 1) {
                holder.arrowNext.setVisibility(View.GONE);
                holder.arrowBack.setVisibility(View.VISIBLE);
                if (beans.size() % 4 == 3) {
                    holder.card4.setVisibility(View.INVISIBLE);
                    holder.tagTextAdapter1 = new TagTextAdapter<>(context);
                    holder.tagTextAdapter2 = new TagTextAdapter<>(context);
                    holder.tagTextAdapter3 = new TagTextAdapter<>(context);
                    holder.tagEvaluate1.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
                    holder.tagEvaluate2.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
                    holder.tagEvaluate3.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
                    holder.tagEvaluate1.setAdapter(holder.tagTextAdapter1);
                    holder.tagEvaluate2.setAdapter(holder.tagTextAdapter2);
                    holder.tagEvaluate3.setAdapter(holder.tagTextAdapter3);

                    holder.tagTextAdapter1.setSelectCount(getTagSelectedIndex(beans.get(position * 4)));
                    holder.tagTextAdapter2.setSelectCount(getTagSelectedIndex(beans.get(position * 4 + 1)));
                    holder.tagTextAdapter3.setSelectCount(getTagSelectedIndex(beans.get(position * 4 + 2)));
                    holder.tagTextAdapter1.onlyAddAll(getNewTags());
                    holder.tagTextAdapter2.onlyAddAll(getNewTags());
                    holder.tagTextAdapter3.onlyAddAll(getNewTags());

                    holder.title1.setText(beans.get(position * 4).getTchQuestionStyle());
                    holder.title2.setText(beans.get(position * 4 + 1).getTchQuestionStyle());
                    holder.title3.setText(beans.get(position * 4 + 2).getTchQuestionStyle());
                } else if (beans.size() % 4 == 2) {
                    holder.card3.setVisibility(View.INVISIBLE);
                    holder.card4.setVisibility(View.INVISIBLE);
                    holder.tagTextAdapter1 = new TagTextAdapter<>(context);
                    holder.tagTextAdapter2 = new TagTextAdapter<>(context);
                    holder.tagEvaluate1.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
                    holder.tagEvaluate2.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
                    holder.tagEvaluate1.setAdapter(holder.tagTextAdapter1);
                    holder.tagEvaluate2.setAdapter(holder.tagTextAdapter2);

                    holder.tagTextAdapter1.setSelectCount(getTagSelectedIndex(beans.get(position * 4)));
                    holder.tagTextAdapter2.setSelectCount(getTagSelectedIndex(beans.get(position * 4 + 1)));
                    holder.tagTextAdapter1.onlyAddAll(getNewTags());
                    holder.tagTextAdapter2.onlyAddAll(getNewTags());

                    holder.title1.setText(beans.get(position * 4).getTchQuestionStyle());
                    holder.title2.setText(beans.get(position * 4 + 1).getTchQuestionStyle());
                } else if (beans.size() % 4 == 1) {
                    holder.card1.setVisibility(View.VISIBLE);
                    holder.card2.setVisibility(View.INVISIBLE);
                    holder.card3.setVisibility(View.INVISIBLE);
                    holder.card4.setVisibility(View.INVISIBLE);
                    holder.tagTextAdapter1 = new TagTextAdapter<>(context);
                    holder.tagEvaluate1.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
                    holder.tagEvaluate1.setAdapter(holder.tagTextAdapter1);

                    holder.tagTextAdapter1.setSelectCount(getTagSelectedIndex(beans.get(position * 4)));
                    holder.tagTextAdapter1.onlyAddAll(getNewTags());

                    holder.title1.setText(beans.get(position * 4).getTchQuestionStyle());
                }
            } else {
                if (position == 0) {
                    holder.arrowNext.setVisibility(View.VISIBLE);
                    holder.arrowBack.setVisibility(View.GONE);
                } else {
                    holder.arrowNext.setVisibility(View.VISIBLE);
                    holder.arrowBack.setVisibility(View.VISIBLE);
                }
                holder.card1.setVisibility(View.VISIBLE);
                holder.card2.setVisibility(View.VISIBLE);
                holder.card3.setVisibility(View.VISIBLE);
                holder.card4.setVisibility(View.VISIBLE);
                holder.tagTextAdapter1 = new TagTextAdapter<>(context);
                holder.tagTextAdapter2 = new TagTextAdapter<>(context);
                holder.tagTextAdapter3 = new TagTextAdapter<>(context);
                holder.tagTextAdapter4 = new TagTextAdapter<>(context);
                holder.tagEvaluate1.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
                holder.tagEvaluate2.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
                holder.tagEvaluate3.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
                holder.tagEvaluate4.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
                holder.tagEvaluate1.setAdapter(holder.tagTextAdapter1);
                holder.tagEvaluate2.setAdapter(holder.tagTextAdapter2);
                holder.tagEvaluate3.setAdapter(holder.tagTextAdapter3);
                holder.tagEvaluate4.setAdapter(holder.tagTextAdapter4);

                holder.tagTextAdapter1.setSelectCount(getTagSelectedIndex(beans.get(position * 4)));
                holder.tagTextAdapter2.setSelectCount(getTagSelectedIndex(beans.get(position * 4 + 1)));
                holder.tagTextAdapter3.setSelectCount(getTagSelectedIndex(beans.get(position * 4 + 2)));
                holder.tagTextAdapter4.setSelectCount(getTagSelectedIndex(beans.get(position * 4 + 3)));
                holder.tagTextAdapter1.onlyAddAll(getNewTags());
                holder.tagTextAdapter2.onlyAddAll(getNewTags());
                holder.tagTextAdapter3.onlyAddAll(getNewTags());
                holder.tagTextAdapter4.onlyAddAll(getNewTags());

                holder.title1.setText(beans.get(position * 4).getTchQuestionStyle());
                holder.title2.setText(beans.get(position * 4 + 1).getTchQuestionStyle());
                holder.title3.setText(beans.get(position * 4 + 2).getTchQuestionStyle());
                holder.title4.setText(beans.get(position * 4 + 3).getTchQuestionStyle());
            }
        } else {
            holder.arrowNext.setVisibility(View.VISIBLE);
            holder.arrowBack.setVisibility(View.VISIBLE);
            if (position == itemCount - 1) {
                holder.arrowNext.setVisibility(View.GONE);
                holder.arrowBack.setVisibility(View.VISIBLE);
            }
            if (position == 0) {
                holder.arrowNext.setVisibility(View.VISIBLE);
                holder.arrowBack.setVisibility(View.GONE);
            }
            holder.tagTextAdapter1 = new TagTextAdapter<>(context);
            holder.tagTextAdapter2 = new TagTextAdapter<>(context);
            holder.tagTextAdapter3 = new TagTextAdapter<>(context);
            holder.tagTextAdapter4 = new TagTextAdapter<>(context);
            holder.tagEvaluate1.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
            holder.tagEvaluate2.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
            holder.tagEvaluate3.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
            holder.tagEvaluate4.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
            holder.tagEvaluate1.setAdapter(holder.tagTextAdapter1);
            holder.tagEvaluate2.setAdapter(holder.tagTextAdapter2);
            holder.tagEvaluate3.setAdapter(holder.tagTextAdapter3);
            holder.tagEvaluate4.setAdapter(holder.tagTextAdapter4);

            holder.tagTextAdapter1.setSelectCount(getTagSelectedIndex(beans.get(position * 4)));
            holder.tagTextAdapter2.setSelectCount(getTagSelectedIndex(beans.get(position * 4 + 1)));
            holder.tagTextAdapter3.setSelectCount(getTagSelectedIndex(beans.get(position * 4 + 2)));
            holder.tagTextAdapter4.setSelectCount(getTagSelectedIndex(beans.get(position * 4 + 3)));
            holder.tagTextAdapter1.onlyAddAll(getNewTags());
            holder.tagTextAdapter2.onlyAddAll(getNewTags());
            holder.tagTextAdapter3.onlyAddAll(getNewTags());
            holder.tagTextAdapter4.onlyAddAll(getNewTags());

            holder.title1.setText(beans.get(position * 4).getTchQuestionStyle());
            holder.title2.setText(beans.get(position * 4 + 1).getTchQuestionStyle());
            holder.title3.setText(beans.get(position * 4 + 2).getTchQuestionStyle());
            holder.title4.setText(beans.get(position * 4 + 3).getTchQuestionStyle());
        }
        if (data.get("tagEvaluate1" + position) != null) {
            holder.tagTextAdapter1.setSelectCount(data.get("tagEvaluate1" + position));
        }
        if (data.get("tagEvaluate2" + position) != null) {

            holder.tagTextAdapter2.setSelectCount(data.get("tagEvaluate2" + position));
        }
        if (data.get("tagEvaluate3" + position) != null) {

            holder.tagTextAdapter3.setSelectCount(data.get("tagEvaluate3" + position));
        }
        if (data.get("tagEvaluate4" + position) != null) {

            holder.tagTextAdapter4.setSelectCount(data.get("tagEvaluate4" + position));
        }


    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    /**
     * 设置新的标签
     *
     * @return
     */
    private List<String> getNewTags() {
        List<String> textTag = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            textTag.add(TAGS[i]);
        }
        return textTag;
    }

    /**
     * 得到预选TAG的下标
     *
     * @return
     */
    private int getTagSelectedIndex(EvaluateTag.ThreeIndexListBean bean) {
        for (int i = 0; i < bean.getLabelList().size(); i++) {
            if (bean.getLabelList().get(i).getLabelId() == bean.getEvaluateLabel()) {
                return i;
            }
        }
        return 0;
    }
}
