package com.prime.android;


import android.os.Bundle;
import android.util.Log;


import com.prime.baseClass.BaseActivity;
import com.prime.databinding.ActivityMainBinding;
import com.prime.model.UserInfo;

import com.prime.R;

import androidx.databinding.DataBindingUtil;


public class MainActivity extends BaseActivity {

    UserInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setTemp(user);
        binding.setHandler(new MyHandler());
    }

    public class MyHandler {
        public void callService(){
            Log.d(this.getClass().getSimpleName(),"callService");
        }
    }
}
