package com.prime.adapter;


import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.prime.model.PageDataParcelable;

/**
 * Created by alishatergholi on 3/9/18.
 */

public class RecyclerAdapterAlsowatcher extends RecyclerAdapterCustom  {

    ArrayList<PageDataParcelable> mListAlsoWatch;

    public RecyclerAdapterAlsowatcher(ArrayList<PageDataParcelable> alsoWatchs){
        this.mListAlsoWatch = alsoWatchs;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class Handler{
        public void watcherItem(PageDataParcelable item){

        }
    }
}
