package com.prime.slider.sliderTypes;

import android.content.Context;
import android.os.Bundle;
import android.view.View;


import java.io.File;

/**
 * When you want to make your own slider view, you must extends from this class.
 * BaseSliderView provides some useful methods.
 * I provide two example: {@link SimpleSliderView} and
 * if you want to show progressbar, you just need to set a progressbar id as @+id/loading_bar.
 */
public abstract class BaseSliderView {

    protected Context mContext;

    private Bundle mBundle;

    /**
     * Error place holder image.
     */
    private int mErrorPlaceHolderRes;

    /**
     * Empty imageView placeholder.
     */
    private int mEmptyPlaceHolderRes;

    private String mUrl;
    private File mFile;
    private int mRes;

    private boolean mErrorDisappear;

    private String mDescription;

    /**
     * Scale type of the image.
     */
    private ScaleType mScaleType = ScaleType.FitStart;

    public enum ScaleType{
        CenterCrop, CenterInside, Fit, FitCenterCrop,FitStart
    }

    protected BaseSliderView(Context context) {
        mContext = context;
    }

    /**
     * the placeholder image when loading image from url or file.
     * @param resId Image resource id
     * @return
     */
    public BaseSliderView empty(int resId){
        mEmptyPlaceHolderRes = resId;
        return this;
    }

    /**
     * determine whether remove the image which failed to download or load from file
     * @param disappear
     * @return
     */
    public BaseSliderView errorDisappear(boolean disappear){
        mErrorDisappear = disappear;
        return this;
    }

    /**
     * if you set errorDisappear false, this will set a error placeholder image.
     * @param resId image resource id
     * @return
     */
    public BaseSliderView error(int resId){
        mErrorPlaceHolderRes = resId;
        return this;
    }

    /**
     * the description of a slider image.
     * @param description
     * @return
     */
    public BaseSliderView description(String description){
        mDescription = description;
        return this;
    }

    /**
     * set a url as a image that preparing to load
     * @param url
     * @return
     */
    public BaseSliderView image(String url){
        if(mFile != null || mRes != 0){
            throw new IllegalStateException("Call multi image function," +
                    "you only have permission to call it once");
        }
        mUrl = url;
        return this;
    }

    /**
     * set a file as a image that will to load
     * @param file
     * @return
     */
    public BaseSliderView image(File file){
        if(mUrl != null || mRes != 0){
            throw new IllegalStateException("Call multi image function," +
                    "you only have permission to call it once");
        }
        mFile = file;
        return this;
    }

    public BaseSliderView image(int res){
        if(mUrl != null || mFile != null){
            throw new IllegalStateException("Call multi image function," +
                    "you only have permission to call it once");
        }
        mRes = res;
        return this;
    }

    /**
     * lets users add a bundle of additional information
     * @param bundle
     * @return
     */
    public BaseSliderView bundle(Bundle bundle){
        mBundle = bundle;
        return this;
    }

    public String getUrl(){
        return mUrl;
    }

    public boolean isErrorDisappear(){
        return mErrorDisappear;
    }

    public int getEmpty(){
        return mEmptyPlaceHolderRes;
    }

    public int getError(){
        return mErrorPlaceHolderRes;
    }

    public String getDescription(){
        return mDescription;
    }

    public Context getContext(){
        return mContext;
    }

    public abstract View getView();

    public BaseSliderView setScaleType(ScaleType type){
        mScaleType = type;
        return this;
    }

    /**
     * when you have some extra information, please put it in this bundle.
     * @return
     */
    public Bundle getBundle(){
        return mBundle;
    }

    public interface ImageLoadListener{
        public void onStart(BaseSliderView target);
        public void onEnd(boolean result, BaseSliderView target);
    }
}
