package com.example.diokhlpass.bookChecker;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.diokhlpass.R;

public class PreviewHistoryActivity extends AppCompatActivity {
    private String dsp , arv, sc, date, ttt,pc,num;
    private TextView desp,arr,scode,NOT,DOT,TT,price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_history);


        desp = findViewById(R.id.pt_preview);
        arr = findViewById(R.id.dst_preview);
        scode = findViewById(R.id.selted_seat_code);
        NOT = findViewById(R.id.seat_preview);
        DOT= findViewById(R.id.dat_preview);
        TT = findViewById(R.id.tim_preview);
        price = findViewById(R.id.pric_preview);

        dsp = getIntent().getStringExtra("dept");
        arv = getIntent().getStringExtra("arr");
        sc = getIntent().getStringExtra("scode");
        date = getIntent().getStringExtra("date");
        ttt = getIntent().getStringExtra("ttt");
        pc = getIntent().getStringExtra("pc");
        num =  getIntent().getStringExtra("num");



        desp.setText(dsp);
        arr.setText(arv);
        scode.setText(sc);
        NOT.setText(num);
        DOT.setText(date);
        TT.setText(ttt);
        price.setText(new StringBuilder().append(pc).append(" F CFA") );
    }
}
