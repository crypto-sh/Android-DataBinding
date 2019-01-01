package com.prime.adapter;


import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by alishatergholi on 3/1/17.
 */
public abstract class RecyclerAdapterCustom extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

    protected onAdapterCustomListener onAdapterCustomListener;
    public interface onAdapterCustomListener {
        void scrollChanged(int nextPage);
    }
    public void setonAdapterCustomListener(onAdapterCustomListener listener) {
        onAdapterCustomListener = listener;
    }


}
