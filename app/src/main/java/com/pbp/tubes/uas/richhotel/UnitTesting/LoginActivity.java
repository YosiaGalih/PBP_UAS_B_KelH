//package com.pbp.tubes.uas.richhotel.UnitTesting;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.pbp.tubes.uas.richhotel.Dao.UserDAO;
//import com.pbp.tubes.uas.richhotel.MainActivity.MainActivity;
//import com.pbp.tubes.uas.richhotel.MainActivity.MainActivity2;
//import com.pbp.tubes.uas.richhotel.R;
//import com.pbp.tubes.uas.richhotel.Register.LoginUser;
//import com.pbp.tubes.uas.richhotel.Register.UserRegister;
//
//public class LoginActivity extends AppCompatActivity implements LoginView {
//    private EditText txtPassword, txtEmail;
//    private Button login;
//    private LoginPresenter presenter;
//    private FirebaseAuth mAuth;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_login);
//
//        login = findViewById(R.id.loginUser);
//        txtEmail = findViewById(R.id.email);
//        txtPassword = findViewById(R.id.password);
//        mAuth = FirebaseAuth.getInstance();
//
//        presenter = new LoginPresenter(this, new LoginService());
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                presenter.onLoginClicked();
//            }
//        });
//
//
//    }
//
//    @Override
//    public String getEmail() { return txtEmail.getText().toString();}
//
//    @Override
//    public void showEmailError(String message) { txtEmail.setError(message);}
//
//    @Override
//    public String getPassword() { return txtPassword.getText().toString();}
//
//    @Override
//    public void showPasswordError(String message) {txtPassword.setError(message);}
//
//    @Override
//    public void startMainActivity() {
//        new ActivityUtil(this).startMainActivity();
//    }
//
//    @Override
//    public void StartUserProfileActivity(UserDAO user) {
//        new ActivityUtil(this).startUserProfile(user);
//    }
//
//    @Override
//    public void showLoginError(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void showErrorResponse(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }
//
//}
