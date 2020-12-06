package com.pbp.tubes.uas.richhotel.UnitTest;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.pbp.tubes.uas.richhotel.R;
import com.pbp.tubes.uas.richhotel.Register.LoginUser;

public class StartActivity extends AppCompatActivity {
    private String CHANNEL_ID = "Channel 1";
    ImageView mainimg;
    TextView welcomeText, descText;
    Button getStarted;
    Animation forimg, frombottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mainimg = (ImageView) findViewById(R.id.mainimg);
        welcomeText = (TextView) findViewById(R.id.welcomeText);
        descText = (TextView) findViewById(R.id.descText);
        getStarted = (Button) findViewById(R.id.getStarted);

        forimg = AnimationUtils.loadAnimation(this, R.anim.forimg);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);

        mainimg.startAnimation(forimg);
        welcomeText.startAnimation(frombottom);
        descText.startAnimation(frombottom);
        descText.startAnimation(frombottom);

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginUser.class );
                createNotificationChannel();
                addNotification();
                startActivity(intent);
                finish();
            }
        });
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel 1";
            String description = "This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void addNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Let's Get Started :)")
                .setContentText("Please enjoy using this application :)")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notificationIntent = new Intent(this, LoginUser.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
