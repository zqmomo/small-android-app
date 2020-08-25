package com.example.android.forq.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.forq.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qian Zhou on 2020/4/17.
 */

public class MyEdlvAdapter extends BaseExpandableListAdapter {
    private String[] groupData;
    private String[][] childData;
    //                用于存放Indicator的集合
    private SparseArray<ImageView> mIndicators;

    public MyEdlvAdapter(String[] groupData, String[][] childData) {
        this.groupData = groupData;
        this.childData = childData;
        mIndicators = new SparseArray<>();
    }

    //            根据分组的展开闭合状态设置指示器
    public void setIndicatorState(int groupPosition, boolean isExpanded) {
        if (isExpanded) {
            mIndicators.get(groupPosition).setImageResource(R.drawable.ic_chevron_right_black_24dp);
        } else {
            mIndicators.get(groupPosition).setImageResource(R.drawable.ic_expand_more_black_24dp);
        }
    }

    @Override
    public int getGroupCount() {
        return groupData.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childData[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupData[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childData[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wdjy_parent_view, parent, false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.wdjy_parent_textview_id);
            groupViewHolder.ivIndicator = (ImageView) convertView.findViewById(R.id.parent_image);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        if(isExpanded){
            groupViewHolder.ivIndicator.setImageResource(R.drawable.ic_chevron_right_black_24dp);
        } else {
            groupViewHolder.ivIndicator.setImageResource(R.drawable.ic_expand_more_black_24dp);
        }
        groupViewHolder.tvTitle.setText(groupData[groupPosition]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View
            convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wdjy_child_item, parent, false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.wdjy_child_id);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.tvTitle.setText(childData[groupPosition][childPosition]);
        // 设置点击事件
        convertView.setOnClickListener(view -> {
            // 测试吧
           // Toast.makeText(context, childData[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }




    private static class GroupViewHolder {
        TextView tvTitle;
        ImageView ivIndicator;
    }

    private static class ChildViewHolder {
        TextView tvTitle;
    }

}
