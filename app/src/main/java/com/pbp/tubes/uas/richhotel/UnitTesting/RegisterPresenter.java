package com.pbp.tubes.uas.richhotel.UnitTesting;


import com.pbp.tubes.uas.richhotel.Dao.UserDAO;

public class RegisterPresenter {

    private RegisterView view;
    private RegisterService service;
    private RegisterCallback callback;
    public RegisterPresenter(RegisterView view, RegisterService service) {
        this.view = view;
        this.service = service;
    }
    public void onRegisterClicked() {
        if (view.getEmail().isEmpty()) {
            view.showEmailError("Email tidak boleh kosong");
            return;
        }else if (!view.getEmail().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")) {
            view.showEmailError("Email tidak valid");
            return;
        }else if (view.getPassword().isEmpty()) {
            view.showPasswordError("Password tidak boleh kosong");
            return;
        } else if (view.getPassword().length()<6) {
            view.showPasswordError("Password tidak boleh kurang dari 6 karakter");
            return;
        }else if (view.getName().isEmpty()) {
            view.showNameError("Nama tidak boleh kosong");
            return;
        }else if (view.getAge().isEmpty()) {
            view.showAgeError("Age tidak boleh kosong");
            return;
        }else {
            service.register(view, view.getEmail(), view.getPassword(), view.getName(),view.getAge(), new
                    RegisterCallback() {
                        @Override
                        public void onSuccess(boolean value, UserDAO user) {
                            if (!user.getEmail().isEmpty() && !user.getPassword().isEmpty() &&!user.getName().isEmpty() &&
                                    !user.getAge().isEmpty()) {
                                view.startMainActivity();
                            } else {
                                view.startUserProfileActivity(user);
                            }
                        }

                        @Override
                        public void onError() {
                        }
                    });
            return;
        }
    }
}
