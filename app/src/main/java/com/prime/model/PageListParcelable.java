package com.prime.model;



import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.prime.enum_package.RowType;

import java.util.List;


/**
 * Created by alishatergholi on 2/24/18.
 */
public class PageListParcelable extends BaseObservable  implements Parcelable {

    @Bindable
    public String rowTitle;

    @Bindable
    public RowType rowType;

    @Bindable
    public List<ItemsDataParcelable> rowData;
    
    protected PageListParcelable(Parcel in) {
        rowTitle = in.readString();
        rowType  = RowType.Parse(in.readInt());
        rowData = in.createTypedArrayList(ItemsDataParcelable.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rowTitle);
        dest.writeInt(rowType.getCode());
        dest.writeTypedList(rowData);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PageListParcelable> CREATOR = new Creator<PageListParcelable>() {
        @Override
        public PageListParcelable createFromParcel(Parcel in) {
            return new PageListParcelable(in);
        }

        @Override
        public PageListParcelable[] newArray(int size) {
            return new PageListParcelable[size];
        }
    };
}
