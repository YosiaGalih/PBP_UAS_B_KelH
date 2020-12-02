package com.pbp.tubes.uas.richhotel.Register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.google.gson.GsonBuilder;
import com.pbp.tubes.uas.richhotel.Api.ApiClient;
import com.pbp.tubes.uas.richhotel.Api.ApiInterface;
import com.pbp.tubes.uas.richhotel.Dao.UserDAO;
import com.pbp.tubes.uas.richhotel.MainActivity.MainActivity;
import com.pbp.tubes.uas.richhotel.R;
import com.pbp.tubes.uas.richhotel.Response.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRegister extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private EditText txtFullName, txtAge, txtEmail, txtPassword;
    private TextView banner;
    private Button registerUser;
    private Intent intent = null;
    private SharedPreferences sharedPreferences;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register) ;

        mAuth = FirebaseAuth.getInstance();

        sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");

        Log.i("register", "sharedPref Id: "+id);

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

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> add = apiService.createUser(txtFullName.getText().toString(), txtAge.getText().toString(),
                                                        txtEmail.getText().toString(), txtPassword.getText().toString());

        if(fullname.isEmpty()){
            txtFullName.setError("Full name is required!");
            txtFullName.requestFocus();
            return;
        }
        if(email.isEmpty() || email.equals("0")){
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

                            add.enqueue(new Callback<UserResponse>() {
                                @Override
                                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                    Log.i("register", "msg: " + new GsonBuilder().setPrettyPrinting().create().toJson(response));
                                    Toast.makeText(UserRegister.this,  response.body().getMessage(), Toast.LENGTH_LONG).show();
                                    onBackPressed();

                                    UserDAO user = response.body().getUser();
                                    Log.i("response", "msg" +new GsonBuilder().setPrettyPrinting().create().toJson(response));
                                    intent = new Intent(UserRegister.this, LoginUser.class);
                                    //intent.putExtra("id", user.getId());
                                    finish();

                                    startActivity(intent);
//                                    SharedPreferences.Editor editor = sharedPreferences.edit();
//
//                                    editor.putString("id", user.getId());
//                                    editor.apply();
                                }

                                @Override
                                public void onFailure(Call<UserResponse> call, Throwable t) {
                                    Toast.makeText(UserRegister.this, "Kesalahan Jaringan", Toast.LENGTH_LONG).show();

                                    Log.i("response", "msg" + t.getMessage());
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
