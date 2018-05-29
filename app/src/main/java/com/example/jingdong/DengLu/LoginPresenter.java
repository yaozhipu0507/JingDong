package com.example.jingdong.DengLu;


public class LoginPresenter implements LoginJieKou.ILoginPresenter{
    LoginJieKou.ILoginView iLoginView;
    private final LoginJieKou.ILoginModel iLoginModel;
    public LoginPresenter(LoginJieKou.ILoginView iLoginView) {
        this.iLoginView=iLoginView;
        iLoginModel =new LoginMoudle();
    }

    @Override
    public void onSignUp(String url, String mobile, String password) {
        iLoginModel.RequestData(url, mobile, password, new LoginJieKou.OnRequestListener() {
            @Override
            public void OnSuccess(LoginBean.DataBean db) {
                iLoginView.showLogin(db);
            }

            @Override
            public void OnError(String e) {
                iLoginView.showerroe(e);
            }
        });
    }
}
