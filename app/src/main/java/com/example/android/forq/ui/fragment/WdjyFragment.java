package com.example.android.forq.ui.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.forq.R;
import com.example.android.forq.base.BaseFragment;
import com.example.android.forq.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WdjyFragment extends BaseFragment<BasePresenter> {


    @BindView(R.id.wdjy_wfli)
    ViewFlipper viewfli;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(requireActivity()).inflate(R.layout.fragment_wdjy, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
//        listView = view.findViewById(R.id.wdjy_edlv);
//        listView.setAdapter(new MyEdlvAdapter(Constants.GROUPS,Constants.CHILDS));
//        listView.setGroupIndicator(null);
//        view.findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(Objects.requireNonNull(getActivity()), SdtfActivity.class);
//                startActivity(intent);
//                //Toast.makeText(getActivity(),"imageButton",Toast.LENGTH_SHORT).show();
//            }
//        });
        getData();

    }

    /**
    * @Description:要显示的文字信息
     */

    @SuppressLint("SetTextI18n")
    private void getData(){
        for(int i = 0; i < 4; i++){
            TextView tv = new TextView(getActivity());
            tv.setText("这是测试用的第 "+i+"行测试数据");
            viewfli.addView(tv);
        }
        viewfli.setInAnimation(getActivity(),R.anim.push_up_in);
        viewfli.setOutAnimation(getActivity(),R.anim.push_up_out);
        viewfli.startFlipping();
    }

    @Override
    public BasePresenter getPresenter() {
        return new BasePresenter() {
            @Override
            public void unsubscribe() {
            }
        };
    }
    @Override
    public void onItemClick(int position){}
}
