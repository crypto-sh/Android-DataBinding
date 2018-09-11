package ir.prime.baseClass;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import ir.prime.model.UserInfo;
import ir.prime.utils.Apps;
import ir.prime.utils.PublicFunction;


/**
 * Created by alishatergholi on 3/28/17.
 */
public abstract class BaseDialogFragment extends DialogFragment {

    AlertDialog mAlertDialogPermission;

    final String TAG = BaseDialogFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getDialog().getWindow().setGravity(Gravity.CENTER);
//      getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().setCancelable(true);
//        ButterKnife.bind(this, view);
    }

    @Override
    public void onStart() {
        super.onStart();

        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    /**
     * Every fragment has to inflate selector_radio_selected layout in the onCreateView method. We have added this method to
     * avoid duplicate all the inflate code in every fragment. You only have to return the layout to
     * inflate in this method when extends BaseFragment.
     */
    protected abstract int getFragmentLayout();

    /**
     * This method shows dialog with given title & MessageModel.
     * Also there is an option to pass onClickListener for positive & negative button.
     *
     * @param title                         - dialog title
     * @param message                       - dialog MessageModel
     * @param onPositiveButtonClickListener - listener for positive button
     * @param positiveText                  - positive button text
     * @param onNegativeButtonClickListener - listener for negative button
     * @param negativeText                  - negative button text
     */
    protected void showAlertDialog(@Nullable String title, @Nullable String message,
                                   @Nullable DialogInterface.OnClickListener onPositiveButtonClickListener,
                                   @NonNull String positiveText,
                                   @Nullable DialogInterface.OnClickListener onNegativeButtonClickListener,
                                   @NonNull String negativeText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveText, onPositiveButtonClickListener);
        builder.setNegativeButton(negativeText, onNegativeButtonClickListener);
        mAlertDialogPermission = builder.show();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAlertDialogPermission != null && mAlertDialogPermission.isShowing()) {
            mAlertDialogPermission.dismiss();
        }
    }

    protected void setTabletViewMode() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = 0;
            if (PublicFunction.getScreenSize(getActivity()) > 5) {
                width = PublicFunction.convertDpToPixelsInt(PublicFunction.getScreenWidth(getActivity()) > 400 ? 400 : PublicFunction.getScreenWidth(getActivity()), getActivity());
            } else {
                width = ViewGroup.LayoutParams.MATCH_PARENT;
            }
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    protected void addAnalyticLogEvent(DialogFragment dialogFragment, String method) {
        try {
            Bundle params = new Bundle();
            params.putString("DialogFragment", dialogFragment.getClass().getSimpleName());
            params.putString("method", method);
//            Apps.getFirebaseAnalytic(getContext()).logEvent(TAG, params);
        } catch (Exception ex) {
            PublicFunction.LogData(false, TAG, ex.getMessage());
        }
    }

    protected String getSessionId() {
        String token = "";
        UserInfo userInfo = Apps.getDataBaseHelper(getActivity()).getUserInfo();
        if (userInfo != null)
            token = userInfo.getSessionId();
        return token;
    }

    protected void showSnakbarAlert(CoordinatorLayout coordinatorLayout, String message) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    protected void showSnakbarAlert(CoordinatorLayout coordinatorLayout, String message, String actionTitle, View.OnClickListener action) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        if (!PublicFunction.StringIsEmptyOrNull(actionTitle)) {
            snackbar.setAction(actionTitle, action);
        }
        snackbar.show();
    }

}
