package com.example.diokhlpass.byt;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.diokhlpass.R;
import com.example.diokhlpass.bookChecker.bookChecker;
import com.example.diokhlpass.home.Home;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;

public class qr_code extends AppCompatActivity {
  private String infoQR ;
  private   ImageView image;
  private Button save;
  private  MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
 private BitMatrix bitMatrix;
 private  Bitmap bitmap;
 private String dsp , arv, sc, date, ttt,pc,num;
  private BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
private  String TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);


        dsp = getIntent().getStringExtra("dept");
        arv = getIntent().getStringExtra("arr");
        sc = getIntent().getStringExtra("scode");
        date = getIntent().getStringExtra("date");
        ttt = getIntent().getStringExtra("ttt");
        pc = getIntent().getStringExtra("pc");
        num =  getIntent().getStringExtra("num");
        infoQR=  getIntent().getStringExtra("infoQR");

        image = findViewById(R.id.image);
        save = findViewById(R.id.save);
        try{
            bitMatrix = multiFormatWriter.encode(infoQR, BarcodeFormat.QR_CODE,200,200);
            bitmap = barcodeEncoder.createBitmap(bitMatrix);
            image.setImageBitmap(bitmap);

            FileOutputStream outputStream = null;
            File file = Environment.getDataDirectory();
            File dir = new File(file.getAbsolutePath() + "/Joxeel Pass ticket(s)");
            dir.mkdirs();

            String filename = String.format("%d.png",System.currentTimeMillis());
            File outFile = new File(dir,filename);
            try{
                outputStream = new FileOutputStream(outFile);
            }catch (Exception e){
                e.printStackTrace();
            }
            bitmap = barcodeEncoder.createBitmap(bitMatrix);
            try{
                outputStream.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                outputStream.close();
            }
            catch (Exception e){
                e.printStackTrace();

            }



        }
        catch (WriterException e){
            e.printStackTrace();
        }
        ActivityCompat.requestPermissions(qr_code.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        ActivityCompat.requestPermissions(qr_code.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(qr_code.this, bookChecker.class);

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(qr_code.this, Home.class);
        startActivity(i);
        finish();
    }
}
