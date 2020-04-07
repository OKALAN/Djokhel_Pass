package com.example.diokhlpass;

public class Application extends android.app.Application {

    private prefs prefs;
    private static Application app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        prefs = new prefs(this);
    }
    public static Application getApp() {
        return app;
    }
    public prefs getPrefs() {
        return prefs;
    }
    public void setPrefs(prefs prefs) {
        this.prefs = prefs;
    }


}
