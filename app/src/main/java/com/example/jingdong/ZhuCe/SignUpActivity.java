package com.example.jingdong.ZhuCe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jingdong.Api.Api;
import com.example.jingdong.MainActivity;
import com.example.jingdong.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity implements SignUpJieKou.ISignUpView{

    @BindView(R.id.signup_user)
    EditText mSignupUser;
    @BindView(R.id.signup_pwd)
    EditText mSignupPwd;
    @BindView(R.id.signup_again_pwd)
    EditText mSignupAgainPwd;
    @BindView(R.id.signup_btn)
    Button mSignupBtn;
    private SignUpPresenter signUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        signUpPresenter = new SignUpPresenter(this);
    }

    @OnClick({R.id.signup_btn})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.signup_btn:
                String name = mSignupUser.getText().toString().trim();
                String pwd = mSignupPwd.getText().toString().trim();
                String againPwd = mSignupAgainPwd.getText().toString().trim();
                signUpPresenter.onSignUp(Api.BASEURL,name,pwd,againPwd);

                Intent in = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(in);
                finish();
                break;
        }
    }

    @Override
    public void ShowSign() {

        Toast.makeText(SignUpActivity.this,"注册成功", Toast.LENGTH_LONG);
    }

    @Override
    public void ShowError(String e) {

    }
}
