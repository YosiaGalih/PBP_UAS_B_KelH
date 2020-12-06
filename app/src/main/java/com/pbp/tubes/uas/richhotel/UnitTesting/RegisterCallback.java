package com.pbp.tubes.uas.richhotel.UnitTesting;

import com.pbp.tubes.uas.richhotel.Dao.UserDAO;

public interface RegisterCallback {
    void onSuccess(boolean value, UserDAO user);
    void onError();
}
