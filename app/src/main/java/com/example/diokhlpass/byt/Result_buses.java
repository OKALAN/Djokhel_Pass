package com.example.diokhlpass.byt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.diokhlpass.R;
import com.example.diokhlpass.byt.select_seat.Select_seat;

public class Result_buses extends AppCompatActivity {

    TextView dept_preview, dest_preview, number_ticket, travel_date, travel_time;
    String departure,arrival,Tickets,day,month,year,time;
    Button choose_a_seat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_buses);


    choose_a_seat = findViewById(R.id.choose_seats);
    dept_preview = findViewById(R.id.point_preview);
    dest_preview = findViewById(R.id.dest_preview);
    number_ticket = findViewById(R.id.Numseats_preview);
    travel_date = findViewById(R.id.date_preview);
    travel_time = findViewById(R.id.time_preview);


    departure = getIntent().getStringExtra("point");
    arrival = getIntent().getStringExtra("destination");
    Tickets = getIntent().getStringExtra("tickets");
    day = getIntent().getStringExtra("day");
    month = getIntent().getStringExtra("month");
    year = getIntent().getStringExtra("year");
    time = getIntent().getStringExtra("time");

    dept_preview.setText(departure);
    dest_preview.setText(arrival);
    number_ticket.setText(Tickets);
    travel_date.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    travel_time.setText(time);



  choose_a_seat.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
           Intent i = new Intent(Result_buses.this, Select_seat.class);
           startActivity(i);
           finish();
      }
  });

    }
}
