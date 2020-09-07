package com.example.diokhlpass.byt;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.diokhlpass.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Research_formular extends AppCompatActivity implements View.OnClickListener {

    private int year,month,day;
    private Spinner dest_spinner, dept_spinner ;
    private ImageButton btnPlus, btnMinus;
    private TextView number_place;
    private  EditText datePicker;
    final Calendar myCalendar = Calendar.getInstance();
    private ProgressBar spinner;
    private Button findRide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_formular);

       // Toolbar toolbar =(Toolbar) findViewById();

        //Spinner departure place
        dept_spinner = (Spinner) findViewById(R.id.spinner_dept_from);
        List<String> departure_point = new ArrayList<>();
        departure_point.add("Dakar");
        departure_point.add("Thies");
        departure_point.add("Diourbel");
        departure_point.add("Mbour");
        departure_point.add("Saint-Louis");
        departure_point.add("Kaolack");
        departure_point.add("Ziguenchor");

        // Spinner destination place
        dest_spinner = (Spinner) findViewById(R.id.spinner_arrival_to);
        final List<String> destination = new ArrayList<>();
        destination.add("Ziguenchor");
        destination.add("Kaolack");
        destination.add("Saint-Louis");
        destination.add("Mbour");
        destination.add("Diourbel");
        destination.add("Thies");
        destination.add("Dakar");

        //Creating adapter for spinner
        ArrayAdapter<String> dept_Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, departure_point);
        ArrayAdapter<String> dest_Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,destination);

        // attaching data adapter to spinner
        dept_spinner.setAdapter(dept_Adapter);
        dest_spinner.setAdapter(dest_Adapter);

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

     //find a ride button
        findRide =(Button) findViewById(R.id.findaRide_button);
        findRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });



    }

    private void validation() {

        String _departure = String.valueOf(dept_spinner.getSelectedItem());
        String _destination = String.valueOf(dest_spinner.getSelectedItem());

        if (_departure.equals("Dakar") && _destination.equals("Dakar")) {
            dialogWarning();
        } else if (_departure.equals("Thies") && _destination.equals("Thies") || _departure.equals("Thies") && _destination.equals("Mbour")
                || _departure.equals("Thies") && _destination.equals("Diourbel") || _departure.equals("Thies") && _destination.equals("Saint-Louis")) {
            dialogWarning();
        } else if (_departure.equals("Diourbel") && _destination.equals("Diourbel") || _departure.equals("Diourbel") && _destination.equals("Mbour")
                || _departure.equals("Diourbel") && _destination.equals("Thies") || _departure.equals("Diourbel") && _destination.equals("Saint-Louis")) {
            dialogWarning();
        } else if (_departure.equals("Saint-Louis") && _destination.equals("Saint-Louis") || _departure.equals("Saint-Louis") && _destination.equals("Mbour")
                || _departure.equals("Saint-Louis") && _destination.equals("Diourbel") || _departure.equals("Saint-Louis") && _destination.equals("Thies")) {
            dialogWarning();
        } else if (_departure.equals("Mbour") && _destination.equals("Mbour") || _departure.equals("Mbour") && _destination.equals("Thies")
                || _departure.equals("Mbour") && _destination.equals("Diourbel") || _departure.equals("Mbour") && _destination.equals("Saint-Louis")) {
            dialogWarning();
        }
         else if (_departure.equals("Kaolack") && _destination.equals("Kaolack") || _departure.equals("Kaolack") && _destination.equals("Thies")
                || _departure.equals("Kaolack") && _destination.equals("Diourbel") || _departure.equals("Kaolack") && _destination.equals("Saint-Louis")) {
            dialogWarning();

        } else if (_departure.equals("Ziguenchor") && _destination.equals("Ziguenchor") || _departure.equals("Ziguenchor") && _destination.equals("Thies")
                || _departure.equals("Ziguenchor") && _destination.equals("Diourbel") || _departure.equals("Ziguenchor") && _destination.equals("Saint-Louis")) {
            dialogWarning();
        }

        else {
            spinner.setVisibility(View.GONE);
            spinner.setVisibility(View.VISIBLE);
            Intent intent = new Intent(Research_formular.this, Result_buses.class);
            intent.putExtra("point", String.valueOf(dept_spinner.getSelectedItem()));
            intent.putExtra("destination", String.valueOf(dest_spinner.getSelectedItem()));
            intent.putExtra("tickets", String.valueOf(number_place));
            intent.putExtra("day", String.valueOf(day));
            intent.putExtra("month", String.valueOf(month +1));
            intent.putExtra("year", String.valueOf(year));
            startActivity(intent);
        }
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

    private void dialogWarning() {
        AlertDialog alertDialog = new AlertDialog.Builder(Research_formular.this).create();
        alertDialog.setTitle(" Warning");
        alertDialog.setMessage("Search for tickets from Dakar to other region , or from other region to Dakar.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
