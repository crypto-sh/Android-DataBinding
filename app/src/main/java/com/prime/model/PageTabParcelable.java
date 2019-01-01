package com.prime.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PageTabParcelable implements Parcelable {

    String pageKey;
    String title;
    Boolean isSelected;
    String theme;

    public PageTabParcelable() {
    }

    public String getPageKey() {
        return pageKey;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public String getTheme() {
        return theme;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pageKey);
        dest.writeString(title);
        dest.writeInt((isSelected == null || !isSelected) ? 0 : 1 );
        dest.writeString(theme);
    }

    protected PageTabParcelable(Parcel in) {
        pageKey      = in.readString();
        title        = in.readString();
        isSelected   = in.readInt() == 1;
        theme        = in.readString();
    }

    public static final Creator<PageTabParcelable> CREATOR = new Creator<PageTabParcelable>() {
        @Override
        public PageTabParcelable createFromParcel(Parcel in) {
            return new PageTabParcelable(in);
        }

        @Override
        public PageTabParcelable[] newArray(int size) {
            return new PageTabParcelable[size];
        }
    };
}
