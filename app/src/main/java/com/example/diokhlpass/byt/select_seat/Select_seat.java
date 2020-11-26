package com.example.diokhlpass.byt.select_seat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.diokhlpass.R;
import com.example.diokhlpass.byt.infoSeats;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Select_seat extends AppCompatActivity implements OnSeatSelected {


    private static final int COLUMNS = 5;
    private TextView txtSeatSelected;
    private ArrayList<Integer> listnumSeat = new ArrayList<>();
    private  BusAdapter adapter;
    private // Access a Cloud Firestore instance from your Activity
            FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private Map<String, Integer>  numBooked = new HashMap<>();


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

        adapter= new BusAdapter(this, items);
        recyclerView.setAdapter(adapter);




    }



    @Override
    public void onSeatSelected(int count) {
        txtSeatSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dsp , arv, num, day,month,year, ttt;
                listnumSeat =  adapter.numSeat();
                Intent i = new Intent(Select_seat.this, infoSeats.class);
                //String c = String.valueOf(listnumSeat.size()) ;



                dsp = getIntent().getStringExtra("dept");
                arv = getIntent().getStringExtra("arr");
                num = getIntent().getStringExtra("NOT");
                day = getIntent().getStringExtra("day");
                month = getIntent().getStringExtra("month");
                year = getIntent().getStringExtra("year");
                ttt = getIntent().getStringExtra("ttt");

                String a =" " ;
                String nB;
                if ( Integer.valueOf(num)==listnumSeat.size() ){
                    for (int x=0;x<listnumSeat.size();x++){
                        a = a + String.valueOf(listnumSeat.get(x)) + " ";
                        nB = "numBooked"+x;
                        numBooked.put(nB,listnumSeat.get(x));
                        db.collection("Bus0").document(day+"-"+month+"-"+year).collection(dsp+"-"+arv).document(ttt).set(numBooked, SetOptions.merge());


                    }

                    i.putExtra("dept", dsp);
                    i.putExtra("arr",arv);
                    i.putExtra("day",day);
                    i.putExtra("month", month);
                    i.putExtra("year",year);
                    i.putExtra("NOT",num);
                    i.putExtra("day",day);
                    i.putExtra("ttt",ttt);
                    i.putExtra("scode",a);

                    startActivity(i);

                }
                else {
                    Toast.makeText(Select_seat.this, "Nombre de places incorrect!",Toast.LENGTH_SHORT).show();

                }



            }
        });

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
