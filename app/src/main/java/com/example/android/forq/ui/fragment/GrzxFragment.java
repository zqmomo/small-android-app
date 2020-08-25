package com.example.android.forq.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.forq.R;
import com.example.android.forq.activity.FkxxActivity;
import com.example.android.forq.activity.LoginActivity;
import com.example.android.forq.base.BaseFragment;
import com.example.android.forq.base.BasePresenter;
import com.example.android.forq.util.SpUtil;

import java.util.Objects;

public class GrzxFragment extends BaseFragment<BasePresenter> {
    private String grzx_wdnc;
    public TextView wdnc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(requireActivity()).inflate(R.layout.fragment_grzx, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        grzx_wdnc = (String) SpUtil.get(Objects.requireNonNull(getActivity()),"username","");
        wdnc = view.findViewById(R.id.grzx_wdnc);
        wdnc.setText(grzx_wdnc);
        view.findViewById(R.id.grzx_tc).setOnClickListener(view1 -> showBottomDialog());
        view.findViewById(R.id.grzx_fkxx).setOnClickListener(view2 -> {
            Intent intent = new Intent();
            intent.setClass(Objects.requireNonNull(getActivity()), FkxxActivity.class);
            startActivity(intent);
        });
        view.findViewById(R.id.grzx_qhzh).setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.setClass(Objects.requireNonNull(getActivity()), LoginActivity.class);
            startActivity(intent);
        });
    }

    private void showBottomDialog() {
        //使用Dialog、设置style
        final Dialog dialog = new Dialog(Objects.requireNonNull(getActivity()),R.style.DialogTheme);
        //设置布局
        View view = View.inflate(getActivity(),R.layout.dialog_custom_layout,null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        //设置弹出位置
        assert window != null;
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        dialog.findViewById(R.id.tc_tcdl).setOnClickListener(view1 -> dialog.dismiss());
        dialog.findViewById(R.id.tc_gbcx).setOnClickListener(view1 -> dialog.dismiss());
        dialog.findViewById(R.id.tc_cancel).setOnClickListener(view1 -> dialog.dismiss());
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
