package ir.prime.baseClass;



import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import ir.prime.adapter.RecyclerAdapterPageList;
import ir.prime.android.DetailsMovieActivity;
import ir.prime.android.DetailsSerialActivity;
import ir.prime.model.Api;
import ir.prime.model.ItemsDataParcelable;
import ir.prime.model.RowDataParcelable;
import ir.prime.model.UserInfo;
import ir.prime.utils.Apps;
import ir.prime.utils.PublicFunction;
import ir.prime.utils.PublicValue;

/**
 * Created by alishatergholi on 11/30/16.
 */
public abstract class BaseFragment extends Fragment implements RecyclerAdapterPageList.MediaItemSelected {

    final String TAG = BaseFragment.class.getSimpleName();

    protected <T extends ViewDataBinding> T setContentView(ViewGroup container,int layoutId){
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        return DataBindingUtil.inflate(layoutInflater,layoutId,container,false);
    }

    protected void showSnakbarAlert(CoordinatorLayout coordinatorLayout, String message) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    protected void showSnakbarAlert(CoordinatorLayout coordinatorLayout, String message, String actionTitle, View.OnClickListener action) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.setAction(actionTitle, action);
        snackbar.show();
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

    protected String getSessionId() {
        String token = "";
        UserInfo userInfo = Apps.getDataBaseHelper(getActivity()).getUserInfo();
        if (userInfo != null)
            token = userInfo.getSessionId();
        return token;
    }

    protected String getSubscriberId() {
        String subscriberId = "";
        UserInfo userInfo = Apps.getDataBaseHelper(getActivity()).getUserInfo();
        if (userInfo != null)
            subscriberId = userInfo.getSubscriberId();
        return subscriberId;
    }

    @Override
    public void onSelectedItem(ItemsDataParcelable item, Api.RowType type) {
        switch (type){
            case MOVIE:
                Intent movieDetails = new Intent(getContext(), DetailsMovieActivity.class);
                movieDetails.putExtra(PublicValue.EXTRA_DETAILS,item);
                startActivity(movieDetails);
                break;
            case SERIAL:
                Intent serialDetails = new Intent(getContext(), DetailsSerialActivity.class);
                serialDetails.putExtra(PublicValue.EXTRA_DETAILS,item);
                startActivity(serialDetails);
                break;
            case BANNER:

                break;
        }


    }

}