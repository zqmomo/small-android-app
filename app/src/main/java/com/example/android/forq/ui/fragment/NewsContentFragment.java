package com.example.android.forq.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.forq.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class NewsContentFragment extends Fragment {
    private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //加载news_content_frag.xml布局
        view = inflater.inflate(R.layout.item_news_content_frag, container, false);
        return view;
    }

    /**
     * 用于刷新新闻标题和内容
     *
     * @param newsTitle
     * @param newsContent
     */
    public void refresh(String newsTitle, String newsContent) {
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView title = view.findViewById(R.id.news_title);
        title.setText(newsTitle);
        TextView content = view.findViewById(R.id.news_content);
        content.setText(newsContent);
    }
}
