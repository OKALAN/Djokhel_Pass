package com.example.diokhlpass.byt;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.diokhlpass.R;
import com.example.diokhlpass.byt.paypalConfig.PaypalConfig;
import com.paypal.android.sdk.payments.*;
import org.json.JSONException;

import java.math.BigDecimal;

public class method_paiement extends AppCompatActivity {
    Button om, paypal, wave,wizall;
    private String dsp , arv, sc, num, day,month,year, ttt,pc,date;


    //Paypal  request code
    public static final int PAYPAL_REQUEST_CODE = 123;

    //Paypal Configuration Object
    private static PayPalConfiguration config = new PayPalConfiguration() // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PaypalConfig.PAYPAL_CLIENT_ID)
            .merchantName("")
            .merchantPrivacyPolicyUri(
                    Uri.parse("https://www.paypal.com/webapps/mpp/ua/privacy-full"))
            .merchantUserAgreementUri(
                    Uri.parse("https://www.paypal.com/webapps/mpp/ua/useragreement-full"))
            ;  // or live (ENVIRONMENT_PRODUCTION)
    private String infoQR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method_paiement);

        om = findViewById(R.id.OM);
        paypal = findViewById(R.id.Paypal);
        wave = findViewById(R.id.wave);
        wizall = findViewById(R.id.wizall);

        infoQR = getIntent().getStringExtra("infoQR");
        dsp = getIntent().getStringExtra("dept");
        arv = getIntent().getStringExtra("arr");
        sc = getIntent().getStringExtra("scode");
        num = getIntent().getStringExtra("NOT");
        day = getIntent().getStringExtra("day");
        month = getIntent().getStringExtra("month");
        year = getIntent().getStringExtra("year");
        ttt = getIntent().getStringExtra("ttt");
        pc =getIntent().getStringExtra("price");
        date = day+"-"+month+"-"+year;

        paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPayment();
            }
        });

        om.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(method_paiement.this, "Bientot Disponible!",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(method_paiement.this, qr_code.class);
                i.putExtra("dept", dsp);
                i.putExtra("arr",arv);
                i.putExtra("day",day);
                i.putExtra("NOT",num);
                i.putExtra("date",date);
                i.putExtra("ttt",ttt);
                i.putExtra("scode",sc);
                i.putExtra("pc", pc);

                i.putExtra("infoQR",infoQR);
                startActivity(i);

            }
        });

        wizall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(method_paiement.this, "Bientot Disponible!",Toast.LENGTH_SHORT).show();
            }
        });

        wave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(method_paiement.this, "Bientot Disponible!",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getPayment() {

        //Creating a paypalpayment
        PayPalPayment payment = new PayPalPayment(new
                BigDecimal( getIntent().getStringExtra("priceTotal")),"USD","Price Ticket(s)",PayPalPayment.PAYMENT_INTENT_SALE);

        //Creating Paypal Payment activity intent
        Intent intent = new Intent(this, PaymentActivity.class);
        //putting the paypal configuration to the intent
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        //invoice number
        payment.invoiceNumber("321");

        //Puting paypal payment to the intent
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        //Starting the intent activity for result
        //the request code will be used on the method onActivityResult
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYPAL_REQUEST_CODE) {
            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                //Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                Log.d("CONFIRM", String.valueOf(confirm));
                //if confirmation is not null
                if (confirm != null) {
                    try {
                        //Getting the payment details
                        String paymentDetails = confirm.toJSONObject().toString(4);
                        Log.d("paymentExample", paymentDetails);
                        Log.i("paymentExample", paymentDetails);
                        Log.d("Pay Confirm : ", String.valueOf(confirm.getPayment().toJSONObject()));
//                        Starting a new activity for the payment details and status to show
                        Intent intent = new Intent(this, SuccessActivity.class).putExtra("PaymentDetails", paymentDetails);
                        intent.putExtra("infoQR",infoQR);

                        startActivity(intent);

                    } catch (JSONException e) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred : ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    }

    @Override
    public void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }
}
