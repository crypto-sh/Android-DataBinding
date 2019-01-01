package com.prime.utils;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Process;

import java.util.concurrent.TimeUnit;

import com.prime.android.MainActivity;

import androidx.multidex.MultiDex;
import okhttp3.OkHttpClient;


/**
 * Created by alishatergholi on 11/5/16.
 */
public class Apps extends Application {

    private static Apps Instance;

    private static DBController instance;

    private static OkHttpClient okHttpClientInstance;

    final String TAG = Apps.class.getSimpleName();

    public static Apps getInstance() {
        return Instance;
    }

    static {
        System.loadLibrary("native-lib");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public static native String getSaltString();

    public static native String getAppIDString();

    @Override
    public void onCreate() {
        super.onCreate();

        Thread.setDefaultUncaughtExceptionHandler((thread, e) -> handleUncaughtException(thread, e));
    }

    public void handleUncaughtException(Thread thread, Throwable e) {
        try {
            String exceprionMessage = "****************************************************\n";
            exceprionMessage += "exception ClassName    : " + e.getClass().getSimpleName() + "\n";
            if (e.getStackTrace().length > 0) {
                exceprionMessage += "exception MethodName   : " + e.getStackTrace()[0].getMethodName() + "\n";
                exceprionMessage += "exception LineNumber   : " + e.getStackTrace()[0].getLineNumber() + "\n";
            }
            exceprionMessage += "exception Message      : " + e.getMessage() + "\n";
            exceprionMessage += "****************************************************\n";
            PublicFunction.LogData(false, thread.getName(), exceprionMessage);
            // restartApplication(getApplicationContext());
        } catch (Exception ex) {
            PublicFunction.LogData(false, TAG, "handleUncaughtException ", ex.getMessage());
        }
    }

    public static void restartApplication(Context context) {
        Intent registerActivity = new Intent(context, MainActivity.class);
        registerActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(registerActivity);
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static synchronized DBController getDataBaseHelper(Context context) {
        if (instance == null)
            instance = new DBController(context);
        return instance;
    }

    public static synchronized OkHttpClient getClient() {
        if (okHttpClientInstance == null) {
            //HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            //interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //.addNetworkInterceptor(interceptor)
            okHttpClientInstance = new OkHttpClient().newBuilder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build();

        }
        return okHttpClientInstance;

    }
}
