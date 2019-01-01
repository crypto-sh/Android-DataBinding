package com.prime.android;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;


import com.prime.baseClass.BaseActivity;
import com.prime.model.UserInfo;
import com.prime.utils.PublicFunction;
import com.prime.utils.PublicValue;

import com.prime.R;


import com.prime.databinding.ActivityMainBinding;


public class MainActivity extends BaseActivity {

    UserInfo user;

    ActivityMainBinding binding;

    Integer OVERLAY_PERMISSION = 1234;

    final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        user = new UserInfo();
//        user.setNicename("Ali Shatergholi");
//        user.setUsername("alis");

        binding = setContentView(this, R.layout.activity_main);
        binding.setTemp(user);
        binding.setHandler(new MyHandler());

    }

    public class MyHandler {

        public void callService(){
            getPageData();
        }

    }

    private void getPageData(){
        String url = PublicValue.getBaseUrl() + "getHome";
//        RestClient.Post(MainActivity.this, url, new RequestParams(), new ResponseHandler() {
//            @Override
//            public void onSuccess(byte[] result) {
//                PublicFunction.LogData(false,TAG,"onSuccess");
//            }
//
//            @Override
//            public void onFailure(int errorCode, String errorMsg) {
//                PublicFunction.LogData(false,TAG,errorMsg);
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OVERLAY_PERMISSION) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {

            } else {
                //PrimeVideoPlayer.StartPlayingOverlay(this);
            }
        }
    }
}
