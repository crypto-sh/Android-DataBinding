package ir.prime.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import ir.prime.model.PageListParcelable;

/**
 * Created by alishatergholi on 3/9/18.
 */

public class RecyclerAdapterAlsowatcher extends RecyclerAdapterCustom  {

    Context context;

    ArrayList<PageListParcelable> mListAlsoWatch;

    public RecyclerAdapterAlsowatcher(Context context,ArrayList<PageListParcelable> alsoWatchs){
        this.context        = context;
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
        public void watcherItem(PageListParcelable item){

        }
    }
}
