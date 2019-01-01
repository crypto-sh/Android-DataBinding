package com.prime.model;


import android.os.Parcel;
import android.os.Parcelable;


import java.util.List;


/**
 * Created by alishatergholi on 2/24/18.
 */
public class PageDataParcelable implements Parcelable {

    public Integer status;
    public PageTabParcelable pageData;
    public List<PageTabParcelable> tabRow;
    public List<PageListParcelable> listRow;

    public PageDataParcelable() {

    }

    protected PageDataParcelable(Parcel in) {
        status      = in.readInt();
        pageData    = in.readParcelable(PageTabParcelable.class.getClassLoader());
        tabRow      = in.createTypedArrayList(PageTabParcelable.CREATOR);
        listRow     = in.createTypedArrayList(PageListParcelable.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(status);
        dest.writeParcelable(pageData, flags);
        dest.writeTypedList(tabRow);
        dest.writeTypedList(listRow);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PageDataParcelable> CREATOR = new Creator<PageDataParcelable>() {
        @Override
        public PageDataParcelable createFromParcel(Parcel in) {
            return new PageDataParcelable(in);
        }

        @Override
        public PageDataParcelable[] newArray(int size) {
            return new PageDataParcelable[size];
        }
    };
}
