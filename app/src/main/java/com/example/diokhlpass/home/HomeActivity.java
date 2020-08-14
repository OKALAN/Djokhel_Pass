package com.example.diokhlpass.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.example.diokhlpass.R;
import com.example.diokhlpass.byt.Research_formular;

public class HomeActivity extends AppCompatActivity {
  private Button BYT , profile, balance, book_checker, MTB, contact_us;
  private ImageButton calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BYT = findViewById(R.id.bat);
        profile = findViewById(R.id.profile);
        balance = findViewById(R.id.balance);
        book_checker = findViewById(R.id.book_checker);
        MTB = findViewById(R.id.Mtb);
        contact_us = findViewById(R.id.contact_us);
        calendar = findViewById(R.id.calendar_icon);

        BYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, Research_formular.class);
                startActivity(i);


            }
        });




    }
}
