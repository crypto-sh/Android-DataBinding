package ir.prime.custom_view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * Created by Reza Amozadeh on 12/26/2017.
 */

public class SnackbarCustom {

    private LayoutInflater layoutInflater;
    private int layout;
    private int background;
    private View contentView;
    private LENGTH duration;
    private boolean swipe;

    private Snackbar snackbar;
    private Snackbar.SnackbarLayout snackbarView;

    private SnackbarCustom(Context context) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.duration = LENGTH.LONG;
        this.background = -1;
        this.layout = -1;
        this.swipe = true;
    }

    public static SnackbarCustom Builder(Context context) {
        return new SnackbarCustom(context);
    }

    public SnackbarCustom layout(int layout) {
        this.layout = layout;
        return this;
    }

    public SnackbarCustom background(int background) {
        this.background = background;
        return this;
    }

    public SnackbarCustom duration(LENGTH duration) {
        this.duration = duration;
        return this;
    }

    public SnackbarCustom swipe(boolean swipe) {
        this.swipe = swipe;
        return this;
    }

    public SnackbarCustom build(View view) {
        if (view == null) throw new CustomSnackbarException("view can not be null");
        if (layout == -1) throw new CustomSnackbarException("layout must be setted");
        switch (duration) {
            case INDEFINITE:
                snackbar = Snackbar.make(view, "", Snackbar.LENGTH_INDEFINITE);
                break;
            case SHORT:
                snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT);
                break;
            case LONG:
                snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);
                break;
        }
        snackbarView = (Snackbar.SnackbarLayout) snackbar.getView();

        if (!swipe) {
            snackbarView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    snackbarView.getViewTreeObserver().removeOnPreDrawListener(this);
                    ((CoordinatorLayout.LayoutParams) snackbarView.getLayoutParams()).setBehavior(null);
                    return true;
                }
            });
        }

        snackbarView.setPadding(0, 0, 0, 0);
        if (background != -1) snackbarView.setBackgroundResource(background);
        TextView text = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        text.setVisibility(View.INVISIBLE);
        TextView action = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_action);
        action.setVisibility(View.INVISIBLE);
        contentView = layoutInflater.inflate(layout, null);
        snackbarView.addView(contentView, 0);
        return this;
    }

    public void show() {
        snackbar.show();
    }

    public boolean isShowing() {
        return snackbar != null && snackbar.isShown();
    }

    public void dismiss() {
        if (snackbar != null) snackbar.dismiss();
    }

    public View getContentView() {
        return contentView;
    }

    public enum LENGTH {
        INDEFINITE, SHORT, LONG
    }

    public class CustomSnackbarException extends RuntimeException {

        public CustomSnackbarException(String detailMessage) {
            super(detailMessage);
        }

    }



//    public static void showSnakbarAlert(Context context, View view, String message) {
//        SnackbarCustom snackbar = SnackbarCustom.Builder(context)
//                .layout(R.layout.layout_snackbar)
//                .duration(SnackbarCustom.LENGTH.LONG)
//                .swipe(true)
//                .build(view);
//
//        TextView textView = snackbar.getContentView().findViewById(R.id.snackbar_text);
//        textView.setText(message);
//
//        snackbar.show();
//    }
//
//    public static void showSnakbarAlertWithRetry(Context context, View view, String message, View.OnClickListener onClickListener) {
//        CustomSnackbar snackbar = CustomSnackbar.Builder(context)
//                .layout(R.layout.layout_custom_snackbar_alert_with_retry)
//                .duration(CustomSnackbar.LENGTH.LONG)
//                .swipe(true)
//                .build(view);
//
//        TextView textView = snackbar.getContentView().findViewById(R.id.snackbar_text);
//        textView.setText(message);
//
//        Button button_retry = snackbar.getContentView().findViewById(R.id.snackbar_action_retry);
//        button_retry.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onClickListener.onClick(view);
//            }
//        });
//
//        snackbar.show();
//    }
//
//    public static void showSnakbarYesNo(Context context, View view, String message, @Nullable SnackBarCallBack snackBarCallBack) {
//        CustomSnackbar snackbar = CustomSnackbar.Builder(context)
//                .layout(R.layout.layout_custom_snackbar_yes_no)
//                .duration(CustomSnackbar.LENGTH.INDEFINITE)
//                .swipe(true)
//                .build(view);
//
//        TextView textView = snackbar.getContentView().findViewById(R.id.snackbar_text);
//        textView.setText(message);
//        Button button_yes = snackbar.getContentView().findViewById(R.id.snackbar_action_yes);
//        button_yes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                snackbar.dismiss();
//                if (snackBarCallBack != null) {
//                    snackBarCallBack.onYesButtonClick();
//                }
//            }
//        });
//        Button button_no = snackbar.getContentView().findViewById(R.id.snackbar_action_no);
//        button_no.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                snackbar.dismiss();
//                if (snackBarCallBack != null) {
//                    snackBarCallBack.onCancelButtonClick();
//                }
//            }
//        });
//        snackbar.show();
//    }
//
//    public static void showSnakbarNewAddress(Context context, View view, @Nullable SnackBarAddressCallBack snackBarAddressCallBack) {
//        CustomSnackbar snackbar = CustomSnackbar.Builder(context)
//                .layout(R.layout.layout_custom_snackbar_new_address)
//                .duration(CustomSnackbar.LENGTH.INDEFINITE)
//                .build(view);
//        AppCompatEditText AppCompatEditText_address = snackbar.getContentView().findViewById(R.id.AppCompatEditText_address);
//
//        Button button_submit = snackbar.getContentView().findViewById(R.id.snackbar_action_submit);
//        button_submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                snackbar.dismiss();
//                if (snackBarAddressCallBack != null) {
//                    snackBarAddressCallBack.onSubmitButtonClick(AppCompatEditText_address.getText().toString());
//                }
//            }
//        });
//        Button button_cancel = snackbar.getContentView().findViewById(R.id.snackbar_action_cancel);
//        button_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                snackbar.dismiss();
//                if (snackBarAddressCallBack != null) {
//                    snackBarAddressCallBack.onCancelButtonClick();
//                }
//            }
//        });
//        snackbar.show();
//    }
}
