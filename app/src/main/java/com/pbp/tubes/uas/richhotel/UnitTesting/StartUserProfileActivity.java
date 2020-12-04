//package com.pbp.tubes.uas.richhotel.UnitTesting;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.material.button.MaterialButton;
//import com.pbp.tubes.uas.richhotel.MainActivity.MainActivity2;
//import com.pbp.tubes.uas.richhotel.R;
//
//public class StartUserProfileActivity extends AppCompatActivity {
//
//    private String sIdUser, sNama, sAge, sEmail;
//    private TextView twNama, twAge, twEmail;
//    private MaterialButton btnCancel;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.profil_user);
//
//        twNama = findViewById(R.id.twNama);
//        twAge = findViewById(R.id.twAge);
//        twEmail = findViewById(R.id.twEmail);
//        btnCancel = findViewById(R.id.btnCancel);
//
//        Intent i = getIntent();
//        sIdUser = i.getStringExtra("id");
//        sNama = i.getStringExtra("name");
//        sAge = i.getStringExtra("age");
//        sEmail = i.getStringExtra("email");
//
//        twNama.setText(sNama);
//        twAge.setText(sAge);
//        twEmail.setText(sEmail);
//
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(StartUserProfileActivity.this, MainActivity2.class));
//                finish();
//            }
//        });
//    }
//}
