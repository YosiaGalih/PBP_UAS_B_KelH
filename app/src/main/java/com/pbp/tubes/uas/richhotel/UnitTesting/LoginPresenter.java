//package com.pbp.tubes.uas.richhotel.UnitTesting;
//
//import com.pbp.tubes.uas.richhotel.Dao.UserDAO;
//
//public class LoginPresenter {
//
//    private LoginView view;
//    private LoginService service;
//    private LoginCallBack callBack;
//
//    public LoginPresenter(LoginView view, LoginService service) {
//        this.view = view;
//        this.service = service;
//    }
//
//    public void onLoginClicked() {
//        if(view.getEmail().isEmpty()) {
//            view.showEmailError("Email tidak boleh kosong");
//            return;
//        }else if(view.getPassword().isEmpty()) {
//            view.showPasswordError("Password tidak boleh kosong");
//            return;
//        }else {
//            service.loginUser(view, view.getEmail(), view.getPassword(), new LoginCallBack() {
//                @Override
//                public void onSuccess(boolean value, UserDAO user) {
//                    if(user.getEmail().equalsIgnoreCase("admin@gmail.com")) {
//                        view.startMainActivity();
//                    } else {
//                        view.StartUserProfileActivity();
//                    }
//                }
//
//                @Override
//                public void onError() {
//
//                }
//            });
//        }
//    }
//}
