package com.prime.utils;



import com.prime.BuildConfig;


/**
 * Created by alishatergholi on 10/16/17.
 */
public class PublicValue {

    public static String Url_server_debug = "https://primevideo.ir/api/";

    public static String Url_server_release = "https://primevideo.ir/api/";

    public static final String DIRECTORY_NAME = "/Prime";

    public static final String EXTENSION_JPG = ".jpg";

    public static final String EXTENSION_JPEG = ".jpeg";

    public static final String EXTENSION_PNG = ".png";

    public static final String DIRECTORY_NAME_IMAGE = DIRECTORY_NAME + "/Image";

    public final static String EXTRA_DETAILS    = "details";

    private static String IMEI = "";

    public static String getBaseUrl() {
        if (BuildConfig.DEBUG) {
            return Url_server_debug;
        } else {
            return Url_server_release;
        }
    }
}
