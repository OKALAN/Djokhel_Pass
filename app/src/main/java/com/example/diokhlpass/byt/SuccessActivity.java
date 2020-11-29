package com.example.diokhlpass.byt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.diokhlpass.R;
import com.example.diokhlpass.home.Home;
import org.json.JSONException;
import org.json.JSONObject;

public class SuccessActivity extends AppCompatActivity {
    private Button qrcode;
    private String infoQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        qrcode = findViewById(R.id.qr_generator);

 //Getting Intent
        Intent intent = getIntent();
        infoQR =  getIntent().getStringExtra("infoQR");


        try {
            JSONObject jsonDetails = new JSONObject(intent.getStringExtra("PaymentDetails"));

            //Displaying payment details
            showDetails(jsonDetails.getJSONObject("response"));


        } catch (JSONException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SuccessActivity.this,qr_code.class);
                i.putExtra("infoQR",infoQR);
                startActivity(i);
            }
        });
    }
    private void showDetails(JSONObject jsonDetails) throws JSONException {
        //Views
        TextView textViewId = (TextView) findViewById(R.id.paymentId);
        TextView textViewStatus= (TextView) findViewById(R.id.paymentStatus);

        //Showing the paypal payment details and status from json object
        textViewId.setText(jsonDetails.getString("id"));
        textViewStatus.setText(jsonDetails.getString("state"));



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SuccessActivity.this, Home.class));
    }
}
