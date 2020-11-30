package com.example.diokhlpass.byt;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.diokhlpass.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;

public class qr_code extends AppCompatActivity {
  private   String infoQR;
  private   ImageView image;
  private Button save;
  private  MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
 private BitMatrix bitMatrix;
 private  Bitmap bitmap;
  private BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
private  String TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        infoQR = getIntent().getStringExtra("infoQR");
        image = findViewById(R.id.image);
        save = findViewById(R.id.save);
        try{
            bitMatrix = multiFormatWriter.encode(infoQR, BarcodeFormat.QR_CODE,200,200);
            bitmap = barcodeEncoder.createBitmap(bitMatrix);
            image.setImageBitmap(bitmap);

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        bitMatrix = multiFormatWriter.encode(infoQR, BarcodeFormat.QR_CODE,200,200);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }


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
                   bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);

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
                });
        }
        catch (WriterException e){
            e.printStackTrace();
        }
        ActivityCompat.requestPermissions(qr_code.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        ActivityCompat.requestPermissions(qr_code.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);



    }



}
