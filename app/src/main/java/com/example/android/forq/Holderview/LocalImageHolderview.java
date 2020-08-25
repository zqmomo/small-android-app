package com.example.android.forq.Holderview;

import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.example.android.forq.R;

public class LocalImageHolderview extends Holder<Integer> {
    private ImageView imageView;

    public LocalImageHolderview(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        imageView =itemView.findViewById(R.id.ivPost);
    }

    @Override
    public void updateUI(Integer data) {
        imageView.setImageResource(data);
    }

}
