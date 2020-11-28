package com.pbp.tubes.uas.richhotel.Register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.pbp.tubes.uas.richhotel.MainActivity;
import com.pbp.tubes.uas.richhotel.R;

import java.util.regex.Pattern;

public class UserRegister extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private EditText txtFullName, txtAge, txtEmail, txtPassword;
    private TextView banner;
    private Button registerUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register) ;

        mAuth = FirebaseAuth.getInstance();

        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        txtFullName = (EditText) findViewById(R.id.fullname);
        txtAge = (EditText) findViewById(R.id.age);
        txtEmail = (EditText) findViewById(R.id.email);
        txtPassword = (EditText) findViewById(R.id.password);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.registerUser:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        String fullname = txtFullName.getText().toString().trim();
        String age = txtAge.getText().toString().trim();

        if(fullname.isEmpty()){
            txtFullName.setError("Full name is required!");
            txtFullName.requestFocus();
            return;
        }
        if(email.isEmpty()){
            txtEmail.setError("Email is required!");
            txtEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            txtPassword.setError("Password is required!");
            txtPassword.requestFocus();
            return;
        }
        if(age.isEmpty()){
            txtAge.setError("Age is required!");
            txtAge.requestFocus();
            return;
        }
        if(!txtEmail.getText().toString().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")){
            txtEmail.setError("Please provide valid email!");
            txtEmail.requestFocus();
            return;
        }

        if(password.length() <6){
            txtPassword.setError("Password is require!");
            txtPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(fullname, age, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(UserRegister.this, "User has been registereed successfully!", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(UserRegister.this, LoginUser.class));
                                    }else{
                                        Toast.makeText(UserRegister.this, "User has been failed to registered!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(UserRegister.this, "Failed to registerd!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
