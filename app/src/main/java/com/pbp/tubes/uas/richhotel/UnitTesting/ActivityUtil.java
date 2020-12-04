//package com.pbp.tubes.uas.richhotel.UnitTesting;
//
//import android.content.Context;
//import android.content.Intent;
//
//import com.pbp.tubes.uas.richhotel.Dao.UserDAO;
//import com.pbp.tubes.uas.richhotel.MainActivity.MainActivity;
//import com.pbp.tubes.uas.richhotel.MainActivity.MainActivity2;
//import com.pbp.tubes.uas.richhotel.Profil.ProfilUser;
//
//public class ActivityUtil {
//    private Context context;
//
//    public ActivityUtil(Context context) {
//        this.context = context;
//    }
//
//    public void startMainActivity() {
//        context.startActivity(new Intent(context, MainActivity.class));
//    }
//
//    public void startUserProfile(UserDAO user) {
//        Intent i = new Intent(context, StartUserProfileActivity.class);
//        i.putExtra("id", user.getId());
//        i.putExtra("name", user.getName());
//        i.putExtra("age", user.getAge());
//        i.putExtra("email", user.getEmail());
//        context.startActivity(i);
//    }
//}
