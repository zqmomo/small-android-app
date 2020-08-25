package com.example.android.forq.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.example.android.forq.R;
import com.example.android.forq.ui.fragment.NewsContentFragment;

import androidx.fragment.app.FragmentActivity;

public class NewsContentActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置活动布局
        setContentView(R.layout.item_news_content);
        //获取传入此活动的新闻标题
        String newsTitle = getIntent().getStringExtra("news_title");
        //获取传入此活动的新闻内容
        String newsContent = getIntent().getStringExtra("news_content");
        //获取NewsContentFragment类
        NewsContentFragment contentFragment = (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        //调用NewsContentFragment中refresh方法刷新新闻内容
        assert contentFragment != null;
        contentFragment.refresh(newsTitle, newsContent);
    }

    public static void actionStart(Context context, String newsTitle, String newsContent){
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title", newsTitle);
        intent.putExtra("news_content", newsContent);
        context.startActivity(intent);
    }
}
