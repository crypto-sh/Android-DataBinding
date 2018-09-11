package ir.prime.utils;


/**
 * Created by alishatergholi on 12/16/17.
 */
public abstract class ResponseHandler {

    public abstract void onSuccess(byte[] result);

    public void onProgress(double percent){}

    public abstract void onFailure(int errorCode, String errorMsg);
}
