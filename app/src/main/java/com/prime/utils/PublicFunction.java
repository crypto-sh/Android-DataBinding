package com.prime.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import androidx.core.content.res.ResourcesCompat;

import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;


import java.io.FileNotFoundException;


import com.prime.BuildConfig;
import com.prime.R;

public class PublicFunction {

    public PublicFunction() {

    }

    final static String TAG = PublicFunction.class.getSimpleName();

    static Typeface mTypeFace;

    public static Integer getVersionCode(Context context) {
        PackageInfo pInfo = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            LogData(false, TAG, "getVersionName", e.getMessage());
            return 0;
        }
    }

    public static String getVersionName(Context context) {
        PackageInfo pInfo = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            LogData(false, TAG, "getVersionName", e.getMessage());
            return "";
        }
    }

    public static Typeface getTypeFace(Context context) throws FileNotFoundException {
        if (mTypeFace == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mTypeFace = context.getResources().getFont(R.font.iran_sans_light);
            } else {
                mTypeFace = ResourcesCompat.getFont(context, R.font.prime_font);
            }
        }
        return mTypeFace;
    }

    public static void LogData(boolean debug, String tag, String log) {
        if (debug) {
            if (BuildConfig.DEBUG) {
                Log.d(tag, log);
            } else {
                Log.d(tag, log);
            }
        } else {
            if (BuildConfig.DEBUG) {
                Log.e(tag, log);
            } else {
//                Crashlytics.log(1, tag, log);
                Log.e(tag, log);
            }
        }
    }

    public static void LogData(boolean debug, String tag, String method, String log) {
        if (debug) {
            if (BuildConfig.DEBUG) {
                Log.d(tag, method + " : " + log);
            } else {
                Log.d(tag, method + " : " + log);
            }
        } else {
            if (BuildConfig.DEBUG) {
                Log.e(tag, method + " : " + log);
            } else {
//                Crashlytics.log(1, tag, method + " : " + log);
                Log.e(tag, log);
            }
        }
    }

    public static float convertPixelsToDp(float px, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public static int convertDpToPixelsInt(float dp, Context context) {
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }

    public static Boolean StringIsEmptyOrNull(String string) {
        try {
            return string == null || string.equals(null) || string.length() == 0 || string.isEmpty() || string.equals("null") || string.equals("");
        } catch (Exception ex) {
            return false;
        }
    }

    public static double getScreenSize(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
        return Math.sqrt(x + y);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

}
