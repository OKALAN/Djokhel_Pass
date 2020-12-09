package com.example.diokhlpass.bookChecker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.diokhlpass.R;
import com.example.diokhlpass.entities.bookticket;
import com.example.diokhlpass.log.PremierPgeActivity;

import java.util.List;

public class bookChecker extends AppCompatActivity {

    private String dsp , arv, sc, num, ttt,pc,date;
    private bookticket bk;
    private ListView lv;
    private  History_Adapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_checker);
        List<bookticket>  ticket = bookticket.findWithQuery(bookticket.class, "SELECT * FROM Bookticket  ");
        Log.d(String.valueOf(ticket), "ticket ");
        lv = (ListView)findViewById(R.id.HistoryListView);
        adapter = new History_Adapter(ticket,this);
        RelativeLayout EmptyRecyclerView = (RelativeLayout) findViewById(R.id.EmptyRecyclerView);
        lv.setAdapter(adapter);

        if (ticket.size()==0){


            EmptyRecyclerView.setVisibility(View.VISIBLE);

        }


            dsp = getIntent().getStringExtra("dept");
            arv = getIntent().getStringExtra("arr");
            sc = getIntent().getStringExtra("scode");
            num = getIntent().getStringExtra("NOT");
            date = getIntent().getStringExtra("date");
            ttt = getIntent().getStringExtra("ttt");
            pc =getIntent().getStringExtra("pc");


                bk = new bookticket();

                bk.departure_point = dsp;
                bk.destination = arv;
                bk.date = date;
                bk.number_of_seat = num;
                bk.number_of_tickets = sc;
                bk.time_travel = ttt;
                bk.price = pc;
                bk.save();

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        Intent i = new Intent(bookChecker.this, PremierPgeActivity.class);
                        i.putExtra("dept", dsp);
                        i.putExtra("arr",arv);
                        i.putExtra("NOT",num);
                        i.putExtra("date",date);
                        i.putExtra("ttt",ttt);
                        i.putExtra("scode",sc);
                        i.putExtra("pc", pc);
                        startActivity(i);


                    }
                });
            }



        }








