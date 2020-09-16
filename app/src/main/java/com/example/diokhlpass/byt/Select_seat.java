package com.example.diokhlpass.byt;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.diokhlpass.R;

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
        txtSeatSelected.setText("Book "+count+" seats");

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
