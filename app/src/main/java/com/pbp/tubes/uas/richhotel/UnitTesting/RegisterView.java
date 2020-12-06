package com.pbp.tubes.uas.richhotel.UnitTesting;


import com.pbp.tubes.uas.richhotel.Dao.UserDAO;

public interface RegisterView {
    String getEmail();
    void showEmailError(String message);
    String getPassword();
    void showPasswordError(String message);
    String getName();
    void showNameError(String message);
    String getAge();
    void showAgeError(String message);
    void startMainActivity();
    void startUserProfileActivity(UserDAO user);
    void showRegisterError(String message);
    void showErrorResponse(String message);
}
