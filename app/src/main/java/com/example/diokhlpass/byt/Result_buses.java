package com.example.diokhlpass.byt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.diokhlpass.R;

import java.util.ArrayList;
import java.util.List;

public class Result_buses extends AppCompatActivity {

    TextView dept_preview, dest_preview, number_ticket, travel_date;
    Spinner Select_busSpinner;
    String departure,arrival,Tickets,day,month,year;
    Button choose_a_seat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_buses);

    Select_busSpinner = findViewById(R.id.select_bus_spinner);
    choose_a_seat = findViewById(R.id.choose_seats);
    dept_preview = findViewById(R.id.point_preview);
    dest_preview = findViewById(R.id.dest_preview);
    number_ticket = findViewById(R.id.Numseats_preview);
    travel_date = findViewById(R.id.date_preview);

    departure = getIntent().getStringExtra("point");
    arrival = getIntent().getStringExtra("destination");
    Tickets = getIntent().getStringExtra("tickets");
    day = getIntent().getStringExtra("day");
    month = getIntent().getStringExtra("month");
    year = getIntent().getStringExtra("year");

    dept_preview.setText(departure);
    dest_preview.setText(arrival);
    number_ticket.setText(Tickets);
    travel_date.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));

    List<String> Select_bus_list = new ArrayList<>();
    Select_bus_list.add("7:00 am Inter-urban travel company");
    Select_bus_list.add("12:00 am Inter-urban travel company");
    Select_bus_list.add("6:00 pm Inter-urban travel company");
    Select_bus_list.add("9:00 pm Inter-urban travel company");

    ArrayAdapter<String> busAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,Select_bus_list);
    Select_busSpinner.setAdapter(busAdapter);

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
