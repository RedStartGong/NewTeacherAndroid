package com.zidian.teacher.ui.evaluate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zidian.teacher.R;
import com.zidian.teacher.recyclerviewpager.flowlayout.OnInitSelectedPosition;

import java.util.ArrayList;
import java.util.List;




/**
 * Created by HanHailong on 15/10/19.
 */
public class TagTextAdapter<T> extends BaseAdapter implements OnInitSelectedPosition {

    private final Context mContext;
    private final List<T> mDataList;

    public TagTextAdapter(Context context) {
        this.mContext = context;
        mDataList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_evaluate_text_tag, null);

        TextView textView = (TextView) view.findViewById(R.id.tv_tag);
        T t = mDataList.get(position);

        if (t instanceof String) {
            textView.setText((String) t);
        }
        return view;
    }

    public void onlyAddAll(List<T> datas) {
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<T> datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }

    private int count = 99;

    public void setSelectCount(int position) {
        count = position;
    }


    @Override
    public boolean isSelectedPosition(int position) {
        if (position == count) {
            return true;
        }
        return false;
    }
}
