package ir.prime.primevideo.Utils;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

import ir.prime.primevideo.BuildConfig;


/**
 * Created by alishatergholi on 2/14/18.
 */
public class PublicFunction {

    static final String TAG = PublicFunction.class.getSimpleName();

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

    public static Boolean StringIsEmptyOrNull(String string) {
        try {
            return string == null || string.equals(null) || string.length() == 0 || string.isEmpty() || string.equals("null") || string.equals("");
        } catch (Exception ex) {
            return false;
        }
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
}
