package ir.prime.custom_view;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import java.io.FileNotFoundException;

import ir.prime.utils.PublicFunction;


/**
 * Created by alishatergholi on 11/7/16.
 */
public class ButtonCustom extends AppCompatButton {

    final String TAG = ButtonCustom.class.getSimpleName();

    public ButtonCustom(Context context) {
        super(context);
        init(context);
    }

    public ButtonCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ButtonCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        try {
            if (!this.isInEditMode())
                this.setTypeface(PublicFunction.getTypeFace(context));
        } catch (FileNotFoundException e) {
            PublicFunction.LogData(false,TAG,e.getMessage());
        }
    }

    public void setStyle(Context context, int style) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            this.setTextAppearance(context, style);
        } else {
            this.setTextAppearance(style);
        }
    }

}
