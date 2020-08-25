package com.example.diokhlpass.byt;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.diokhlpass.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Research_formular extends AppCompatActivity implements View.OnClickListener {

    private int year,month,day;
    private Spinner dest_spinner, dept_spinner ;
    private ImageButton btnPlus, btnMinus;
    private TextView number_place;
    EditText datePicker;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_formular);

        //traveling date
        datePicker = findViewById(R.id.travel_dateEditext);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        datePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Research_formular.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

     //Number of places that user wants
        btnPlus = findViewById(R.id.btn_plus);
        btnMinus = findViewById(R.id.btn_minus);
        number_place = findViewById(R.id.number_place);

        btnPlus.setOnClickListener((View.OnClickListener) this);
        btnMinus.setOnClickListener((View.OnClickListener) this);
        btnMinus.setEnabled(false);






    }


    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ROOT);
        datePicker.setText(sdf.format(myCalendar.getTime()));
    }


    @Override
    public void onClick(View v) {

        number_place = findViewById(R.id.number_place);

       int i = Integer.valueOf(number_place.getText().toString());
        switch (v.getId())
        {
            case R.id.btn_plus :
                if(i<49)
                   i+=1;
                else
                    Toast.makeText(this,"No more place available!",Toast.LENGTH_SHORT).show();
                if(i>1){
                    btnMinus.setEnabled(true);
                }
                number_place.setText(String.valueOf(i));
                break;
            case R.id.btn_minus :
                i-=1;
                number_place.setText(String.valueOf(i));

                if(i == 1){
                    btnMinus.setEnabled(false);
                }
                break;
        }
    }
}
