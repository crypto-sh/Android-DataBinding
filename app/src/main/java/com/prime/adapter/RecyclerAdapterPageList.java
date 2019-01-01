package com.prime.adapter;



import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.prime.enum_package.RowType;
import com.prime.model.ItemsDataParcelable;
import com.prime.model.PageListParcelable;
import com.prime.pk_interface.MediaItemSelected;
import com.prime.slider.SliderLayout;
import com.prime.slider.sliderTypes.SimpleSliderView;
import com.prime.utils.PublicFunction;

import java.util.List;
import com.prime.R;
import com.prime.custom_view.LayoutManagerCustom;
import com.prime.databinding.RecyclerItemCastBinding;
import com.prime.databinding.RecyclerItemCommentInfoBinding;
import com.prime.databinding.RecyclerItemCommentRowBinding;
import com.prime.databinding.RecyclerItemDirectorBinding;
import com.prime.databinding.RecyclerItemEpisodeBinding;
import com.prime.databinding.RecyclerItemKeyvalueBinding;
import com.prime.databinding.RecyclerItemTextBinding;

import com.prime.databinding.RecyclerRowBannerBinding;
import com.prime.databinding.RecyclerRowItemBinding;

/**
 * Created by alishatergholi on 2/18/18.
 */
public class RecyclerAdapterPageList extends RecyclerAdapterCustom implements MediaItemSelected,SimpleSliderView.BannerSelected {

    private MediaItemSelected mediaItemSelected;

    private List<PageListParcelable> listRow;

    private final String TAG = RecyclerAdapterPageList.class.getSimpleName();

    public RecyclerAdapterPageList(MediaItemSelected mediaItemSelected, List<PageListParcelable> mListRowParcelables) {
        this.mediaItemSelected = mediaItemSelected;
        this.listRow = mListRowParcelables;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        LayoutInflater lInflater = LayoutInflater.from(parent.getContext());
        switch (RowType.Parse(type)) {
            case BANNER:
                RecyclerRowBannerBinding binding = DataBindingUtil.inflate(lInflater, R.layout.recycler_row_banner, parent, false);
                return new BannerBindingHolder(binding);
            case SERIAL:
            case MOVIE:
                RecyclerRowItemBinding recyclerBinding           = DataBindingUtil.inflate(lInflater, R.layout.recycler_row_item, parent, false);
                return new RowBindingHolder(recyclerBinding);
            case CAST:
                RecyclerItemCastBinding castBinding              = DataBindingUtil.inflate(lInflater,R.layout.recycler_item_cast,parent,false);
                return new CastBindingHolder(castBinding);
            case EPISODE:
                RecyclerItemEpisodeBinding episodBinding          = DataBindingUtil.inflate(lInflater,R.layout.recycler_item_episode,parent,false);
                return new EpisodeBindingHolder(episodBinding);
            case DIRECTOR:
                RecyclerItemDirectorBinding directorBinding      = DataBindingUtil.inflate(lInflater,R.layout.recycler_item_director,parent,false);
                return new DirectorBindingHolder(directorBinding);
            case KEY_VALUE:
                RecyclerItemKeyvalueBinding keyvalueBinding      = DataBindingUtil.inflate(lInflater,R.layout.recycler_item_keyvalue,parent,false);
                return new KeyValueBindingHolder(keyvalueBinding);
            case TEXT:
                RecyclerItemTextBinding textBinding              = DataBindingUtil.inflate(lInflater,R.layout.recycler_item_text,parent,false);
                return new TextBindingHolder(textBinding);
            case COMMENT_ROW:
                RecyclerItemCommentRowBinding cmtRowBinding      = DataBindingUtil.inflate(lInflater,R.layout.recycler_item_comment_row,parent,false);
                return new CommentRowBindingHolder(cmtRowBinding);
            case COMMENT_INFO:
                RecyclerItemCommentInfoBinding cmtInfoBinding    = DataBindingUtil.inflate(lInflater,R.layout.recycler_item_comment_info,parent,false);
                return new CommentInfoBindingHolder(cmtInfoBinding);
            default:
                RecyclerRowItemBinding undefineBinding           = DataBindingUtil.inflate(lInflater, R.layout.recycler_row_item, parent, false);
                return new RowBindingHolder(undefineBinding);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return listRow.get(position).rowType.getCode();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PageListParcelable item = listRow.get(position);
        switch (RowType.Parse(getItemViewType(position))) {
            case BANNER:
                BannerBindingHolder bannerHolder             = (BannerBindingHolder) holder;
                bannerHolder.Bind(item);
                break;
            case SERIAL:
            case MOVIE:
            case ALSO_WATCHED:
                RowBindingHolder viewHolder                  = (RowBindingHolder) holder;
                viewHolder.Bind(item);
                break;
            case CAST:
                CastBindingHolder castViewHolder             = (CastBindingHolder)holder;
                castViewHolder.Bind(item);
                break;
            case EPISODE:
                EpisodeBindingHolder episodeHolder           = (EpisodeBindingHolder)holder;
                episodeHolder.Bind(item);
                break;
            case DIRECTOR:
                DirectorBindingHolder directHolder           = (DirectorBindingHolder)holder;
                directHolder.Bind(item);
                break;
            case KEY_VALUE:
                KeyValueBindingHolder keyValueHolder         = (KeyValueBindingHolder)holder;
                keyValueHolder.Bind(item);
                break;
            case TEXT:
                TextBindingHolder textHolder                 = (TextBindingHolder)holder;
                textHolder.Bind(item);
                break;
            case COMMENT_ROW:
                CommentRowBindingHolder commentRowHolder     = (CommentRowBindingHolder)holder;
                commentRowHolder.Bind(item);
                break;
            case COMMENT_INFO:
                CommentInfoBindingHolder commentInfoHolder   = (CommentInfoBindingHolder)holder;
                commentInfoHolder.Bind(item);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listRow.size();
    }


    @Override
    public void onSelectedbanner(ItemsDataParcelable item) {
        if (mediaItemSelected != null){
            mediaItemSelected.onSelectedItem(item, RowType.BANNER);
        }
    }

    @Override
    public void onSelectedItem(ItemsDataParcelable item, RowType type) {
        if (mediaItemSelected != null){
            mediaItemSelected.onSelectedItem(item,type);
        }
    }

    public class BannerBindingHolder extends RecyclerView.ViewHolder {

        private RecyclerRowBannerBinding binding;

        BannerBindingHolder(RecyclerRowBannerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void Bind(PageListParcelable rowParcelable) {
            binding.slider.removeAllSliders();
            int content_h   = 9;
            int content_w   = 16;
            int width       = binding.getRoot().getResources().getDisplayMetrics().widthPixels;
            int height      = ((width * content_h) / content_w);
            binding.setHeight(height);
            for (ItemsDataParcelable item : rowParcelable.rowData) {
                SimpleSliderView defaultSliderView = new SimpleSliderView(binding.getRoot().getContext(), item);
                defaultSliderView.setBannerSelected(RecyclerAdapterPageList.this);
                binding.slider.addSlider(defaultSliderView);
            }
            binding.slider.setDuration(6000);
        }
    }

    public class RowBindingHolder extends RecyclerView.ViewHolder {

        private RecyclerRowItemBinding binding;

        RowBindingHolder(RecyclerRowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void Bind(PageListParcelable rowParcelable){
            try {
                binding.recyclerView.setLayoutManager(new LayoutManagerCustom(binding.getRoot().getContext(),false));
                RecyclerAdapterRowList adapter = new RecyclerAdapterRowList(rowParcelable);
                adapter.setOnHorizontalSelected(RecyclerAdapterPageList.this);
                binding.setHeight(adapter.getRowHeight());
                binding.setAdapter(adapter);
                binding.setItemRow(rowParcelable);
            } catch (Exception e) {
                PublicFunction.LogData(false,TAG,e.getMessage());
            }
        }
    }

    public class CastBindingHolder extends RecyclerView.ViewHolder {

        private RecyclerItemCastBinding binding;

        CastBindingHolder(RecyclerItemCastBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void Bind(PageListParcelable rowParcelable){
            CastAdapter adapter = new CastAdapter(binding.getRoot().getContext(),rowParcelable);
            binding.setCastAdapter(adapter);
            binding.setItemRow(rowParcelable);
            binding.setHeight(adapter.getHeight());
        }
    }

    public class DirectorBindingHolder extends RecyclerView.ViewHolder {

        private RecyclerItemDirectorBinding binding;

        DirectorBindingHolder(RecyclerItemDirectorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void Bind(PageListParcelable rowParcelable) {
            if (rowParcelable.rowData.size() > 0){
                ItemsDataParcelable item = rowParcelable.rowData.get(0);
                binding.setTitle(rowParcelable.rowTitle);
                binding.setItem(item);
            }
        }
    }

    public class EpisodeBindingHolder extends RecyclerView.ViewHolder {

        private RecyclerItemEpisodeBinding binding;

        EpisodeBindingHolder(RecyclerItemEpisodeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void Bind(PageListParcelable rowParcelable){
            if (rowParcelable.rowData.size() > 0){
                ItemsDataParcelable item = rowParcelable.rowData.get(0);
                binding.setItem(item);
            }
        }
    }

    public class KeyValueBindingHolder extends RecyclerView.ViewHolder {

        private RecyclerItemKeyvalueBinding binding;

        KeyValueBindingHolder(RecyclerItemKeyvalueBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void Bind(PageListParcelable rowParcelable){
            if (rowParcelable.rowData.size() > 0){
                ItemsDataParcelable item = rowParcelable.rowData.get(0);
                binding.setItem(item);
            }
        }
    }

    public class TextBindingHolder extends RecyclerView.ViewHolder {

        private RecyclerItemTextBinding binding;

        TextBindingHolder(RecyclerItemTextBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void Bind(PageListParcelable rowParcelable){
            if (rowParcelable.rowData.size() > 0){
                ItemsDataParcelable item = rowParcelable.rowData.get(0);
                binding.setItem(item);
            }
        }
    }

    public class CommentRowBindingHolder extends RecyclerView.ViewHolder {

        private RecyclerItemCommentRowBinding binding;

        CommentRowBindingHolder(RecyclerItemCommentRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void Bind(PageListParcelable rowParcelable){

        }
    }

    public class CommentInfoBindingHolder extends RecyclerView.ViewHolder {

        private RecyclerItemCommentInfoBinding binding;

        public CommentInfoBindingHolder(RecyclerItemCommentInfoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void Bind(PageListParcelable rowParcelable){

        }
    }

    @BindingAdapter(value = {"android:pagerAdapter"})
    public static void BindingGridviewData(GridView gridView, CastAdapter adapter){
        gridView.setAdapter(adapter);
    }

    @BindingAdapter(value = {"android:layout_height"})
    public static void setGridViewHeight(GridView gridView,Integer height){
        if (height == null){
            return;
        }
        int margin = gridView.getContext().getResources().getDimensionPixelSize(R.dimen.header_margin);
        gridView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height + PublicFunction.convertDpToPixelsInt(margin,gridView.getContext())));
    }

    @BindingAdapter(value = {"android:layout_height"})
    public static void setRecyclerHeight(RecyclerView recycler,Integer height){
        if (height == null){
            return;
        }
        recycler.setLayoutManager(new LayoutManagerCustom(recycler.getContext(),false));
        int margin = recycler.getContext().getResources().getDimensionPixelSize(R.dimen.header_margin);
        recycler.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,PublicFunction.convertDpToPixelsInt(height + margin,recycler.getContext())));
    }

    @BindingAdapter(value = {"android:layout_height"})
    public static void setBannerHeight(SliderLayout sliderLayout, Integer height){
        sliderLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height));
    }
}
