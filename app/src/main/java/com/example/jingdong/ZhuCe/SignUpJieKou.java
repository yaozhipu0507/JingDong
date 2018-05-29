package com.example.jingdong.ZhuCe;

public interface SignUpJieKou {

    interface ISignUpView {
        void ShowSign();
        void ShowError(String e);
    }

    interface ISignUpModel {
        void RequestData(String url, String username, String password, String password_confirm, OnRequestListener onRequestListener);
    }

    interface OnRequestListener{
        void OnSuccess();
        void OnError(String e);
    }

    interface ISignUpPresenter {
        void onSignUp(String url, String username, String password, String password_confirm);
    }
}
