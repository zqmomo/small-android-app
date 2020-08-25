package com.example.android.forq.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.forq.R;
import com.example.android.forq.base.BaseActivity;
import com.example.android.forq.base.BasePresenter;

public class FkxxActivity extends BaseActivity<BasePresenter> {
    //反馈信息长度初始值
    private int num = 0;
    //最大长度
    public int maxNum = 50;

    private EditText mPublish;
    private TextView mPublishTextNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grzx_fkxx);
        mPublishTextNum = findViewById(R.id.publish_text_num);
        mPublish = findViewById(R.id.fkxx_text);
        mPublish.addTextChangedListener(new TextWatcher() {
            //记录输入的字数
            private CharSequence wordNum;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //实时记录输入的字数
                wordNum = s;
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                int number = num + s.length();
                //textView显示剩余字数
                mPublishTextNum.setText(""+ number+"/50");
                int selectionStart = mPublish.getSelectionStart();
                int selectionEnd = mPublish.getSelectionEnd();
                //判断大于最大值
                if(wordNum.length() > maxNum){
                    //删除多余输入的字
                    s.delete(selectionStart -1 , selectionEnd);
                    mPublish.setText(s);
                    mPublish.setSelection(selectionEnd-1);//设置光标在最后
                    //最多输入50字
                    Toast.makeText(FkxxActivity.this,"你输入的字数已经超过50字了！",Toast.LENGTH_SHORT).show();
                }
            }
        });
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
