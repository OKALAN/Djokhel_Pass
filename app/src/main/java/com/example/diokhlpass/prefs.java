package com.example.diokhlpass;
//To set flags in preference

import android.content.Context;
import android.content.SharedPreferences;

public class prefs {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    //mode
    int PRIVATE_MODE = 0;

    // file name
    private static final String PREF_NAME = "OKALAN";
    private static final String IS_FIRST_TIME = "IsFirstTime";

    public prefs(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME, true);
    }

}




