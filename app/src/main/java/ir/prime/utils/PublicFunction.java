package ir.prime.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.graphics.drawable.VectorDrawableCompat;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatImageView;
import android.telephony.TelephonyManager;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;


import ir.prime.BuildConfig;
import ir.prime.custom_view.ButtonCustom;
import ir.prime.custom_view.TextViewCustom;
import ir.prime.R;
import ir.prime.enum_package.ErrorCode;

import static android.content.Context.WINDOW_SERVICE;

public class PublicFunction {

    public PublicFunction() {

    }

    final static String TAG = PublicFunction.class.getSimpleName();

    final static String JsonException = "JSON EXCEPTION";

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

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

    public static Integer convertStringToInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception ex) {
            return 0;
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

    public static void createClickableTextView(Context context, final TextView view, String text, String clickableText, final View.OnClickListener clickListener) {
//        if (PublicFunction.getTypeface(context) != null)
//            view.setTypeface(PublicFunction.getTypeface(context));
        SpannableStringBuilder spanTxt = new SpannableStringBuilder(text + " ");
        spanTxt.append(clickableText);
        spanTxt.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                clickListener.onClick(view);
            }
        }, spanTxt.length() - clickableText.length(), spanTxt.length(), 0);
        spanTxt.setSpan(new ForegroundColorSpan(getColor(context, R.color.white)), spanTxt.length() - clickableText.length(), spanTxt.length(), 0);
        view.setMovementMethod(LinkMovementMethod.getInstance());
        view.setText(spanTxt, TextView.BufferType.SPANNABLE);
    }

    public static void show_toast(Context context, String toast_string) {
        try {
            Toast toast = new Toast(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View toastView = inflater.inflate(R.layout.layout_toast, null);
            TextViewCustom toast_txt = toastView.findViewById(R.id.toastMessageTextView);
            toast_txt.setText(toast_string);
            toast.setView(toastView);
            toast.setGravity(Gravity.BOTTOM, 0, 50);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
        } catch (Exception ex) {
            LogData(false, TAG, ex.getMessage());
        }
    }

    public static boolean checkNetworkConnection(Context context) {
        try {
            if (context == null) {
                return true;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nwInfo = connectivityManager.getActiveNetworkInfo();
            return nwInfo != null && nwInfo.isConnectedOrConnecting();
        } catch (Exception ex) {

            return false;
        }
    }

    public static Boolean StringIsEmptyOrNull(String string) {
        try {
            return string == null || string.equals(null) || string.length() == 0 || string.isEmpty() || string.equals("null") || string.equals("");
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean checkMobileNumber(String phone) {
        boolean check = false;
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            if (phone.length() != 10) {
                check = false;
            } else {
                check = true;
            }
        } else {
            check = false;
        }
        return check;
    }

    public static String URLEncode(String stringValue) {

        try {
            if (PublicFunction.StringIsEmptyOrNull(stringValue))
                return "";

            return URLEncoder.encode(stringValue, "utf-8");
        } catch (UnsupportedEncodingException e) {
            PublicFunction.LogData(false, TAG, "URLEncode", e.getMessage());
            return stringValue;
        }
    }

    public static String URLDecoder(String stingvalue) {
        try {
            return URLDecoder.decode(stingvalue, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return stingvalue;
        }
    }

    public static String MD5(String md5) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    @SuppressLint("HardwareIds")
    public static String getIMEI(Context context) {
        String identifier = null;
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return "";
            }
            identifier = tm.getDeviceId();
            if (identifier == null || identifier.length() == 0)
                identifier = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        return identifier;
    }

    //region file
    private String readableFileSize(long size) {
        if (size <= 0) return size + " B";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.##").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public static String getMimeType(String url) {
        String type = null;
        try {
            String extension = MimeTypeMap.getFileExtensionFromUrl(url);
            if (extension != null) {
                type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
            }
        } catch (Exception e) {
            PublicFunction.LogData(false, TAG, "getMimeType", e.getMessage());
        }
        return type;
    }

    public static void RemoveFile(String filePath) {
        try {
            if (!PublicFunction.StringIsEmptyOrNull(filePath)) {
                File fdelete = new File(filePath);
                if (fdelete.exists()) {
                    fdelete.delete();
                }
            }
        } catch (Exception e) {
            PublicFunction.LogData(true, TAG, "File not Exist", e.getMessage());
        }
    }

    public static ArrayList<File> getImageFilePath() {
        ArrayList<File> arrayList = new ArrayList<>();
        try {
            arrayList.add(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
            arrayList.add(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
            arrayList.add(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                arrayList.add(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS));
            }
        } catch (Exception e) {
            PublicFunction.LogData(false, TAG, "getImageFilePath", e.getMessage());
        }
        return arrayList;
    }

    public static String getFileName(String filepath) {

        try {
            return filepath.substring(filepath.lastIndexOf("/") + 1);
        } catch (Exception e) {
            PublicFunction.LogData(false, TAG, "getFileName", e.getMessage());
            return "";
        }
    }

    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            PublicFunction.LogData(false, TAG, "getFileMD5", e.getMessage());
            return null;
        }
        BigInteger bigint = new BigInteger(1, digest.digest());
        return bigint.toString(16);
    }

    public static String SaveImage(Bitmap finalBitmap) {
        try {
            if (finalBitmap != null) {
                File dir = new File(Environment.getExternalStorageDirectory() + PublicValue.DIRECTORY_NAME_IMAGE);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(Environment.getExternalStorageDirectory() + PublicValue.DIRECTORY_NAME_IMAGE, System.currentTimeMillis() + "." + Bitmap.CompressFormat.PNG);
                if (file.exists()) file.delete();
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    finalBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();
                    return file.getPath();
                } catch (Exception e) {
                    PublicFunction.LogData(false, TAG, "SaveImage FileOutputStream", e.getMessage());
                    return "";
                }
            }
        } catch (Exception e) {
            PublicFunction.LogData(false, TAG, "SaveImage", e.getMessage());
        }
        return "";
    }

    public static void parseDir(File dir, ArrayList<File> files) {
        try {
            final File[] sortedByDate = dir.listFiles();
            if (sortedByDate != null && sortedByDate.length > 1) {
                Arrays.sort(sortedByDate, new Comparator<File>() {
                    @Override
                    public int compare(File object1, File object2) {
                        if ((object1).lastModified() > (object2).lastModified()) {
                            return -1;
                        } else if ((object1).lastModified() < (object2).lastModified()) {
                            return +1;
                        } else {
                            return 0;
                        }
                    }
                });
            }
            for (File file : sortedByDate) {
                if (file.isDirectory()) {
                    if (!file.getName().toLowerCase().startsWith(".")) {
                        parseDir(file, files);
                    }
                } else {
                    if (file.getName().toLowerCase().endsWith(PublicValue.EXTENSION_JPG) || file.getName().toLowerCase().endsWith(PublicValue.EXTENSION_JPEG) || file.getName().toLowerCase().endsWith(PublicValue.EXTENSION_PNG)) {
                        files.add(file);
                    }
                }
            }
        } catch (Exception ex) {
            PublicFunction.LogData(true, TAG, ex.getMessage());
        }
    }

    public static ArrayList<File> parseImageFromDirectory(File dir) {
        ArrayList<File> arrayList = new ArrayList<>();
        parseDir(dir, arrayList);
        return arrayList;
    }

    public static void pickImage(Activity activity, int updateMode) {
        try {
            if (checkAccessStoragePermission(activity)) {
                Intent intent;
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                    intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    activity.startActivityForResult(intent, updateMode);
                } else {
                    intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    activity.startActivityForResult(intent, updateMode);
                }
            }
        } catch (Exception e) {
            PublicFunction.LogData(false, TAG, "pickImage", e.getMessage());
        }
    }

    private String fileSize(File file) {
        return readableFileSize(file.length());
    }
    //endregion

    public static void closeKeyBoard(Activity activity) {
        InputMethodManager imm = null;
        View view = null;
        try {
            imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            view = activity.getCurrentFocus();
            if (view == null) {
                view = new View(activity);
            }
        } catch (Exception e) {
            PublicFunction.LogData(false, TAG, "closeKeyboard", e.getMessage());
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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

    //region check Permission
    public static boolean checkNetworkPermissionWithPhoneState(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> listPermissionsNeeded = new ArrayList<>();
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.INTERNET);
            }
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE);
            }
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.ACCESS_NETWORK_STATE);
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
                return false;
            }
        }
        return true;
    }

    public static boolean checkContactPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> listPermissionsNeeded = new ArrayList<>();
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS);
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
                return false;
            }
        }
        return true;
    }

    public static boolean checkRecordVoicePermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> listPermissionsNeeded = new ArrayList<>();
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.RECORD_AUDIO);
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
                return false;
            }
        }
        return true;
    }

    public static boolean checkAccessStoragePermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> listPermissionsNeeded = new ArrayList<>();
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
                return false;
            }
        }
        return true;
    }

    public static boolean checkCameraPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> listPermissionsNeeded = new ArrayList<>();
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.CAMERA);
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
                return false;
            }
        }
        return true;
    }

    public static boolean checkLocationPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> listPermissionsNeeded = new ArrayList<>();
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
                return false;
            }
        }
        return true;
    }
    //endregion

    public static void setEmptyListErrorHandler(final Context context, final LinearLayout emptyListErrorHandler, final View.OnClickListener onClickListener, int status, String errorCode) {

        ErrorCode error = ErrorCode.valueOf(errorCode);
        switch (error) {
            case ERROR_SESSION_EXPIRE:
                Apps.getDataBaseHelper(context).clearDataBase();
                Apps.getDataBaseHelper(context).clearDataBase();
                Apps.restartApplication(context);
                break;
            case ERROR_INTERNET_CONNECTION:
                errorCode = context.getString(R.string.error_internet_access);
                break;
            case ERROR_SERVER_CONNECTTON:
                errorCode = context.getString(R.string.error_connection_server);
                break;
            case ERROR_TIMEOUT_CONNECTION:
                errorCode = context.getString(R.string.error_connection_server);
                break;
            case ERROR_EMPTY_LIST:
                errorCode = context.getString(R.string.error_empty_list);
                break;
        }
        setEmptyListErrorHandler(context, emptyListErrorHandler, onClickListener, errorCode);
    }

    public static void setEmptyListErrorHandler(final Context context, final LinearLayout emptyListErrorHandler, final View.OnClickListener onClickListener, String errorMsg) {
        try {
            emptyListErrorHandler.removeAllViews();

            RelativeLayout relativeLayout = new RelativeLayout(context);
            relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            ImageView imageViewErrorHanler = new ImageView(context);
            imageViewErrorHanler.setId(R.id.text_error_show);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(convertDpToPixelsInt(0, context), convertDpToPixelsInt(0, context));
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            imageViewErrorHanler.setLayoutParams(params);


            TextViewCustom textViewError = new TextViewCustom(context);
            textViewError.setId(R.id.button_error_show);
            textViewError.setTextColor(getColor(context, R.color.black));
            textViewError.setTextSize(14);
            textViewError.setGravity(View.TEXT_ALIGNMENT_CENTER);
            RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textParams.addRule(RelativeLayout.BELOW, imageViewErrorHanler.getId());
            textParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            textParams.leftMargin = convertDpToPixelsInt(16, context);
            textParams.rightMargin = convertDpToPixelsInt(16, context);
            textViewError.setLayoutParams(textParams);
            textViewError.setText(errorMsg);


            ButtonCustom buttonCustom = new ButtonCustom(context);
            buttonCustom.setText(context.getString(R.string.retry_button));
            RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            buttonParams.addRule(RelativeLayout.BELOW, textViewError.getId());
            buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            buttonParams.topMargin = 10;
            buttonCustom.setMinWidth(convertDpToPixelsInt(140, context));
            buttonCustom.setLayoutParams(buttonParams);
            buttonCustom.setTextAppearance(context, R.style.ButtonTransparentCustom);
            buttonCustom.setTextSize(18);
//            buttonCustom.setTypeface(PublicFunction.getTypeface(context), Typeface.BOLD);
            buttonCustom.setBackgroundDrawable(getDrawable(context, R.drawable.button_transparent));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                buttonCustom.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }

            buttonCustom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    emptyListErrorHandler.removeAllViews();
                    RelativeLayout layout = new RelativeLayout(context);
                    layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    ProgressBar progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
                    progressBar.setIndeterminate(true);
                    progressBar.getIndeterminateDrawable().setColorFilter(getColor(context, R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(convertDpToPixelsInt(56, context), convertDpToPixelsInt(56, context));
                    params.addRule(RelativeLayout.CENTER_IN_PARENT);
                    progressBar.setLayoutParams(params);
                    layout.addView(progressBar);
                    emptyListErrorHandler.addView(layout);
                    onClickListener.onClick(v);
                }
            });

            relativeLayout.addView(imageViewErrorHanler);
            relativeLayout.addView(textViewError);
            relativeLayout.addView(buttonCustom);
            emptyListErrorHandler.addView(relativeLayout);
        } catch (Exception ex) {
            PublicFunction.LogData(false, TAG, ex.getMessage());
        }
    }


    public static void loadRoundImageIntoImageView(Context context, String url, AppCompatImageView imageView) {
        try {
            GlideApp
                    .with(context)
                    .load(url)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageView);
        } catch (Exception e) {
            PublicFunction.LogData(false, TAG, e.getMessage());
        }
    }

    //region change Number language
    public static String toEnglishNum(String s) {
        char cApple = 217;
        char cNokia = 219;

        s = s.replace(new String(new char[]{cApple, 160}), "0");
        s = s.replace(new String(new char[]{cApple, 161}), "1");
        s = s.replace(new String(new char[]{cApple, 162}), "2");
        s = s.replace(new String(new char[]{cApple, 163}), "3");
        s = s.replace(new String(new char[]{cApple, 164}), "4");
        s = s.replace(new String(new char[]{cApple, 165}), "5");
        s = s.replace(new String(new char[]{cApple, 166}), "6");
        s = s.replace(new String(new char[]{cApple, 167}), "7");
        s = s.replace(new String(new char[]{cApple, 168}), "8");
        s = s.replace(new String(new char[]{cApple, 169}), "9");

        s = s.replace(new String(new char[]{cNokia, 176}), "0");
        s = s.replace(new String(new char[]{cNokia, 177}), "1");
        s = s.replace(new String(new char[]{cNokia, 178}), "2");
        s = s.replace(new String(new char[]{cNokia, 179}), "3");
        s = s.replace(new String(new char[]{cNokia, 180}), "4");
        s = s.replace(new String(new char[]{cNokia, 181}), "5");
        s = s.replace(new String(new char[]{cNokia, 182}), "6");
        s = s.replace(new String(new char[]{cNokia, 183}), "7");
        s = s.replace(new String(new char[]{cNokia, 184}), "8");
        s = s.replace(new String(new char[]{cNokia, 185}), "9");

        s = s.replace("١", "1");
        s = s.replace("٢", "2");
        s = s.replace("٣", "3");
        s = s.replace("٤", "4");
        s = s.replace("۵", "5");
        s = s.replace("۶", "6");
        s = s.replace("۷", "7");
        s = s.replace("۸", "8");
        s = s.replace("۹", "9");
        s = s.replace("۰", "0");

        return s;
    }

    public static String toPersianNumber(String text) {
        if (text.length() == 0) {
            return "";
        }
        String out = "";
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            if ('0' <= c && c <= '9') {
                int number = Integer.parseInt(String.valueOf(c));
                out += persianNumbers[number];
            } else if (c == '٫') {
                out += '،';
            } else {
                out += c;
            }
        }
        return out;
    }

    private static String[] persianNumbers = new String[]{"۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹"};
    //endregion

    //region change Status base Color
    public static void setStatusBarColor(Activity activity, int color) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(activity, color));
            }
        } catch (Exception e) {
            LogData(false, TAG, e.getMessage());
        }
    }

    public static void setStatusBarColor(Activity activity, Drawable backgroundDrawable) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getColor(activity, R.color.transparent));
                window.setBackgroundDrawable(backgroundDrawable);
            }
        } catch (Exception e) {
            LogData(false, TAG, e.getMessage());
        }
    }
    //endregion

    public static void setWindowBackgroundColor(Activity activity, int color) {
        try {
            activity.getWindow().getDecorView().setBackgroundColor(PublicFunction.getColor(activity, color));
        } catch (Exception e) {
            LogData(false, TAG, e.getMessage());
        }
    }

    public static String getTimeMilisecond() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String getAppFolderPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    public static int getColor(Context context, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    public static Drawable getDrawable(Context context, int drawable) {
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                return context.getResources().getDrawable(drawable);
            } else {
                return ContextCompat.getDrawable(context, drawable);
            }
        } catch (Exception ex) {
            LogData(false, TAG, ex.getMessage());
        }
        return null;
    }

    public static Drawable getVectorDrawable(Context context, int drawable) {
        Drawable icon;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            icon = VectorDrawableCompat.create(context.getResources(), drawable, context.getTheme());
        } else {
            icon = context.getResources().getDrawable(drawable, context.getTheme());
        }
        return icon;
    }

    public static void changeScreenOrientation(Activity activity) {
        Display display = ((WindowManager) activity.getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        final int orientation = display.getOrientation();

        switch (orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
        }
    }
}
