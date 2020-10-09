package com.example.diokhlpass.byt;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.diokhlpass.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.*;

public class Research_formular extends AppCompatActivity implements View.OnClickListener {

    private int myear,month,day;
    private Spinner dest_spinner, dept_spinner,Select_busSpinner ;
    private ImageButton btnPlus, btnMinus;
    private TextView number_place;
    private  EditText datePicker;
    private String date;
    final Calendar myCalendar = Calendar.getInstance();
    private ProgressBar spinner;
    private Button findRide;
    private // Access a Cloud Firestore instance from your Activity
            FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



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

                myear = year;
                month = monthOfYear;
                day = dayOfMonth;
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

     //Departure time
        Select_busSpinner = findViewById(R.id.spinner_time);
        List<String> Select_bus_list = new ArrayList<>();
        Select_bus_list.add("7:00 am Inter-urban travel company");
        Select_bus_list.add("12:00 am Inter-urban travel company");
        Select_bus_list.add("6:00 pm Inter-urban travel company");
        Select_bus_list.add("9:00 pm Inter-urban travel company");
        ArrayAdapter<String> busAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,Select_bus_list);
        Select_busSpinner.setAdapter(busAdapter);


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
        String _time = String.valueOf(Select_busSpinner.getSelectedItem());
        String name = "lala", email = "xyz@gmail.com", phone = "778001212";
        date = String.valueOf(day)+"/"+String.valueOf(month +1)+"/"+String.valueOf(myear);

        if (user != null) {
            // Name, email address, and phone
            name = user.getDisplayName();
            email = user.getEmail();
            phone = user.getPhoneNumber();


            // Check if user's email is verified
            //boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }

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

            //Bus bus_seat_booked = new Bus( String.valueOf(dept_spinner.getSelectedItem()),String.valueOf(dest_spinner.getSelectedItem()),date,String.valueOf(_time),Integer.valueOf(number_place.getText().toString()));
           // db.collection("Reservations").document("client").set(bus_seat_booked);
// Create a new user with a first and last name
            Map<String, Object> bus_seat_booked = new HashMap<>();
           bus_seat_booked.put("Dept_town", String.valueOf(dept_spinner.getSelectedItem()));
            bus_seat_booked.put("Dest_town",String.valueOf(dest_spinner.getSelectedItem()));
            bus_seat_booked.put("date",date);
            bus_seat_booked.put("leaving_time",String.valueOf(_time));
            bus_seat_booked.put("place_booked",Integer.valueOf(number_place.getText().toString()));
            bus_seat_booked.put("Custom",name );
            bus_seat_booked.put("email", email);
            bus_seat_booked.put("phone",phone);

// Add a new document with a generated ID
            db.collection("Reservations")
                    .add(bus_seat_booked)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                           // Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                           // Log.w(TAG, "Error adding document", e);
                        }
                    });




//            spinner.setVisibility(View.GONE);
  //          spinner.setVisibility(View.VISIBLE);
            Intent intent = new Intent(Research_formular.this, Result_buses.class);
            intent.putExtra("point", String.valueOf(dept_spinner.getSelectedItem()));
            intent.putExtra("destination", String.valueOf(dest_spinner.getSelectedItem()));
            intent.putExtra("tickets", String.valueOf(number_place.getText().toString()));
            intent.putExtra("day", String.valueOf(day));
            intent.putExtra("month", String.valueOf(month +1));
            intent.putExtra("year", String.valueOf(myear));
            intent.putExtra("time",String.valueOf(_time));
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
