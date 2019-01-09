package com.prime.custom_view;


import android.content.Context;
import android.content.res.TypedArray;

import androidx.appcompat.widget.AppCompatImageView;

import android.util.AttributeSet;


import com.prime.utils.PublicFunction;

import com.prime.R;
import com.squareup.picasso.Picasso;


/**
 * Created by alishatergholi on 11/7/16.
 */
public class ImageViewCustom extends AppCompatImageView {

    boolean imageSquare = false;

    final String TAG = ImageViewCustom.class.getSimpleName();

    public ImageViewCustom(Context context) {
        super(context);
    }

    public ImageViewCustom(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageViewCustom(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        try {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageViewCustom, 0, 0);
            imageSquare = a.getBoolean(R.styleable.ImageViewCustom_square_image, false);
            init();
        } catch (Exception ex) {
            PublicFunction.LogData(false, TAG, ex.getMessage());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (imageSquare) {
            int width = getMeasuredWidth();
            setMeasuredDimension(width, width);
        }
    }

    private void init() {
        super.setClickable(true);
    }

    public void loadImage(String url) {
        loadImage(url,null);
    }

    public void loadImage(String url,ImageLoadListener listener) {
        if (listener != null) {
            listener.onStart();
        }
        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(this);
//        GlideApp
//                .with(this.getContext())
//                .load(url)
//                .centerCrop()
//                .placeholder(R.drawable.place_holder)
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        if (listener != null) {
//                            listener.onEnd(false);
//                        }
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        if (listener != null) {
//                            listener.onEnd(true);
//                        }
//                        return false;
//                    }
//                })
//                .into(this);

    }

    public interface ImageLoadListener{
        void onStart();
        void onEnd(boolean result);
    }
}
