package ir.prime.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import ir.prime.R;
import ir.prime.enum_package.ErrorCode;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by alishatergholi on 2/20/18.
 */
public class RestClient {

    static final String TAG = RestClient.class.getSimpleName();

    synchronized public static void Post (
            @NonNull Context context,
            @NonNull String url,
            @NonNull RequestParams params,
            @NonNull ResponseHandler responsehandler) {
        try {
            if (!PublicFunction.checkNetworkConnection(context)){
                responsehandler.onFailure(ErrorCode.ERROR_INTERNET_CONNECTION.getCode(),context.getString(R.string.error_internet_access));
                return;
            }
            Long startTime = getTimeMillisecond();
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("accept-language"    ,"fa")
                    .addHeader("Content-type"       ,"application/x-www-form-urlencoded")
                    .addHeader("cache-control"      ,"no-cache")
                    .addHeader("os"                 ,"android")
//                    .addHeader("Accept-Encoding"    ,"gzip")
                    .addHeader("Accept"             ,"application/x-protobuf")
                    //.addHeader("Accept"             ,"application/octet-stream")
                    .post(params.getRequestForm())
                    .build();
            Apps.getClient().newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    PublicFunction.LogData(true,TAG,"url : " + url + " - duration : " + calcTime(startTime) + " second");
                    responsehandler.onFailure(ErrorCode.ERROR_SERVER_CONNECTTON.getCode(),e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if(response.isSuccessful()) {
                        byte[] result = response.body().source().readByteArray();
                        PublicFunction.LogData(true,TAG,"url : " + url + " - duration : " + calcTime(startTime) + " second - size " + getFileSize(result));
                        responsehandler.onSuccess(result);
                    } else {
                        PublicFunction.LogData(true,TAG,"url : " + url + " - duration : " + calcTime(startTime) + " second");
                        responsehandler.onFailure(ErrorCode.ERROR_SERVER_DATA_ERROR.getCode(),response.body().string());
                    }
                }
            });
        } catch (Exception ex) {
            PublicFunction.LogData(false, TAG, "Exception", ex.getMessage());
        }
    }

    public static String calcTime(Long startTime){
        Long duration = getTimeMillisecond() - startTime;
        return String.valueOf(TimeUnit.MILLISECONDS.toSeconds(duration)) + "." + String.valueOf(TimeUnit.MILLISECONDS.toMillis(duration) % 1000).substring(0,1);
    }

    public static String getFileSize(byte[] result) {
        String modifiedFileSize = null;
        double fileSize = 0.0;
        fileSize = result.length;//in Bytes
        if (fileSize < 1024) {
            modifiedFileSize = String.valueOf(fileSize).concat(" B");
        } else if (fileSize > 1024 && fileSize < (1024 * 1024)) {
            modifiedFileSize = String.valueOf(Math.round((fileSize / 1024 * 100.0)) / 100.0).concat(" KB");
        } else {
            modifiedFileSize = String.valueOf(Math.round((fileSize / (1024 * 1204) * 100.0)) / 100.0).concat(" MB");
        }
        return modifiedFileSize;
    }

    private static String gethash(Context context,String timeMilisecond) {
        String hash = PublicFunction.MD5(String.format("%s_%s_%s_%s", Apps.getAppIDString(), timeMilisecond, PublicValue.getIMEI(context), Apps.getSaltString()));
        return hash;

    }

    private static Long getTimeMillisecond() {
        return System.currentTimeMillis();
    }
}
