package com.example.diokhlpass.log;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.diokhlpass.R;
import com.example.diokhlpass.home.Home;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class PremierPgeActivity extends AppCompatActivity {

    private TextView  registerTextView, forgetPassTextView;
    private EditText emailEditText, passwordEditText;
    private ImageView logoImageView;
    private Button loginButton;
    private FirebaseAuth mAuth;
    private String email, password;
    private static final String TAG = "activity_premier_pge";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premier_pge);


        registerTextView = findViewById(R.id.register_textview);
        forgetPassTextView = findViewById(R.id.forget_password_textview);
        emailEditText = findViewById(R.id.emailogin_edittext);
        passwordEditText = findViewById(R.id.passwordlogin_edittext);
        loginButton = findViewById(R.id.button);

        mAuth = FirebaseAuth.getInstance();

        //checking if user is logged in
       if (mAuth.getCurrentUser() != null) {
            updateUI(mAuth.getCurrentUser());
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();

                if (email.compareTo("")==0 || password.compareTo("")==0) {

                    Toast.makeText(PremierPgeActivity.this,"email or password can't be empty!",Toast.LENGTH_SHORT).show();

                }

                else {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(PremierPgeActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(PremierPgeActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //  updateUI(null);
                                }

                                // ...
                            }
                        });
                }
            }
        });



        registerTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            updateUI(currentUser);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            updateUI(currentUser);
        }
    }

    public void updateUI(FirebaseUser currentUser) {
        Intent HomePgeIntent = new Intent(getApplicationContext(), Home.class);
          HomePgeIntent.putExtra("email", currentUser.getEmail());
//        Log.v("DATA", currentUser.getUid());
        startActivity(HomePgeIntent);
    }


}
