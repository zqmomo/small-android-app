package com.example.android.forq.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.forq.R;
import com.example.android.forq.base.BaseFragment;
import com.example.android.forq.base.BasePresenter;
import com.example.android.forq.comm.ApproveDialog;
import com.example.android.forq.util.SpUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;


public class JykxFragment extends BaseFragment<BasePresenter> {
    
    private ApproveDialog dialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(requireActivity()).inflate(R.layout.fragment_jykx, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView txt_scgz = view.findViewById(R.id.jykx_scgz);
        txt_scgz.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        
        String value = (String) SpUtil.get(Objects.requireNonNull(getContext()),"is_buy","");
        assert value != null;
        if (!value.endsWith("1")) {
            IsshowDialog(getActivity());
        }
        txt_scgz.setOnClickListener(view1 -> IsshowDialog(getActivity()));

    }

    @SuppressLint("ApplySharedPref")
    private void IsshowDialog(Context context){
        dialog = new ApproveDialog(context, "交易规则", "电力市场交易规则", "已读", "已读并不再提示", new ApproveDialog.ApproveDialogHelper() {
            @Override
            public void go() {
                dialog.dismiss();
                SpUtil.put(Objects.requireNonNull(getContext()),"is_buy","0");
            }

            @Override
            public void cancel() {
                dialog.dismiss();
                //将数据保存到sharedPreferences
//                SharedPreferences pre = getActivity().getSharedPreferences("check_buy", MODE_PRIVATE);
//                SharedPreferences.Editor editor = pre.edit();
//                editor.putString("is_buy","1");
                //提交选择的check，并保存在pre中
               // editor.commit();
                SpUtil.put(Objects.requireNonNull(getContext()),"is_buy","1");

            }
        });
        dialog.show();
    }


    @Override
    public BasePresenter getPresenter() {
        return new BasePresenter() {
            @Override
            public void unsubscribe() {
            }
        };
    }

    /*
    *读取文本内容
    * 
     */
    public static String ReadTxtFile(String strFilePath)
    {
        StringBuilder content = new StringBuilder(); //文件内容字符串
        //打开文件
        File file = new File(strFilePath);
        //如果path是传递过来的参数，可以做一个非目录的判断
        if (file.isDirectory())
        {
            Log.d("TestFile", "The File doesn't not exist.");
        }
        else
        {
            try {
                InputStream instream = new FileInputStream(file);
                if (instream != null)
                {
                    InputStreamReader inputreader = new InputStreamReader(instream);
                    BufferedReader buffreader = new BufferedReader(inputreader);
                    String line;
                    //分行读取
                    while (( line = buffreader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    instream.close();
                }
            }
            catch (java.io.FileNotFoundException e)
            {
                Log.d("TestFile", "The File doesn't not exist.");
            }
            catch (IOException e)
            {
                Log.d("TestFile", Objects.requireNonNull(e.getMessage()));
            }
        }
        return content.toString();
    }
    
    @Override
    public void onItemClick(int position){}
}
