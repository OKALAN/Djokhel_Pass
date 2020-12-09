package com.example.diokhlpass.byt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.diokhlpass.R;
import com.example.diokhlpass.home.Home;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class infoSeats extends AppCompatActivity {
    private TextView desp,arr,scode,NOT,DOT,TT,price, pay_button;
    private String dsp , arv, sc, num, day,month,year, ttt,pc;
    private int priceTotal;
    private  float PriceDollar;
    private // Access a Cloud Firestore instance from your Activity
            FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String TAG;

    private String infoQR  ,  name = "lala", email, phone = "778001212";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_seats);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.d(TAG, String.valueOf(user));
        email= getIntent().getStringExtra("email");

      /*  if (user != null) {
            // Name, email address, and phone
            name = user.getDisplayName();
            email = user.getEmail();
            phone = user.getPhoneNumber();


            // Check if user's email is verified
            //boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }*/

        desp = findViewById(R.id.pt_preview);
        arr = findViewById(R.id.dst_preview);
        scode = findViewById(R.id.selted_seat_code);
        NOT = findViewById(R.id.seat_preview);
        DOT= findViewById(R.id.dat_preview);
        TT = findViewById(R.id.tim_preview);
        price = findViewById(R.id.pric_preview);
        pay_button = findViewById(R.id.payBill_button);


        dsp = getIntent().getStringExtra("dept");
        arv = getIntent().getStringExtra("arr");
        sc = getIntent().getStringExtra("scode");
        num = getIntent().getStringExtra("NOT");
        day = getIntent().getStringExtra("day");
        month = getIntent().getStringExtra("month");
        year = getIntent().getStringExtra("year");
        ttt = getIntent().getStringExtra("ttt");

        final String date = day+"-"+month+"-"+year;

        Map<String, Object> bus_seat_booked = new HashMap<>();
        bus_seat_booked.put("Dept_town", dsp);
        bus_seat_booked.put("Dest_town",arv);
        bus_seat_booked.put("date",date);
        bus_seat_booked.put("leaving_time",ttt);
        bus_seat_booked.put("place_booked",Integer.valueOf(num));
        bus_seat_booked.put("Custom",name );
        bus_seat_booked.put("email", email);
        bus_seat_booked.put("phone",phone);

        priceTotal = (Integer.valueOf(num)) * 5000;
        PriceDollar = priceTotal/551;
        pc =String.valueOf(PriceDollar);
        bus_seat_booked.put("PaySum", priceTotal);
        bus_seat_booked.put("scode",sc);

        db.collection("Reservations").document(date).collection(email).add(bus_seat_booked);

           pay_button.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent i =new Intent(infoSeats.this,method_paiement.class);
                   infoQR ="JOXEEL PASS :"+"\n"+" Dep: "+ dsp + "\n"+ "Arv: "+arv + "\n"+"Nombre de ticket(s): "+num+"\n"+ "No. siège(s): "+ sc + "\n"+ "Date: "+date+ "\n"+ "Heure de dep: "+ ttt;
                   Log.d(TAG, "chaton:"+infoQR);
                   i.putExtra("priceTotal",pc);
                   i.putExtra("infoQR",infoQR);
                   i.putExtra("dept", dsp);
                   i.putExtra("arr",arv);
                   i.putExtra("day",day);
                   i.putExtra("month", month);
                   i.putExtra("year",year);
                   i.putExtra("NOT",num);
                   i.putExtra("day",day);
                   i.putExtra("ttt",ttt);
                   i.putExtra("scode",sc);
                   i.putExtra("price",String.valueOf(priceTotal));
                   i.putExtra("email",email);
                   startActivity(i);
               }
           });

        desp.setText(dsp);
        arr.setText(arv);
        scode.setText(sc);
        NOT.setText(num);
        DOT.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
        TT.setText(ttt);
        price.setText(new StringBuilder().append(num).append("x").append("5000 F CFA").append("= ").append(priceTotal+" F CFA"));




    }




    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
        finish();
    }

}






