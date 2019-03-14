package hj.gsdsd.mafdsf.lesson3;

import android.app.Application;

public class MyApplication extends Application {

    private Object mObject;

    @Override
    public void onCreate() {
        super.onCreate();
        mObject = new Object();
    }
}
