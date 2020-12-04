//package com.pbp.tubes.uas.richhotel.UnitTesting;
//
//import android.content.Intent;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.pbp.tubes.uas.richhotel.MainActivity.MainActivity;
//import com.pbp.tubes.uas.richhotel.MainActivity.MainActivity2;
//import com.pbp.tubes.uas.richhotel.Register.LoginUser;
//
//public class LoginService {
//    private FirebaseAuth mAuth;
//    public void loginUser(final LoginView view, String email, String password, final LoginCallBack callBack) {
//        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//
//                    if (task.isSuccessful()) {
//                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//                        if (user.isEmailVerified()) {
//                            Intent intent = new Intent(LoginService.this, MainActivity2.class);
//                            startActivity(intent);
//                        } else {
//                            user.sendEmailVerification();
//                            Toast.makeText(Log.this, "Please check your email!", Toast.LENGTH_LONG).show();
//                        }
//
//                    } else {
//                        Toast.makeText(LoginUser.this, "Failed to login!", Toast.LENGTH_LONG).show();
//                    }
//                }
//            });
//    }
//}
