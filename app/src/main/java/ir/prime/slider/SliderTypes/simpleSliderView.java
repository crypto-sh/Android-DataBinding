package ir.prime.slider.SliderTypes;


import android.content.Context;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;

import ir.prime.R;
import ir.prime.databinding.LayoutSliderItemBinding;
import ir.prime.model.ItemsDataParcelable;
import ir.prime.model.RowDataParcelable;


public class simpleSliderView extends BaseSliderView {


    ItemsDataParcelable item;
    BannerSelected bannerSelected;
    LayoutSliderItemBinding binding;

    public simpleSliderView(Context context, ItemsDataParcelable item) {
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
                bannerSelected.onSelectedbanner(item);
            }
        }
    }

    public interface BannerSelected {
        void onSelectedbanner(ItemsDataParcelable item);
    }
    public void setBannerSelected(BannerSelected listener){
        bannerSelected = listener;
    }

}