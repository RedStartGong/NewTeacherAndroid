package com.zidian.teacher.ui.evaluate.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zidian.teacher.R;
import com.zidian.teacher.model.entity.remote.CustomEva;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/8/24 0024.
 */
public class CustomEvaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<CustomEva.ListBean> data;
    private static boolean hasMore = true;

    public CustomEvaAdapter(Context context, List<CustomEva.ListBean> data) {
        this.context = context;
        this.data = data;
    }


    @Override
    public int getItemCount() {
        return data.size() == 0 ? 0 : data.size() + 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_evaluate_custom_req, parent,
                false);
        return new ItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).evaluateName.setText(data.get(position).getNickname());
        ((ItemViewHolder) holder).evaluateContent.setText(data.get(position).getContent());
        ((ItemViewHolder) holder).evaluateTime.setText(data.get(position).getTime());
        Glide.with(context)
                .load(data.get(position).getHeadportraitUrl())
                .placeholder(R.drawable.ic_student)
                .centerCrop()
                .into(((ItemViewHolder) holder).evaluateImage);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_evaluate_custom_name)
        TextView evaluateName;
        @BindView(R.id.tv_evaluate_custom_time)
        TextView evaluateTime;
        @BindView(R.id.tv_evaluate_custom_content)
        TextView evaluateContent;
        @BindView(R.id.iv_evaluate_custom_image)
        ImageView evaluateImage;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

