package com.example.diokhlpass.profile;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.diokhlpass.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class profile_page extends AppCompatActivity {

    private TextView nompre, annif, email, adresse, sexe;
    private final String TAG = this.getClass().getName().toUpperCase();
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private Map<String, String> userMap;
    private DatabaseReference userRef;
    private String userid;
    private static final String USERS = "users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);


        nompre = findViewById(R.id.nompre);
        annif = findViewById(R.id.birth);
        email = findViewById(R.id.email);
        adresse = findViewById(R.id.Adress);
        sexe = findViewById(R.id.sexe);

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference(USERS);





            String nom, anniv, mail, adress, sex;
            nom = getIntent().getStringExtra("fname");
            mail = getIntent().getStringExtra("email");
            adress = getIntent().getStringExtra("address");
            anniv = getIntent().getStringExtra("birth");
            sex = getIntent().getStringExtra("sex");




                nompre.setText(nom);
                annif.setText(anniv);
                email.setText(mail);
                adresse.setText(adress);
                sexe.setText(sex);








    }
}
