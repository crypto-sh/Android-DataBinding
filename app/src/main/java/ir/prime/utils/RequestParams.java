package ir.prime.utils;

import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.RequestBody;


/**
 * Created by alishatergholi on 2/21/18.
 */
public class RequestParams {

    LinkedHashMap<String,String> params = new LinkedHashMap<>();

    public RequestParams() {
    }

    public void put(String key,String value){
        params.put(key,value);
    }

    public void put(String key,Integer value){
        params.put(key,String.valueOf(value));
    }

    public void put(String key,Float value){
        params.put(key,String.valueOf(value));
    }

    public void put(String key,Double value){
        params.put(key,String.valueOf(value));
    }

    public RequestBody getRequestForm(){
        FormBody.Builder body = new FormBody.Builder();
        for (String key : params.keySet()){
            body.addEncoded(key,params.get(key));
        }
        return body.build();
    }

}
