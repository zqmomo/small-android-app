package com.example.android.forq.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.forq.R;
import com.example.android.forq.base.BaseActivity;
import com.example.android.forq.base.BasePresenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SdtfActivity extends BaseActivity<BasePresenter> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_grzx);
    }

    @Override
    public com.example.android.forq.base.BasePresenter getPresenter() {
        return new com.example.android.forq.base.BasePresenter() {
            @Override
            public void unsubscribe() {

            }
        };
    }
}
