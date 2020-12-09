package com.example.diokhlpass.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.diokhlpass.Map.map_JP;
import com.example.diokhlpass.R;
import com.example.diokhlpass.bookChecker.bookChecker;
import com.example.diokhlpass.byt.Research_formular;
import com.example.diokhlpass.log.PremierPgeActivity;
import com.example.diokhlpass.profile.profile_page;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Button BYT , profile,map, book_checker, MTB, contact_us;
    private ImageButton calendar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private Menu menu;
    private TextView textView;
    private String email,emailP;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        textView=findViewById(R.id.textView);
        toolbar=findViewById(R.id.toolbar);

        emailP = getIntent().getStringExtra("emailP");
        Log.d(TAG, "onDataChange: "+ emailP);

        BYT = findViewById(R.id.bat);
        profile = findViewById(R.id.profile);
        map = findViewById(R.id.map);
        book_checker = findViewById(R.id.book_checker);
        MTB = findViewById(R.id.Mtb);
        contact_us = findViewById(R.id.contact_us);
        calendar = findViewById(R.id.calendar_icon);
        email = getIntent().getStringExtra("email");




        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        BYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Research_formular.class);
                i.putExtra("email",email);
                startActivity(i);


            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, map_JP.class);
                startActivity(i);
            }
        });

       profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Home.this, profile_page.class);
                startActivity(i);
            }
        });

       book_checker.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(Home.this, bookChecker.class);
               startActivity(i);
           }
       });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
            case R.id.nav_AboutUs:
                break;
                case R.id.nav_help:
              /*  Intent intent = new Intent(Home.this, help.class);
                startActivity(intent);*/
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(Home.this, PremierPgeActivity.class);
                startActivity(i);
                finish();


                break;
            case R.id.nav_share: Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show(); break;
        }
        drawerLayout.closeDrawer(GravityCompat.START); return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {super.onBackPressed();
        }
    }
}

