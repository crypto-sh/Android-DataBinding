package com.prime.baseClass;



import android.content.Context;
import android.content.Intent;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.snackbar.Snackbar;
import com.prime.model.UserInfo;
import com.prime.utils.Apps;
import com.prime.utils.PublicFunction;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.prime.adapter.RecyclerAdapterCustom;
import com.prime.custom_view.CustomViewPager;
import com.prime.custom_view.ImageViewCustom;
import com.prime.custom_view.TabLayoutCustom;

import com.prime.R;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by alishatergholi on 11/7/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected boolean isOnline = true;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        //LocaleHelper.onAttach(newBase);
    }

    protected <T extends ViewDataBinding> T setContentView(AppCompatActivity activity,int layoutId){
        return DataBindingUtil.setContentView(activity, layoutId);
    }

    protected void skipActivity(Class<?> classOf) {
        Intent intent = new Intent(getApplicationContext(), classOf);
        startActivity(intent);
    }

    protected String getSessionId() {
        String token = "";
        UserInfo userInfo = Apps.getDataBaseHelper(getApplicationContext()).getUserInfo();
        if (userInfo != null)
            token = userInfo.getSessionId();
        return token;
    }

    protected String getSubscriberId() {
        String subscriberId = "";
        UserInfo userInfo = Apps.getDataBaseHelper(getApplicationContext()).getUserInfo();
        if (userInfo != null)
            subscriberId = userInfo.getSubscriberId();
        return subscriberId;
    }

    public void setFragments(int layout, Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_out, R.anim.fade_in);
        transaction.replace(layout, fragment, fragment.getClass().getSimpleName());
        if (addToBackStack)
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commit();
    }

    @BindingAdapter("android:src")
    public static void BindingImageDetails(ImageViewCustom imageview, String url) {
        if (!PublicFunction.StringIsEmptyOrNull(url)) {
            imageview.loadImage(url);
        }
    }

    @BindingAdapter(value = {"android:pagerAdapter"}, requireAll = false)
    public static void setRecyclerAdapter(RecyclerView recyclerview, RecyclerAdapterCustom adapter) {
        recyclerview.setAdapter(adapter);
    }

    @BindingAdapter({"android:pagerAdapter"})
    public static void bindViewPagerAdapter(ViewPager view, PagerAdapter adapter) {
        view.setAdapter(adapter);
    }

    @BindingAdapter({"pager"})
    public static void bindViewPagerTabs(TabLayoutCustom view, CustomViewPager pagerView) {
        view.setupWithViewPager(pagerView, true);
    }

    protected void webServiceErrorHandler(CoordinatorLayout coordinatorLayout, int errorCode, String error) {
        switch (errorCode) {
            case 1:
                break;
            default:
                showSnakbarAlert(coordinatorLayout, error);
                break;
        }
    }

    protected void showSnakbarAlert(View coordinatorLayout, String message) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        ViewGroup group = (ViewGroup) snackbar.getView();
        group.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.snackbar_color));;
        snackbar.show();
    }

    protected void showSnakbarAlert(View coordinatorLayout, String message, String actionTitle, View.OnClickListener action) {
        final Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(actionTitle, action);
        View sbView = snackbar.getView();
        snackbar.setActionTextColor(getResources().getColor(R.color.white));
        sbView.setBackgroundColor(ContextCompat.getColor(BaseActivity.this, R.color.snackbar_color));
        snackbar.show();
    }

    public String loadLocalData(String inFile) {
        String tContents = "";

        try {
            InputStream stream = getAssets().open(inFile);
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            tContents = new String(buffer);
        } catch (IOException e) {
            // Handle exceptions here
        }

        return tContents;

    }
}
