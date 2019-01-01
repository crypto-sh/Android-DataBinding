package com.prime.adapter;


import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.prime.enum_package.RowType;
import com.prime.model.ItemsDataParcelable;

import java.util.List;

import com.prime.R;
import com.prime.databinding.RecyclerItemMovieBinding;
import com.prime.databinding.RecyclerItemSerialBinding;

import com.prime.model.PageListParcelable;
import com.prime.pk_interface.MediaItemSelected;


/**
 * Created by alishatergholi on 2/18/18.
 */
public class RecyclerAdapterRowList extends RecyclerAdapterCustom {

    private RowType type;
    private List<ItemsDataParcelable> rowData;
    private MediaItemSelected horizontalItemSelected;

    public RecyclerAdapterRowList(PageListParcelable rowData) {
        this.type = rowData.rowType;
        this.rowData    = rowData.rowData;
    }

    @Override
    public RecyclerView.ViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        LayoutInflater lInflater = LayoutInflater.from(parent.getContext());
        switch (this.type){
            case SERIAL:
                RecyclerItemSerialBinding serial  = DataBindingUtil.inflate(lInflater,R.layout.recycler_item_serial,parent,false);
                return new SerialBindingHolder (serial);
            case MOVIE:
                RecyclerItemMovieBinding movie    = DataBindingUtil.inflate(lInflater,R.layout.recycler_item_movie,parent,false);
                return new MovieBindingHolder (movie);
            default:
                RecyclerItemSerialBinding binding = DataBindingUtil.inflate(lInflater,R.layout.recycler_item_serial,parent,false);
                return new SerialBindingHolder (binding);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemsDataParcelable item = rowData.get(position);
        switch (type){
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
        return rowData.size();
    }

    public void setMediaItemSelected(MediaItemSelected listener){
        horizontalItemSelected = listener;
    }

    public class SerialBindingHolder extends RecyclerView.ViewHolder {
        private RecyclerItemSerialBinding binding;

        SerialBindingHolder(RecyclerItemSerialBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void Bind(ItemsDataParcelable item){
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

    Integer getRowHeight(){
        switch (type) {
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
                horizontalItemSelected.onSelectedItem(item, RowType.MOVIE);
            }
        }

        public void SerialSelected(ItemsDataParcelable item){
            if (horizontalItemSelected != null){
                horizontalItemSelected.onSelectedItem(item, RowType.SERIAL);
            }
        }
    }


}
