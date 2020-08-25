package com.example.android.forq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.forq.R;
import com.example.android.forq.comm.News;

import java.util.List;

import androidx.annotation.NonNull;

public class NewsAdapter extends ArrayAdapter<News> {
    private int resourceId;
    public NewsAdapter(Context context, int resource, List<News> objects) {
        super(context, resource,objects);
        resourceId = resource;
//		Log.d(TAG, objects.size() + "");
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
//		super.getView(position, convertView, parent);
        View view ;
//		News news = newsList.get(position);
        News news = getItem(position);//获取position位置的新闻数据
//		Log.d(TAG, news.getTitle());
        //如果当前项视图没有加载布局，则进行加载否则直接从converView对象中获取，防止重复加载降低效率
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        }
        else {
            view = convertView;
        }
        TextView tv = view.findViewById(R.id.news_title);
        assert news != null;
        tv.setText(news.getTitle());
        return view;
    }

}
