package com.prime.adapter;



import android.content.Context;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import com.prime.model.ItemsDataParcelable;
import com.prime.model.PageListParcelable;
import com.prime.utils.PublicFunction;

import java.util.List;

import com.prime.R;
import com.prime.custom_view.ImageViewCustom;
import com.prime.databinding.GridItemCaseBinding;


/**
 * Created by alishatergholi on 3/9/18.
 */
public class CastAdapter extends BaseAdapter {

    private static int width;
    private static int height;
    private static int rowCount;
    private static int heightImage;

    castViewHolder holder;

    List<ItemsDataParcelable> mItems;

    public CastAdapter(Context context,PageListParcelable listRowParcelable){

        this.mItems = listRowParcelable.rowData;

        rowCount    = (int) Math.ceil((double) mItems.size() / 3);
        width       = PublicFunction.getScreenWidth(context) / 3;

        height      = 520;
        heightImage = 450;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public ItemsDataParcelable getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            GridItemCaseBinding binding = DataBindingUtil.inflate(inflater, R.layout.grid_item_case,viewGroup,false);
            holder  = new castViewHolder(binding);
            convertView = binding.getRoot();
            convertView.setTag(holder);
        } else {
            holder = (castViewHolder) convertView.getTag();
        }
        holder.binding.setItem(getItem(position));
        holder.binding.setHeight(heightImage);
        return convertView;
    }

    @BindingAdapter(value = {"android:layout_height"})
    public static void BindingHeight(ImageViewCustom view, Integer imageheight){
        if (imageheight == null){
            return;
        }
        view.setLayoutParams(new RelativeLayout.LayoutParams(width,imageheight));
    }

    public Integer getHeight(){
        return height * rowCount;
    }

    private static class castViewHolder {

        GridItemCaseBinding binding;

        public castViewHolder(GridItemCaseBinding binding) {
            this.binding = binding;
        }

        public GridItemCaseBinding getBinding(){
            return binding;
        }

    }
}
