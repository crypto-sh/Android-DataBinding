package ir.prime.adapter;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ir.prime.R;
import ir.prime.databinding.RecyclerItemMovieBinding;
import ir.prime.databinding.RecyclerItemSerialBinding;
import ir.prime.model.Api;
import ir.prime.model.ItemsDataParcelable;

import ir.prime.model.RowDataParcelable;


/**
 * Created by alishatergholi on 2/18/18.
 */
public class RecyclerAdapterRowList extends RecyclerAdapterCustom {

    Context context;

    Api.RowType mType = Api.RowType.MOVIE;

    HorizontalItemSelected horizontalItemSelected;

    List<ItemsDataParcelable> mRowDataParcelables;

    static final String TAG = RecyclerAdapterRowList.class.getSimpleName();

    public RecyclerAdapterRowList(Context context, RowDataParcelable rowData) {
        this.context    = context;
        this.mType      = rowData.mType;
        this.mRowDataParcelables = rowData.mRows;
    }

    @Override
    public RecyclerView.ViewHolder  onCreateViewHolder(ViewGroup parent, int type) {
        LayoutInflater lInflater = LayoutInflater.from(parent.getContext());
        switch (mType){
            case SERIAL:
                RecyclerItemSerialBinding serial = DataBindingUtil.inflate(lInflater,R.layout.recycler_item_serial,parent,false);
                return new SerialBindingHolder (serial);
            case MOVIE:
                RecyclerItemMovieBinding movie = DataBindingUtil.inflate(lInflater,R.layout.recycler_item_movie,parent,false);
                return new MovieBindingHolder (movie);
            default:
                RecyclerItemSerialBinding binding = DataBindingUtil.inflate(lInflater,R.layout.recycler_item_serial,parent,false);
                return new SerialBindingHolder (binding);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemsDataParcelable item = mRowDataParcelables.get(position);
        switch (mType){
            case SERIAL:

                SerialBindingHolder  viewHolder = (SerialBindingHolder) holder;
                viewHolder.Bind(item);

                break;
            case MOVIE:
                MovieBindingHolder movieHolder = (MovieBindingHolder) holder;
                movieHolder.Bind(item);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mRowDataParcelables.size();
    }

    public class SerialBindingHolder extends RecyclerView.ViewHolder {
        private RecyclerItemSerialBinding binding;

        public SerialBindingHolder (RecyclerItemSerialBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void Bind(ItemsDataParcelable item){
            binding.setItem(item);
            binding.setHandler(new Handler());
            binding.executePendingBindings();
        }
    }

    public class MovieBindingHolder extends RecyclerView.ViewHolder {

        private RecyclerItemMovieBinding binding;

        public MovieBindingHolder (RecyclerItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void Bind(ItemsDataParcelable item){
            binding.setItem(item);
            binding.setHandler(new Handler());
            binding.executePendingBindings();
        }
    }

    public Integer getRowHeight(){
        switch (mType) {
            case MOVIE:
                return 160;
            case SERIAL:
                return 120;
            case ALSO_WATCHED:
                return 140;
            default:
                return 100;
        }
    }

    public class Handler{
        public void MovieSelected(ItemsDataParcelable item){
            if (horizontalItemSelected != null){

                horizontalItemSelected.onSelectedItem(item, Api.RowType.MOVIE);

            }
        }

        public void SerialSelected(ItemsDataParcelable item){
            if (horizontalItemSelected != null){
                horizontalItemSelected.onSelectedItem(item, Api.RowType.SERIAL);
            }
        }
    }

    public interface HorizontalItemSelected{
        void onSelectedItem(ItemsDataParcelable item, Api.RowType type);
    }

    public void setOnHorizontalSelected(HorizontalItemSelected listener){
        horizontalItemSelected = listener;
    }
}
