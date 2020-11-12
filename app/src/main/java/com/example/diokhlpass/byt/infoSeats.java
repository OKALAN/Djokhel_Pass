package com.example.diokhlpass.byt;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.diokhlpass.R;

public class infoSeats extends AppCompatActivity {
    private TextView desp,arr,scode,NOT,DOT,TT,price, pay_button;
    private String dsp , arv, sc, num, day,month,year, ttt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_seats);

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

        desp.setText(dsp);
        arr.setText(arv);
        scode.setText(sc);
        NOT.setText(num);
        DOT.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
        TT.setText(ttt);
        price.setText(new StringBuilder().append(num).append("x").append("5000 F CFA"));






    }
}
