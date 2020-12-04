package com.pbp.tubes.uas.richhotel.MainActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.pbp.tubes.uas.richhotel.About;
import com.pbp.tubes.uas.richhotel.Create.CreateKamar;
import com.pbp.tubes.uas.richhotel.Daftar.DaftarKamarAdmin;
//import com.pbp.tubes.uas.richhotel.Daftar.DaftarReservasiAdmin;
import com.pbp.tubes.uas.richhotel.Profil.ProfilAdmin;
import com.pbp.tubes.uas.richhotel.R;
import com.pbp.tubes.uas.richhotel.SignOut.SignOut;
import com.pbp.tubes.uas.richhotel.SignOut.SignOutUser;

//ADMIN///
public class MainActivity extends AppCompatActivity {
    public RelativeLayout  btnAbout,btnSignOut, btnProfil, btnKamar, btnDaftarKamar, btnDaftarReservasi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAbout = (RelativeLayout) findViewById(R.id.about);
        btnSignOut = (RelativeLayout) findViewById(R.id.sign_out);
        btnProfil = (RelativeLayout) findViewById(R.id.profil);
        btnKamar = (RelativeLayout) findViewById(R.id.kamar);
        btnDaftarKamar = findViewById(R.id.daftarKamar);
        btnDaftarReservasi = findViewById(R.id.daftar_reservasi_admin);

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), About.class);
                startActivity(intent);
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignOut.class);
                startActivity(intent);
            }
        });

        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), ProfilAdmin.class);
                startActivity(intent);
            }
        });

        btnKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateKamar.class);
                startActivity(intent);
            }
        });

        btnDaftarKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DaftarKamarAdmin.class);
                startActivity(intent);
            }
        });

//        btnDaftarReservasi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), DaftarReservasiAdmin.class);
//                startActivity(intent);
//            }
//        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}