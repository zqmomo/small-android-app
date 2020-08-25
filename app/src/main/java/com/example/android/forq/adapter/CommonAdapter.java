package com.example.android.forq.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.*;

/**
 * 万能Adapter
 * 自动绑定Recycler数据
 * @param <T>
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<T> mData;
    private int mLayoutId;

    public CommonAdapter(Context context,List<T> data,int layoutId){
        this.mContext = context;
        this.mData = data;
        this.mLayoutId = layoutId;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        // 先inflate数据
        View itemView = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        // 返回ViewHolder
        return new ViewHolder(itemView) {};
    }

    @Override
     public void onBindViewHolder(ViewHolder holder,int  position) {

        // 绑定怎么办？回传出去
        convert(holder, mData.get(position),position);
    }

    /**
     * 利用抽象方法回传出去，每个不一样的Adapter去设置
     * @param nowData 当前的数据
     */
    public abstract void convert(ViewHolder holder, T nowData, int position);

    @Override
    public int getItemCount() {
        return mData.size();
    }
}