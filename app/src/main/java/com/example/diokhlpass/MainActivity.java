package com.example.diokhlpass;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static int Duree_AffichageOnScreen = 4000;


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
