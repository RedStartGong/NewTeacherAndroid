package com.zidian.teacher.ui.questionnaire.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.zidian.teacher.R;
import com.zidian.teacher.ui.questionnaire.bean.QuestionAddBean;


import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/8/24 0024.
 */
public class QuestionnaireAddAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int ITEM_FOOT = 2;
    private static final int ITEM_NOT_FOOT = 3;
    private Context context;
    private List<QuestionAddBean> data = new ArrayList<>();
    private Map<Integer, String> tabName = new HashMap<>();


    public QuestionnaireAddAdapter(Context context, List<QuestionAddBean> data) {
        this.context = context;
        this.data = data;
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 添加题目点击事件接口
     */
    public interface OnFootItemClickListener {
        void onFootItemClick(int position);
    }

    private OnFootItemClickListener onFootItemClickListener;

    public void setOnFootItemClickListener(OnFootItemClickListener onFootItemClickListener) {
        this.onFootItemClickListener = onFootItemClickListener;
    }

    /**
     * 添加答案选项点击事件接口
     */
    public interface OnItemFootItemClickListener {
        void onItemFootItemClick(int pId, int position);
    }

    private OnItemFootItemClickListener onItemFootItemClickListener;

    public void setOnItemFootItemClickListener(OnItemFootItemClickListener onItemFootItemClickListener) {
        this.onItemFootItemClickListener = onItemFootItemClickListener;
    }

    /**
     * 删除答案选项接口
     */
    public interface OnQuestionDeleteListener {
        void onQuestionDeleteListener(int pId, int position);
    }

    private OnQuestionDeleteListener onQuestionDeleteListener;

    public void setOnQuestionDeleteListener(OnQuestionDeleteListener onQuestionDeleteListener) {
        this.onQuestionDeleteListener = onQuestionDeleteListener;
    }

    @Override
    public int getItemCount() {
        return data.size() == 0 ? 0 : (data.size() + 1);
    }

    @Override
    public int getItemViewType(int position) {

        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else if (data.get(position).isChild() && data.get(position).isLastChild()) {
            return ITEM_FOOT;
        } else if (data.get(position).isChild() && !data.get(position).isLastChild()) {
            return ITEM_NOT_FOOT;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_question_add_question, parent,
                    false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_question_add_foot, parent,
                    false);
            return new FootViewHolder(view);
        } else if (viewType == ITEM_FOOT) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_question_add_item_foot, parent,
                    false);
            return new ItemFootViewHolder(view);
        } else if (viewType == ITEM_NOT_FOOT) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_question_add_item, parent,
                    false);
            return new ItemNOTFootViewHolder(view);
        }
        return null;
    }

    /**
     * 输入题目
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {

            ((ItemViewHolder) holder).questionName.setText(data.get(position).getQuestionName());

            ((ItemViewHolder) holder).questionName.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String name = ((ItemViewHolder) holder).questionName.getText().toString().trim();
                    if (name.length() > 0) {
                        int position = holder.getLayoutPosition();
                        data.get(position).setQuestionName(name);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, position);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemLongClick(holder.itemView, position);
                        return false;
                    }
                });
            }


        } else if (holder instanceof FootViewHolder) {

            if (onFootItemClickListener != null) {

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        onFootItemClickListener.onFootItemClick(position);
                    }
                });

            }


        } else if (holder instanceof ItemFootViewHolder) {

            if (onItemFootItemClickListener != null) {
                holder.itemView
                        .setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                int position = holder.getLayoutPosition();
                                onItemFootItemClickListener.onItemFootItemClick(data.get(position).getpId(), position);
                            }
                        });
            }


        } else if (holder instanceof ItemNOTFootViewHolder) {
            //添加选项
            ((ItemNOTFootViewHolder) holder).itemName.setText(data.get(position).getItemName());

            ((ItemNOTFootViewHolder) holder).itemName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    String name = ((ItemNOTFootViewHolder) holder).itemName.getText().toString().trim();
                    if (name.length() > 0) {
                        int position = holder.getLayoutPosition();
                        data.get(position).setItemName(name);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            //删除选项
            ((ItemNOTFootViewHolder) holder).deleteIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    onQuestionDeleteListener.onQuestionDeleteListener(data.get(position).getpId(), position);
                }
            });


        }
    }


    static class ItemViewHolder extends RecyclerView.ViewHolder {

        EditText questionName;

        public ItemViewHolder(View view) {
            super(view);
            questionName = (EditText) view.findViewById(R.id.question_add_title);
        }
    }

    /**
     * 添加题目
     */
    static class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View view) {
            super(view);
        }
    }

    /**
     * 添加答案选项
     */
    static class ItemFootViewHolder extends RecyclerView.ViewHolder {

        public ItemFootViewHolder(View view) {
            super(view);
        }
    }

    /**
     * 添加答案选项啊的内容
     */
    class ItemNOTFootViewHolder extends RecyclerView.ViewHolder {
        EditText itemName;
        ImageView deleteIv;

        public ItemNOTFootViewHolder(View view) {
            super(view);
            itemName = (EditText) view.findViewById(R.id.question_add_title);
            deleteIv = (ImageView) view.findViewById(R.id.question_delete);

        }

    }

}
