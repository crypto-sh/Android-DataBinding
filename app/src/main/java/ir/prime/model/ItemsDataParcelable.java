package ir.prime.model;



import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import ir.prime.BR;
import ir.prime.utils.PublicFunction;


/**
 * Created by alishatergholi on 2/24/18.
 */
public class ItemsDataParcelable extends BaseObservable implements Parcelable {

    public String mId;
    public String mImage;
    public String mTitle;
    public String mDescription;
    public EpisodeModel episode;
    public CommentRowModel commentRow;
    public CommentInfoModel commentInfo;

    final String TAG = ItemsDataParcelable.class.getSimpleName();

    public ItemsDataParcelable(Api.RowData data, Api.RowType type){
        try {
            switch (type){
                case BANNER:
                    Api.Banner item             = data.getBannerData();
                    mId                         = item.getId();
                    mImage                      = item.getImage();
                    break;
                case MOVIE:
                case ALSO_WATCHED:
                    Api.Movie movie             = data.getMovieData();
                    mId                         = movie.getId();
                    mImage                      = movie.getImage();
                    break;
                case SERIAL:
                    Api.Serial serial           = data.getSerialData();
                    mId                         = serial.getId();
                    mImage                      = serial.getImage();
                    break;
                case CAST:
                case DIRECTOR:
                    Api.Person person            = data.getPersonData();
                    mId                          = person.getId();
                    mImage                       = person.getImage();
                    mTitle                       = person.getTitle();
                    mDescription                 = person.getDescription();
                    break;
                case KEY_VALUE:
                    Api.KeyValueData keyvalue    = data.getKeyValueData();
                    mTitle                       = keyvalue.getKey();
                    mDescription                 = keyvalue.getValue();
                    break;
                case EPISODE:
                    Api.Episode episode          = data.getEpisodeData();
                    this.episode                 = new EpisodeModel(episode);
                    break;
                case TEXT:
                    Api.TextData text            = data.getTextData();
                    mDescription                 = text.getText();
                    break;
                case COMMENT_ROW:
                    Api.CommentRowData commentRow = data.getCommentRowData();
                    this.commentRow               = new CommentRowModel(commentRow);
                    break;
                case COMMENT_INFO:
                    Api.CommentInfoData commentinfo = data.getCommentInfoData();
                    this.commentInfo              = new CommentInfoModel(commentinfo);
                    break;
            }
        } catch (Exception e) {
            PublicFunction.LogData(false,TAG,e.getMessage());
        }
    }

    @Bindable
    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
        notifyPropertyChanged(BR.mId);
    }

    @Bindable
    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
        notifyPropertyChanged(BR.mImage);
    }

    @Bindable
    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
        notifyPropertyChanged(BR.mTitle);
    }

    @Bindable
    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
        notifyPropertyChanged(BR.mDescription);
    }

    protected ItemsDataParcelable(Parcel in) {
        mId             = in.readString();
        mImage          = in.readString();
        mTitle          = in.readString();
        mDescription    = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mImage);
        dest.writeString(mTitle);
        dest.writeString(mDescription);
    }

    @Override
    public int describeContents() {
        return 0;
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
