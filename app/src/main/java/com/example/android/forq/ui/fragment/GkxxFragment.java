package com.example.android.forq.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.android.forq.Holderview.LocalImageHolderview;
import com.example.android.forq.R;
import com.example.android.forq.activity.NewsContentActivity;
import com.example.android.forq.adapter.NewsAdapter;
import com.example.android.forq.base.BaseFragment;
import com.example.android.forq.base.BasePresenter;
import com.example.android.forq.comm.News;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GkxxFragment extends BaseFragment<BasePresenter> implements OnItemClickListener {
    private List<News> newsList;
    private NewsAdapter adapter;

    @BindView(R.id.banner)
    ConvenientBanner convenientBanner;//顶部广告栏控件

    private ArrayList<Integer> localImages = new ArrayList<>();
    private static int position1=0;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        newsList = getNews();
        //将ListView单项布局和新闻数据加载到Adapter中
        adapter = new NewsAdapter(getActivity(), R.layout.item_new, newsList);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // layout对应界面，这里只用写这么一句
            return LayoutInflater.from(requireActivity()).inflate(R.layout.fragment_gkxx, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        ListView newsTitleListView = view.findViewById(R.id.news_title_list);
        newsTitleListView.setAdapter(adapter);
        newsTitleListView.setOnItemClickListener((parent, view1, i, l) -> {
            News news = newsList.get(i);
            NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
        });
        init();
    }

    private void init(){
        loadTestDatas();
        convenientBanner.setPages(
                new CBViewHolderCreator() {
                    @Override
                    public LocalImageHolderview createHolder(View itemView) {
                        return new LocalImageHolderview(itemView);
                    }

                    @Override
                    public int getLayoutId() {
                        return R.layout.item_localimage;
                    }
                },localImages)
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                .setOnItemClickListener(this);

    }



    /*
    加入测试Views
    * */
    private void loadTestDatas() {
        //本地图片集合
        for (; position1 < 3; position1++)
            localImages.add(getResId("image" + position1));

    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        News news1 = new News();
        news1.setTitle("新闻标题1");
        news1.setContent("测试新闻内容1");
        newsList.add(news1);
        News news2 = new News();
        news2.setTitle("新闻标题2");
        news2.setContent("测试新闻内容2");
        newsList.add(news2);
        return newsList;
    }
    /**
     * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
     */
    private static int getResId(String variableName) {
        try {
            Field idField = R.drawable.class.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    // 开始自动翻页
    @Override
    public void onResume() {
        super.onResume();
//        //开始自动翻页
        convenientBanner.startTurning();
    }

    // 停止自动翻页
    @Override
    public void onPause() {
        super.onPause();
//        //停止翻页
        convenientBanner.stopTurning();
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getActivity(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
    }

        @Override
    public BasePresenter getPresenter() {
        return new BasePresenter() {
            @Override
            public void unsubscribe() {
            }
        };
    }
}
