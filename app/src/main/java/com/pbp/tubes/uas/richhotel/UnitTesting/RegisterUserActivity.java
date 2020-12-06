package com.pbp.tubes.uas.richhotel.UnitTesting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.pbp.tubes.uas.richhotel.Dao.UserDAO;
import com.pbp.tubes.uas.richhotel.R;

import static android.widget.Toast.LENGTH_SHORT;

public class RegisterUserActivity extends AppCompatActivity implements RegisterView{

    private Button btnRegister;
    private EditText email, password, nama, age;
    private RegisterPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        btnRegister = findViewById(R.id.registerUser);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        nama = findViewById(R.id.fullname);
        age = findViewById(R.id.age);
        presenter = new RegisterPresenter(this, new RegisterService());
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onRegisterClicked();
            }
        });
    }

    @Override
    public String getEmail() {
        return email.getText().toString();
    }
    @Override
    public void showEmailError(String message) {
        email.setError(message);
    }
    @Override
    public String getPassword() {
        return password.getText().toString();
    }
    @Override
    public void showPasswordError(String message) {
        password.setError(message);
    }
    @Override
    public String getName() { return nama.getText().toString(); }
    @Override
    public void showNameError(String message) {
        nama.setError(message);
    }
    @Override
    public String getAge() {
        return age.getText().toString();
    }
    @Override
    public void showAgeError(String message) {
        age.setError(message);
    }
    @Override
    public void startMainActivity() {
        new ActivityUtil(this).startMainActivity();
    }
    @Override
    public void startUserProfileActivity(UserDAO user) {
        new ActivityUtil(this).startUserProfile(user);
    }
    @Override
    public void showRegisterError(String message) {
        Toast.makeText(this, message, LENGTH_SHORT).show();
    }
    @Override
    public void showErrorResponse(String message) {
        Toast.makeText(this, message, LENGTH_SHORT).show();
    }
}
