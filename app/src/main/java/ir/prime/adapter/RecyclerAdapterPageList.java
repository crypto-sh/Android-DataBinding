package ir.prime.adapter;


import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;
import ir.prime.R;
import ir.prime.custom_view.LayoutManagerCustom;
import ir.prime.databinding.RecyclerItemCastBinding;
import ir.prime.databinding.RecyclerItemCommentInfoBinding;
import ir.prime.databinding.RecyclerItemCommentRowBinding;
import ir.prime.databinding.RecyclerItemDirectorBinding;
import ir.prime.databinding.RecyclerItemEpisodeBinding;
import ir.prime.databinding.RecyclerItemKeyvalueBinding;
import ir.prime.databinding.RecyclerItemTextBinding;

import ir.prime.databinding.RecyclerRowBannerBinding;
import ir.prime.databinding.RecyclerRowItemBinding;
import ir.prime.model.Api;
import ir.prime.model.ItemsDataParcelable;
import ir.prime.model.PageListParcelable;
import ir.prime.model.RowDataParcelable;
import ir.prime.slider.SliderLayout;
import ir.prime.slider.SliderTypes.simpleSliderView;
import ir.prime.utils.PublicFunction;

/**
 * Created by alishatergholi on 2/18/18.
 */
public class RecyclerAdapterPageList extends RecyclerAdapterCustom implements RecyclerAdapterRowList.HorizontalItemSelected,simpleSliderView.BannerSelected {

    Context context;

    static int bottomMargin = 8;

    MediaItemSelected mediaItemSelected;

    List<RowDataParcelable> mListRowParcelables;

    static final String TAG = RecyclerAdapterPageList.class.getSimpleName();

    public RecyclerAdapterPageList(Context context,PageListParcelable page) {
        this.context             = context;
        this.mListRowParcelables = page.mRows;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        LayoutInflater lInflater = LayoutInflater.from(parent.getContext());
        Api.RowType rowType = Api.RowType.forNumber(type);
        if (rowType == null){
            rowType = Api.RowType.UNDEFINED_ROWTYPE;
        }
        switch (rowType) {
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
        return mListRowParcelables.get(position).mType.getNumber();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Api.RowType rowType = Api.RowType.forNumber(getItemViewType(position));
        RowDataParcelable item = mListRowParcelables.get(position);
        if (rowType == null){
            return;
        }
        PublicFunction.LogData(true,TAG,"type : " + rowType.toString() + " position : " + position);
        switch (rowType) {
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
        return mListRowParcelables.size();
    }

    @Override
    public void onSelectedItem(ItemsDataParcelable item, Api.RowType type) {
        if (mediaItemSelected != null){
            mediaItemSelected.onSelectedItem(item,type);
        }
    }

    @Override
    public void onSelectedbanner(ItemsDataParcelable item) {
        if (mediaItemSelected != null){
            mediaItemSelected.onSelectedItem(item, Api.RowType.BANNER);
        }
    }

    public class BannerBindingHolder extends RecyclerView.ViewHolder {

        private RecyclerRowBannerBinding binding;

        public BannerBindingHolder(RecyclerRowBannerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void Bind(RowDataParcelable rowParcelable) {
            binding.slider.removeAllSliders();
            int content_h = 9;
            int content_w = 16;
            int width = context.getResources().getDisplayMetrics().widthPixels;
            int height = ((width * content_h) / content_w);
            binding.setHeight(height);
            for (ItemsDataParcelable item : rowParcelable.mRows) {
                simpleSliderView defaultSliderView = new simpleSliderView(context, item);
                defaultSliderView.setBannerSelected(RecyclerAdapterPageList.this);
                binding.slider.addSlider(defaultSliderView);
            }
            binding.slider.setDuration(6000);
        }
    }

    public class RowBindingHolder extends RecyclerView.ViewHolder {
        private RecyclerRowItemBinding binding;

        public RowBindingHolder(RecyclerRowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void Bind(RowDataParcelable rowParcelable){
            try {
                binding.recyclerView.setLayoutManager(new LayoutManagerCustom(context,false));
                RecyclerAdapterRowList adapter = new RecyclerAdapterRowList(context,rowParcelable);
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

        public CastBindingHolder(RecyclerItemCastBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void Bind(RowDataParcelable rowParcelable){
            castAdapter adapter = new castAdapter(binding.getRoot().getContext(),rowParcelable);
            binding.setCastAdapter(adapter);
            binding.setItemRow(rowParcelable);
            binding.setHeight(adapter.getHeight());
        }
    }

    public class DirectorBindingHolder extends RecyclerView.ViewHolder {

        private RecyclerItemDirectorBinding binding;

        public DirectorBindingHolder(RecyclerItemDirectorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void Bind(RowDataParcelable rowParcelable) {
            if (rowParcelable.mRows.size() > 0){
                ItemsDataParcelable item = rowParcelable.mRows.get(0);
                binding.setTitle(rowParcelable.mTitle);
                binding.setItem(item);
            }
        }
    }

    public class EpisodeBindingHolder extends RecyclerView.ViewHolder {

        private RecyclerItemEpisodeBinding binding;

        public EpisodeBindingHolder(RecyclerItemEpisodeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void Bind(RowDataParcelable rowParcelable){
            if (rowParcelable.mRows.size() > 0){
                ItemsDataParcelable item = rowParcelable.mRows.get(0);
                binding.setItem(item);
            }
        }
    }

    public class KeyValueBindingHolder extends RecyclerView.ViewHolder {

        private RecyclerItemKeyvalueBinding binding;

        public KeyValueBindingHolder(RecyclerItemKeyvalueBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void Bind(RowDataParcelable rowParcelable){
            if (rowParcelable.mRows.size() > 0){
                ItemsDataParcelable item = rowParcelable.mRows.get(0);
                binding.setItem(item);
            }
        }
    }

    public class TextBindingHolder extends RecyclerView.ViewHolder {

        private RecyclerItemTextBinding binding;

        public TextBindingHolder(RecyclerItemTextBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void Bind(RowDataParcelable rowParcelable){
            if (rowParcelable.mRows.size() > 0){
                ItemsDataParcelable item = rowParcelable.mRows.get(0);
                binding.setItem(item);
            }
        }
    }

    public class CommentRowBindingHolder extends RecyclerView.ViewHolder {

        private RecyclerItemCommentRowBinding binding;

        public CommentRowBindingHolder(RecyclerItemCommentRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void Bind(RowDataParcelable rowParcelable){

        }
    }

    public class CommentInfoBindingHolder extends RecyclerView.ViewHolder {

        private RecyclerItemCommentInfoBinding binding;

        public CommentInfoBindingHolder(RecyclerItemCommentInfoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void Bind(RowDataParcelable rowParcelable){

        }
    }

    @BindingAdapter(value = {"android:pagerAdapter"})
    public static void BindingGridviewData(GridView gridView, castAdapter adapter){
        gridView.setAdapter(adapter);
    }

    @BindingAdapter(value = {"android:layout_height"})
    public static void setGridViewHeight(GridView gridView,Integer height){
        if (height == null){
            return;
        }
        gridView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height + PublicFunction.convertDpToPixelsInt(bottomMargin,gridView.getContext())));
    }

    @BindingAdapter(value = {"android:layout_height"})
    public static void setRecyclerHeight(RecyclerView recycler,Integer height){
        if (height == null){
            return;
        }
        recycler.setLayoutManager(new LayoutManagerCustom(recycler.getContext(),false));
        recycler.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,PublicFunction.convertDpToPixelsInt(height + bottomMargin,recycler.getContext())));
    }

    @BindingAdapter(value = {"android:layout_height"})
    public static void setBannerHeight(SliderLayout sliderLayout,Integer height){
        sliderLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height));
    }

    public interface MediaItemSelected{
        void onSelectedItem(ItemsDataParcelable item, Api.RowType type);
    }
    public void setMediaItemSelected(MediaItemSelected listener){
        mediaItemSelected = listener;
    }
}
