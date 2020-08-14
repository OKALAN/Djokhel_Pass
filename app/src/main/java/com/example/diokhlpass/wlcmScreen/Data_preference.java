package com.example.diokhlpass.wlcmScreen;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Data_preference extends AppCompatActivity {
    private com.example.diokhlpass.wlcmScreen.prefs prefs;
    private  static Data_preference data_preference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        data_preference = this;
        prefs = new prefs(this);

    }
    public static  Data_preference getData_preference(){
        return data_preference;
    }
    public prefs getPrefs(){
        return  prefs;
    }
    public void setPrefs(prefs prefs){
        this.prefs= prefs;
    }
}
