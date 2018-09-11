package ir.prime.custom_view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import java.io.FileNotFoundException;

import ir.prime.utils.PublicFunction;


/**
 * Created by alishatergholi on 11/7/16.
 */
public class TextViewCustom extends AppCompatTextView {

    Context mContext;

    final String TAG = TextViewCustom.class.getSimpleName();

    public TextViewCustom(Context context) {
        super(context);
        init(context);
    }

    public TextViewCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TextViewCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        mContext = context;
        try {
            if (isInEditMode())
                PublicFunction.getTypeFace(context);
        } catch (FileNotFoundException e) {
            PublicFunction.LogData(false,TAG,e.getMessage());
        }
    }
}
