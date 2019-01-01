package com.prime.model;



import androidx.databinding.BaseObservable;

import android.os.Parcel;
import android.os.Parcelable;



/**
 * Created by alishatergholi on 2/24/18.
 */
public class ItemsDataParcelable extends BaseObservable implements Parcelable {

    public String id;
    public String image;
    public String backImage;
    public String ageRating;
    public String quality;
    public String imdbRating;
    public String details;

    public String title;
    public String duration;

    public EpisodeModel episode;
    public CommentRowModel commentRow;
    public CommentInfoModel commentInfo;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(image);
        dest.writeString(backImage);
        dest.writeString(ageRating);
        dest.writeString(quality);
        dest.writeString(imdbRating);
        dest.writeString(details);
        dest.writeString(title);
        dest.writeString(duration);
    }

    protected ItemsDataParcelable(Parcel in) {
        id = in.readString();
        image = in.readString();
        backImage = in.readString();
        ageRating = in.readString();
        quality = in.readString();
        imdbRating = in.readString();
        details = in.readString();
        title = in.readString();
        duration = in.readString();
    }

    public static final Creator<ItemsDataParcelable> CREATOR = new Creator<ItemsDataParcelable>() {
        @Override
        public ItemsDataParcelable createFromParcel(Parcel in) {
            return new ItemsDataParcelable(in);
        }

        @Override
        public ItemsDataParcelable[] newArray(int size) {
            return new ItemsDataParcelable[size];
        }
    };
}
