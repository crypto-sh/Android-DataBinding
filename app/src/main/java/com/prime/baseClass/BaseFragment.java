package com.prime.baseClass;


import android.content.Intent;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;
import com.prime.android.DetailsSerialActivity;
import com.prime.enum_package.RowType;
import com.prime.model.ItemsDataParcelable;
import com.prime.model.UserInfo;
import com.prime.pk_interface.MediaItemSelected;
import com.prime.utils.Apps;
import com.prime.utils.PublicValue;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.prime.android.DetailsMovieActivity;


/**
 * Created by alishatergholi on 11/30/16.
 */
public abstract class BaseFragment extends Fragment implements MediaItemSelected {

    protected <T extends ViewDataBinding> T setContentView(ViewGroup container, int layoutId) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        return DataBindingUtil.inflate(layoutInflater, layoutId, container, false);
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
    public void onSelectedItem(ItemsDataParcelable item, RowType type) {
        switch (type) {
            case MOVIE:
                Intent movieDetails = new Intent(getContext(), DetailsMovieActivity.class);
                movieDetails.putExtra(PublicValue.EXTRA_DETAILS, item);
                startActivity(movieDetails);
                break;
            case SERIAL:
                Intent serialDetails = new Intent(getContext(), DetailsSerialActivity.class);
                serialDetails.putExtra(PublicValue.EXTRA_DETAILS, item);
                startActivity(serialDetails);
                break;
            case BANNER:

                break;
        }
    }
}