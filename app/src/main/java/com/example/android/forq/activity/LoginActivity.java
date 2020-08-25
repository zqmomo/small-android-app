package com.example.android.forq.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;


import com.example.android.forq.R;
import com.example.android.forq.base.BaseActivity;
import com.example.android.forq.base.BasePresenter;
import com.example.android.forq.ui.MainActivity;
import com.example.android.forq.util.Constant;
import com.example.android.forq.util.Md5;
import com.example.android.forq.util.SpUtil;

import org.json.JSONObject;

import androidx.annotation.RequiresApi;

public class LoginActivity extends BaseActivity<BasePresenter> {
    private String userName,psw,spPsw,isLogin;//获取用户名，密码，加密密码，登录状态
    private EditText et_user,et_psw;//编辑框
    public TextView text;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        isLogin = (String) SpUtil.get(this,"isLogin","");
//        assert isLogin != null;
//        if(isLogin.endsWith("1")){
//            LoginActivity.this.finish();
//            Intent intent = new Intent();
//            intent.setClass(this, MainActivity.class);
//            startActivity(intent);
//        }
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init(){
        Button btn_login = findViewById(R.id.btn_login);
        et_user = findViewById(R.id.et_user);
        et_psw = findViewById(R.id.et_psw);
        text = findViewById(R.id.et_txt);

        testConnectivityManager();

        btn_login.setOnClickListener(view -> {
            userName = et_user.getText().toString().trim();
            psw = et_psw.getText().toString().trim();
            spPsw = Md5.md5(psw);
            if (TextUtils.isEmpty(userName)) {
                Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(psw)) {
                Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
            } else {
                new Thread(() -> yzdl(userName, spPsw)).start();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void testConnectivityManager(){
        ConnectivityManager connManager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        assert connManager != null;
        Network network = connManager.getActiveNetwork();
        if(null == network){
            Toast.makeText(LoginActivity.this, "当前的网络连接不可用", Toast.LENGTH_SHORT).show();
        }
        NetworkCapabilities capabilities = connManager.getNetworkCapabilities(network);
        if (null == capabilities) {
            Toast.makeText(LoginActivity.this, "当前的网络连接不可用", Toast.LENGTH_SHORT).show();
        }
        assert capabilities != null;
        if(capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)){
            Toast.makeText(LoginActivity.this, "当前的网络连接可用", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(LoginActivity.this, "当前的网络连接不可用", Toast.LENGTH_SHORT).show();
        }
    }
    //连接服务器判断用户名和密码
    public void yzdl(String username,String sppsw){
        String path = Constant.URL_Login+"?username="+username+"&password="+sppsw;
        HttpURLConnection connection;
        InputStream in;
        try{
            try{
                URL url = new URL(path);
                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(8000);//建立连接超时
                connection.setReadTimeout(8000);//传递数据超时

                in = connection.getInputStream();
                JSONObject json = new JSONObject(Objects.requireNonNull(parseInfo(in)));
                boolean success = json.getBoolean("success");
                Log.i("success", String.valueOf(success));
                if(success){
                    SpUtil.put(this,"username",username);
                    SpUtil.put(this,"psw",sppsw);
                    SpUtil.put(this,"isLogin","1");
                    LoginActivity.this.finish();
                    Intent intent = new Intent();
                    intent.setClass(this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Looper.prepare();
                    Toast.makeText(LoginActivity.this, "用户名/密码错误，请重新输入！", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //得到字节输入流，将字节输入流转化为String类型
    public static String parseInfo(InputStream inputStream){
        BufferedReader reader = null;
        String line;
        StringBuilder response = new StringBuilder();

        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while((line = reader.readLine()) != null){
                response.append(line);
            }
            return response.toString();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(reader != null){
                try{
                    reader.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
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
