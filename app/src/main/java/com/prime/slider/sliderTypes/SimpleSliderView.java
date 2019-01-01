package com.prime.slider.sliderTypes;


import android.content.Context;

import androidx.databinding.DataBindingUtil;

import android.view.LayoutInflater;
import android.view.View;

import com.prime.enum_package.RowType;
import com.prime.model.ItemsDataParcelable;

import com.prime.R;
import com.prime.databinding.LayoutSliderItemBinding;
import com.prime.pk_interface.MediaItemSelected;


public class SimpleSliderView extends BaseSliderView {

    private ItemsDataParcelable item;
    private MediaItemSelected bannerSelected;
    private LayoutSliderItemBinding binding;

    public SimpleSliderView(Context context, ItemsDataParcelable item) {
        super(context);
        this.item = item;
    }

    @Override
    public View getView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        binding = DataBindingUtil.inflate(inflater,R.layout.layout_slider_item,null,false);
        binding.setItem(item);
        binding.setHandler(new Handler());
        return binding.getRoot();
    }

    public class Handler{
        public void ImageSelected(ItemsDataParcelable item){
            if (bannerSelected != null){
                bannerSelected.onSelectedItem(item,RowType.BANNER);
            }
        }
    }

    public void setBannerSelected(MediaItemSelected listener){
        bannerSelected = listener;
    }

}