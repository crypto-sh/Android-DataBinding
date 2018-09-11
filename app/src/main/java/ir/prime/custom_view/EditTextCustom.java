package ir.prime.custom_view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodManager;

import java.io.FileNotFoundException;

import ir.prime.R;
import ir.prime.utils.PublicFunction;


/**
 * Created by alishatergholi on 11/7/16.
 */

public class EditTextCustom extends AppCompatEditText {


    Context mContext;

    Boolean priceField = false;

    final String TAG = EditTextCustom.class.getSimpleName();

    public EditTextCustom(Context context) {
        super(context);
        init(context);
        priceField = false;
    }

    public EditTextCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EditTextCustom);
        Integer priceValue = a.getInteger(R.styleable.EditTextCustom_price_field,0);
        if (priceValue == 1) {
            priceField = true;
        }
        a.recycle();
    }

    public EditTextCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        mContext = context;
        try {
            if (!this.isInEditMode())
                this.setTypeface(PublicFunction.getTypeFace(context));
        } catch (FileNotFoundException e) {
            PublicFunction.LogData(false,TAG,e.getMessage());
        }
    }

    public void focusRequest(Activity activity) {
        try {
            this.requestFocus();
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT);
        } catch (Exception e) {
            PublicFunction.LogData(false, TAG, e.getMessage());
        }
    }

}

