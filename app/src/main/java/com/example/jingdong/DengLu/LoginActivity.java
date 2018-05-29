package com.example.jingdong.DengLu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.jingdong.Api.Api;
import com.example.jingdong.R;
import com.example.jingdong.ZhuCe.SignUpActivity;
import com.example.jingdong.fragment.Fragment_Geren;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginJieKou.ILoginView{

    @BindView(R.id.login_user)
    EditText mLoginUser;
    @BindView(R.id.login_pwd)
    EditText mLoginPwd;
    @BindView(R.id.wangjimima)
    TextView mWangjimima;
    @BindView(R.id.login_zhuce)
    TextView mLoginZhuce;
    @BindView(R.id.login_btn)
    Button mLoginBtn;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenter(this);
    }

    @butterknife.OnClick({ R.id.login_zhuce, R.id.login_btn})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.login_zhuce:
                Intent in = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(in);
                break;
            case R.id.login_btn:

                String mobile = mLoginUser.getText().toString().trim();
                String pwd = mLoginPwd.getText().toString().trim();
                loginPresenter.onSignUp(Api.BASEURL, mobile, pwd);

                Intent intent = new Intent(LoginActivity.this, Fragment_Geren.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void showLogin(LoginBean.DataBean db) {
        SharedPreferences sp = getSharedPreferences("USER", MODE_PRIVATE);
        sp.edit().putInt("uid", db.getUid())
                .putString("name", db.getUsername())
                .putString("pwd", db.getPassword())
                .commit();

        Log.i("denglu","登录成功");

        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
        //Toasts.showLong(this, "登录成功");
    }

    @Override
    public void showerroe(String e) {

    }
}
