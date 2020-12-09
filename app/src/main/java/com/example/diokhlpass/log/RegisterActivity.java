package com.example.diokhlpass.log;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.diokhlpass.R;
import com.example.diokhlpass.profile.profile_page;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEditText, birthdayEditText, addressEditText, passwordEditText;
    private EditText phoneEditText, emailEditText;
    private RadioButton maleCheckbox, femaleCheckbox;
    private Button registerButton;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private static final String USERS = "users";
    private String TAG = "RegisterActivity";
    private String  fname, email, birthday, phone, address,sex;
    private String password;
    private User user;
    private FirebaseAuth mAuth;


    final Calendar myCalendar = Calendar.getInstance();
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = findViewById(R.id.fullname_edittext);
        birthdayEditText = findViewById(R.id.birthday_edittext);
        addressEditText = findViewById(R.id.address_edittext);
        phoneEditText = findViewById(R.id.phone_edittext);
        passwordEditText = findViewById(R.id.enterpass_edittext);
        emailEditText = findViewById(R.id.email_edittext);
        maleCheckbox = findViewById(R.id.male_checkbox);
        femaleCheckbox = findViewById(R.id.female_checkbox);
        registerButton = findViewById(R.id.register_button);
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(USERS);
        mAuth = FirebaseAuth.getInstance();

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

        birthdayEditText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(RegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //insert data into firebase database
                if(validateFullName() | validatePassword() | validateEmail() | validateAddress() | validateBirth() | validatePhone() | validateGender()) {
                    fname = nameEditText.getText().toString();
                    email = emailEditText.getText().toString();
                    phone = phoneEditText.getText().toString();
                    birthday = birthdayEditText.getText().toString();
                    address = addressEditText.getText().toString();
                    password = passwordEditText.getText().toString();
                    maleCheckbox = findViewById(R.id.male_checkbox);
                    femaleCheckbox = findViewById(R.id.female_checkbox);
                    if (maleCheckbox.isChecked()){
                        sex = maleCheckbox.getText().toString();
                    }
                    if (femaleCheckbox.isChecked()){
                        sex = femaleCheckbox.getText().toString();
                    }
                    user = new User(fname, email, birthday, address, phone,password,sex);

                    Intent i = new Intent(RegisterActivity.this, profile_page.class);
                    i.putExtra("fname", fname);
                    i.putExtra("email", email);
                    i.putExtra("birth", birthday);
                    i.putExtra("address", address);
                    i.putExtra("sex", sex);
                    i.putExtra("phone", phone);
                    startActivity(i);
                    registerUser();
                }
            }
        });

    }


    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
       birthdayEditText.setText(sdf.format(myCalendar.getTime()));
    }

    // validation functions
    private boolean validateFullName(){
        String val = nameEditText.getText().toString().trim();

        if (val.isEmpty()){
          nameEditText.setError("Field  can not be empty");
          return false;
        }
        else {
            nameEditText.setError(null);
           return true;
        }

    }
    private boolean validatePassword(){
        String val = passwordEditText.getText().toString().trim();
      /*  String checkpassword = "^" +
                "(?=.*[a-zA-Z])" + //any letter
                //"(?=.*[@#$^&+=])" + at least 1 special caracter
                "(?=\\S+$)" + // no white spaces
                // ".{8,}" +    at least 8 characters
                "$";*/
        if (val.isEmpty()){
            passwordEditText.setError("Field  can not be empty");
            return false;
        }
       /* else if (!val.matches(checkpassword)){
            passwordEditText.setError("Password should contain 8 characters! ");
            return false;
        }*/
        else {
            passwordEditText.setError(null);
            return true;
        }

    }
    private boolean validateEmail(){
        String val = emailEditText.getText().toString().trim();
        String checkspaces = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()){
            emailEditText.setError("Field  can not be empty");
            return false;
        }
        else if (!val.matches(checkspaces)){
            emailEditText.setError("No White spaces are alloowe!");
            return false;
        }
        else{
            emailEditText.setError(null);
            return true;
        }

    }
    private boolean validateBirth(){
        String val = birthdayEditText.getText().toString().trim();

        if (val.isEmpty()){
            birthdayEditText.setError("Field  can not be empty");
            return false;
        }
        else {
            birthdayEditText.setError(null);
            return true;
        }

    }
    private boolean validateAddress(){
        String val = addressEditText.getText().toString().trim();

        if (val.isEmpty()){
            addressEditText.setError("Field  can not be empty");
            return false;
        }
        else {
            addressEditText.setError(null);
            return true;
        }

    }
    private boolean validatePhone(){
        String val = phoneEditText.getText().toString().trim();

        if (val.isEmpty()){
            phoneEditText.setError("Field  can not be empty");
            return false;
        }
        else {
            phoneEditText.setError(null);
            return true;
        }

    }
    private boolean validateGender(){
          maleCheckbox = findViewById(R.id.male_checkbox);
          femaleCheckbox = findViewById(R.id.female_checkbox);
        if ((maleCheckbox.isChecked())== false && (femaleCheckbox.isChecked()== false)){
               Toast.makeText(this,"Please Select Gender",Toast.LENGTH_SHORT).show();
               return  false;
        }else{
            return true;
        }
    }


    public void registerUser() {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
        }

        /**
         * adding user information to database and redirect to login screen
         * @param currentUser
         */
        public void updateUI(FirebaseUser currentUser) {
            String keyid = mDatabase.push().getKey();
            mDatabase.child(keyid).setValue(user); //adding user info to database
            Intent loginIntent = new Intent(this, PremierPgeActivity.class);
            startActivity(loginIntent);
        }
    }

