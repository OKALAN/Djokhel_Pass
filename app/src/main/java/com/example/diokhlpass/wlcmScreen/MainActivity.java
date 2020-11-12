package com.example.diokhlpass.wlcmScreen;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.diokhlpass.R;

public class MainActivity extends AppCompatActivity {
    private static int Duree_AffichageOnScreen = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent PremierPgeIntent = new Intent(MainActivity.this, Onboarding.class);
                startActivity(PremierPgeIntent);
                finish();

            }
        },Duree_AffichageOnScreen);


              /*  Intent OnboardingIntent = new Intent(MainActivity.this, Onboarding.class);
                startActivity(OnboardingIntent);*/


















    }
}
