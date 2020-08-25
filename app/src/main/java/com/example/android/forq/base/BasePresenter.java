package com.example.android.forq.base;

import android.content.Context;

public abstract class BasePresenter {
    Context mContext;
    public abstract void unsubscribe();
}
