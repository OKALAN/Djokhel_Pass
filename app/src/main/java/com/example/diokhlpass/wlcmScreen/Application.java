package com.example.diokhlpass.wlcmScreen;

public class Application extends android.app.Application {

    private com.example.diokhlpass.wlcmScreen.prefs prefs;
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
