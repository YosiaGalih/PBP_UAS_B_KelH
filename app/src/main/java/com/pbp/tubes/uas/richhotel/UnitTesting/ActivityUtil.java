package com.pbp.tubes.uas.richhotel.UnitTesting;

import android.content.Context;
import android.content.Intent;

import com.pbp.tubes.uas.richhotel.Dao.UserDAO;
import com.pbp.tubes.uas.richhotel.Profil.ProfilUser;
import com.pbp.tubes.uas.richhotel.UnitTesting.RegisterUserActivity;

public class ActivityUtil {

    private Context context;
    public ActivityUtil(Context context) {
        this.context = context;
    }
    public void startMainActivity() {
        context.startActivity(new Intent(context, RegisterUserActivity.class));
    }
    public void startUserProfile(UserDAO user) {
        Intent i = new Intent(context, ProfilUser.class);
        i.putExtra("email",user.getEmail());
        i.putExtra("password",user.getPassword());
        i.putExtra("name",user.getName());
        i.putExtra("age",user.getAge());
        context.startActivity(i);
    }

}
