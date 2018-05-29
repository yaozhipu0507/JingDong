package com.example.jingdong.ZhuCe;

public class SignUpPresenter implements SignUpJieKou.ISignUpPresenter{
    SignUpJieKou.ISignUpView iSignUpView;
    private final SignUpJieKou.ISignUpModel iSignUpModel;
    public SignUpPresenter(SignUpJieKou.ISignUpView iSignUpView) {
        this.iSignUpView=iSignUpView;
        iSignUpModel =new SignUpModel();
    }

    @Override
    public void onSignUp(String url, String username, String password, String password_confirm) {
        iSignUpModel.RequestData(url, username, password,password_confirm, new SignUpJieKou.OnRequestListener() {

            @Override
            public void OnSuccess() {
                iSignUpView.ShowSign();
            }

            @Override
            public void OnError(String e) {
                iSignUpView.ShowError(e);
            }
        });
    }
}
