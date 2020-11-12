package com.example.diokhlpass.byt.select_seat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.diokhlpass.R;
import com.example.diokhlpass.byt.infoSeats;

import java.util.ArrayList;
import java.util.List;

public class Select_seat extends AppCompatActivity implements OnSeatSelected {


    private static final int COLUMNS = 5;
    private TextView txtSeatSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_seat);

        txtSeatSelected = (TextView)findViewById(R.id.txt_seat_selected);

        List<AbstractItem> items = new ArrayList<>();
        for (int i=0; i<30; i++) {

            if (i%COLUMNS==0 || i%COLUMNS==4) {
                items.add(new EdgeItem(String.valueOf(i)));


            } else if (i%COLUMNS==1 || i%COLUMNS==3) {
                items.add(new CenterItem(String.valueOf(i)));

            } else {
                items.add(new EmptyItem(String.valueOf(i)));
            }
        }

        GridLayoutManager manager = new GridLayoutManager(this, COLUMNS);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(manager);

       BusAdapter adapter = new BusAdapter(this, items);
        recyclerView.setAdapter(adapter);


    }



    @Override
    public void onSeatSelected(int count) {
        txtSeatSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dsp , arv, num, day,month,year, ttt;



                dsp = getIntent().getStringExtra("dept");
                arv = getIntent().getStringExtra("arr");
                num = getIntent().getStringExtra("NOT");
                day = getIntent().getStringExtra("day");
                month = getIntent().getStringExtra("month");
                year = getIntent().getStringExtra("year");
                ttt = getIntent().getStringExtra("ttt");


                Intent i = new Intent(Select_seat.this, infoSeats.class);

                i.putExtra("dept", dsp);
                i.putExtra("arr",arv);
                i.putExtra("day",day);
                i.putExtra("month", month);
                i.putExtra("year",year);
                i.putExtra("NOT",num);
                i.putExtra("day",day);
                i.putExtra("ttt",ttt);


                startActivity(i);


            }
        });

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
