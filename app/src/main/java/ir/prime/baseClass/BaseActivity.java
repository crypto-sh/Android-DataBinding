package ir.prime.baseClass;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ir.prime.adapter.RecyclerAdapterCustom;
import ir.prime.custom_view.CustomViewPager;
import ir.prime.custom_view.ImageViewCustom;
import ir.prime.custom_view.TabLayoutCustom;
import ir.prime.model.UserInfo;
import ir.prime.R;
import ir.prime.utils.Apps;
import ir.prime.utils.PublicFunction;


/**
 * Created by alishatergholi on 11/7/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    final static String TAG = BaseActivity.class.getSimpleName();

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

    protected void addAnalyticLogEvent(Activity activity, String method) {
//        try {
//            Bundle params = new Bundle();
//            params.putString("activity", activity.getClass().getSimpleName());
//            params.putString("method", method);
//            Apps.getFirebaseAnalytic(activity).logEvent(TAG, params);
//        } catch (Exception ex) {
//            PublicFunction.LogData(false, TAG, ex.getMessage());
//        }
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
        group.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.snackbar_color));
        TextView textView = (TextView) group.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));

        snackbar.show();
    }

    protected void showSnakbarAlert(View coordinatorLayout, String message, String actionTitle, View.OnClickListener action) {
        final Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(actionTitle, action);
        View sbView = snackbar.getView();
        snackbar.setActionTextColor(getResources().getColor(R.color.white));
        int snackbarTextId = android.support.design.R.id.snackbar_text;
        TextView textView = (TextView) sbView.findViewById(snackbarTextId);
        textView.setTextColor(getResources().getColor(R.color.black));
        sbView.setBackgroundColor(ContextCompat.getColor(BaseActivity.this, R.color.snackbar_color));
        snackbar.show();
    }
}
